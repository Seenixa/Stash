package campaign.spells.playablespells
import campaign.characters.PlayerCharacter
import campaign.spells.Spells

class Ambush extends Spells {
  
  name = "Ambush"
  damage = 50
  manaCost = 10
  
  override def updateValues(char: PlayerCharacter) = {
    damage = char.agility * 2
  }

  
}