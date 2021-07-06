package campaign.spells.playablespells
import campaign.spells.Spells
import campaign.enemies.Enemies

class Ambush extends Spells {

  name = "Ambush"

  override def cast(enemy: Enemies) = {
    println("Casting Ambush")
    enemy.getHit(damage)
  }

}