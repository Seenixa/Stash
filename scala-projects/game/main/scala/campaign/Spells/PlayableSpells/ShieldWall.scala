package campaign.spells.playablespells
import campaign.characters.PlayerCharacter
import campaign.spells.Spells

class ShieldWall extends Spells {

  name = "Shield wall"
  shielding = 50
  manaCost = 5

  override def updateValues(char: PlayerCharacter) = {
    shielding = char.vitality * 3
  }
  
}