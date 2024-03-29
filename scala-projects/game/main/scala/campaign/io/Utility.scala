package campaign.io

class Utility (
  val printer: Printer    
  ) {

  def inputNumber = {
    var input = 9999
    do {
      try {
        input = scala.io.StdIn.readInt()
      } catch {
        case _: Throwable => printer.wrongInputNumber
      }
    } while (input.getClass.getSimpleName != "int" || input == 9999)
    input
  }  
}