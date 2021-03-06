package campaign.spells.playablespells
import campaign.characters.Character
import campaign.spells.Spells

class PoisonShot extends Spells {

  name = "Poison shot"
  manaCost = 5
  
  override def updateValues(char: Character) = {
    damage = char.agility
    poisonDuration = char.agility / 5
  }
  
}