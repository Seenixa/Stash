package campaign.spells.playablespells
import campaign.spells.Spells
import campaign.characters.Character

class Ambush extends Spells {
  
  name = "Ambush"
  
  override def updateValues(char: Character) ={
    damage = char.agility * 2
  }
}