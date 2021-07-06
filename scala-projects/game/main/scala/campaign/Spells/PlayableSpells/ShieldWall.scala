package campaign.spells.playablespells
import campaign.spells.Spells
import campaign.characters.Character

class ShieldWall extends Spells {

  name = "Shield wall"

  override def updateValues(char: Character) = {
    shielding = char.vitality * 3
  }

}