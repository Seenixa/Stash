package campaign.spells.playablespells
import campaign.spells.Spells
import campaign.characters.Character

class ShieldBash extends Spells {

  name = "Shield bash"

  override def updateValues(char: Character) = {
    damage = char.minHitDamage
    stunDuration = 1
  }

}
