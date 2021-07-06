package campaign.spells.playablespells
import campaign.spells.Spells
import campaign.characters.Character

class Frostbolt extends Spells {

  name = "Frostbolt"

  override def updateValues(char: Character) = {
    damage = char.intelligence * 2
    chillDuration = char.intelligence / 5
  }

}