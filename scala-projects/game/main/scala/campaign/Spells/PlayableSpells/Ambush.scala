package campaign.spells.playablespells
import campaign.characters.Character
import campaign.spells.Spells

class Ambush extends Spells {
  
  name = "Ambush"
  
  override def updateValues(char: Character) ={
    damage = char.agility * 2
  }
}