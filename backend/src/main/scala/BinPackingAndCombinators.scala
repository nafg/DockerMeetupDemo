import scala.util.Try

import demo.{Bin, Input, Item, Output}
import oscar.cbls.binPacking.model.BinPackingProblem
import oscar.cbls.binPacking.solver.{EmptyMostViolatedBin, JumpSwapItems, MoveItem, SwapItems}
import oscar.cbls.modeling.CBLSModel

class BinPackingAndCombinators(itemSizes: List[Int], binSizes: List[Int]) extends CBLSModel {

  val problem = BinPackingProblem(itemSizes, binSizes, s, c, 0)

  s.close()

  val x = ((MoveItem(problem) exhaustBack SwapItems(problem))
    exhaust (MoveItem(problem, best = true) exhaustBack SwapItems(problem, best = true)
    orElse (JumpSwapItems(problem) maxMoves 3)
    orElse EmptyMostViolatedBin(problem))) saveBest problem.overallViolation

  x.doAllMoves(_ >= 200 || problem.overallViolation.value == 0, problem.overallViolation)

  x.restoreBest()

}

object BinPackingAndCombinators {

  def processInput(input: Input): Try[Output] =
    Try {
      val bpac = new BinPackingAndCombinators(input.items, input.bins)
      val items1 = bpac.problem.items.values.map(i => Item(i.number, i.bin.value))
      val bins1 = bpac.problem.bins.values.map(b => Bin(b.number, b.items.value.toList))
      Output(items1.toList, bins1.toList)
    }

}
