package campaign.story
import campaign.characters.Character
import campaign.characters.playableclasses._
import campaign.test.UnitTest

class Game {

  def start = {
    var gameStart = new UnitTest
  }

  def chooseCharacterClass(classId: Int): Character = {
    var newChar = new Character
    if (classId > 0 && classId <= 3) {
      classId match {
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