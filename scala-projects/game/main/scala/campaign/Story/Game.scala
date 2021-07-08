package campaign.story
import campaign.characters.Character
import campaign.characters.playableclasses.Mage
import campaign.characters.playableclasses.Rogue
import campaign.characters.playableclasses.Warrior
import campaign.test.UnitTest
import campaign.io._

class Game (
    val printer: Printer,
    val utility: Utility
    ) {

  def start = {
    var gameStart = new UnitTest
  }
  
  def chooseCharacterClass: Character = {
    printer.characterChoice
    var newChar = new Character
    var choice = utility.inputNumber
    while (choice < 0 || choice > 3) {
      printer.wrongChoice
      choice = utility.inputNumber
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