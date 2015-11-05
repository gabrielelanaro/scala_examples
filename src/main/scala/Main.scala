object Main {
  def main(args: Array[String]) {
      //   Select and execute the right example to play
      args(0) match {
          case "Example1" => Example1.exec(args)
          case "Example2" => Example2.exec(args)
          case _ => println(s"Class not found '${args(0)}'")
      }
      println(args(0))
  }
}
