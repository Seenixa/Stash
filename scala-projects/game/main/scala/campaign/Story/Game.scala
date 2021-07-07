package campaign.story
import campaign.characters.Character
import campaign.characters.playableclasses.Mage
import campaign.characters.playableclasses.Rogue
import campaign.characters.playableclasses.Warrior
import campaign.test.UnitTest

class Game {

  def start = {
    var gameStart = new UnitTest
  }

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

  def chooseCharacterClass(classId: Int): Character = {
    var newChar = new Character
    var choice = classId
    while (choice < 0 || choice > 3) {
      println("Choice doesn't exist, pick again.")
      choice = scala.io.StdIn.readInt()
    }
    if (choice > 0 && choice <= 3) {
      choice match {
        case 1 => newChar = new Warrior
        case 2 => newChar = new Rogue
        case 3 => newChar = new Mage
        case _ => throw new Error("Something's wrong, I can feel it.")
      }
    }
    newChar.getBaseStats
    newChar
  }
}