package campaign.spells.playablespells
import campaign.characters.Character
import campaign.spells.Spells

class Frostbolt extends Spells {

  name = "Frostbolt"
  manaCost = 20

  override def updateValues(char: Character) = {
    damage = char.intelligence * 2
    chillDuration = char.intelligence / 5
  }

}