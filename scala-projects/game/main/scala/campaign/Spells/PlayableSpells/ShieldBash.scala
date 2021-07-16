package campaign.spells.playablespells
import campaign.characters.PlayerCharacter
import campaign.spells.Spells

class ShieldBash extends Spells {

  name = "Shield bash"
  damage = 30
  stunDuration = 1
  manaCost = 10

  override def updateValues(char: PlayerCharacter) = {
    damage = char.minHitDamage
  }
  
}
