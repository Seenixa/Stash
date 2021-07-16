package campaign.spells.playablespells
import campaign.characters.PlayerCharacter
import campaign.spells.Spells

class Frostbolt extends Spells {

  name = "Frostbolt"
  damage = 50
  chillDuration = 3
  manaCost = 20

  override def updateValues(char: PlayerCharacter) = {
    damage = char.intelligence * 2
    chillDuration = char.intelligence / 5
  }

}