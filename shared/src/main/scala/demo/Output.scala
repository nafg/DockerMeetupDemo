package demo

case class Item(number: Int, bin: Int)
case class Bin(number: Int, items: List[Int])
case class Output(items: List[Item], bins: List[Bin])
