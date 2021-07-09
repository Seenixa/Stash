package campaign.spells.playablespells
import campaign.characters.Character
import campaign.spells.Spells

class ShieldBash extends Spells {

  name = "Shield bash"
  manaCost = 10

  override def updateValues(char: Character) = {
    damage = char.minHitDamage
    stunDuration = 1
  }

}
