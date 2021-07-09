package campaign.spells.playablespells
import campaign.characters.Character
import campaign.spells.Spells

class Ambush extends Spells {
  
  name = "Ambush"
  manaCost = 10
  
  override def updateValues(char: Character) ={
    damage = char.agility * 2
  }
}