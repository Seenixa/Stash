package campaign.spells.playablespells
import campaign.characters.Character
import campaign.spells.Spells

class ShieldWall extends Spells {

  name = "Shield wall"
  manaCost = 5

  override def updateValues(char: Character) = {
    shielding = char.vitality * 3
  }

}