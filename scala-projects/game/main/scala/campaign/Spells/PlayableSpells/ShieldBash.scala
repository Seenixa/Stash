package campaign.spells.playablespells
import campaign.spells.Spells
import campaign.enemies.Enemies

class ShieldBash extends Spells {
  name = "Shield bash"

  override def cast(enemy: Enemies) = {
    enemy.getHit(damage)
  }
}
