package campaign

class ShieldBash extends Spells {
  name = "Shield bash"

  override def cast(enemy: Enemies) = {
    enemy.getHit(damage)
  }
}
