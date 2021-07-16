package campaign.spells.playablespells
import campaign.characters.PlayerCharacter
import campaign.spells.Spells

class PoisonShot extends Spells {

  name = "Poison shot"
  damage = 40
  poisonDuration = 3
  manaCost = 5

  override def updateValues(char: PlayerCharacter) = {
    damage = char.agility
    poisonDuration = char.agility / 5
  }

}