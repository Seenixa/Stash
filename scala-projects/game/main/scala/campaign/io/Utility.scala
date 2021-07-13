package campaign.io

import main.scala.campaign.io.Printer

class Utility (
  val printer: Printer    
  ) {

  def inputNumber = {
    var input = 0
    do
      try {
        input = scala.io.StdIn.readInt()
      } catch {
        case _: Throwable => printer.wrongInputNumber
      }
    while (input.getClass.getSimpleName != "int" || input < 1)
    input
  }
  
}