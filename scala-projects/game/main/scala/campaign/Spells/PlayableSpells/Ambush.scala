package campaign

class Ambush extends Spells {

  name = "Ambush"

  override def cast(enemy: Enemies) = {
    println("Casting Ambush")
    enemy.getHit(damage)
  }

}