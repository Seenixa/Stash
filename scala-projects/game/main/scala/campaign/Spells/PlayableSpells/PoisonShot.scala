package campaign.spells.playablespells
import campaign.spells.Spells
import campaign.characters.Character

class PoisonShot extends Spells {

  name = "Poison shot"
  
  override def updateValues(char: Character) = {
    damage = char.agility
    poisonDuration = char.agility / 5
  }
  
}