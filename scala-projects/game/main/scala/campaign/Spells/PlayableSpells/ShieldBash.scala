package campaign.spells.playablespells
import campaign.characters.Character
import campaign.spells.Spells

class ShieldBash extends Spells {

  name = "Shield bash"

  override def updateValues(char: Character) = {
    damage = char.minHitDamage
    stunDuration = 1
  }

}
