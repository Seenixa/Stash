package campaign.io

class Utility {

  def inputNumber = {
    var input = 0
    do
      try {
        input = scala.io.StdIn.readInt()
      } catch {
        case _: Throwable => println("Try inputting a number this time.")
      }
    while (input.getClass.getSimpleName != "int" || input < 1)
    input
  }
  
}