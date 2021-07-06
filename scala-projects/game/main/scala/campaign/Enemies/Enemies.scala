package campaign.enemies

class Enemies(
  var name:             String = "",
  var minHitDamage:     Int    = 0,
  var maxHitDamage:     Int    = 0,
  var health:           Int    = 0,
  var armor:            Int    = 0,
  var experienceReward: Int    = 0) {

  var chillDuration = 0
  var burnDuration = 0
  var poisonDuration = 0
  var stunDuration = 0

  def getHit(amount: Int) = {
    if (amount - armor > 0 && amount - armor >= health)
      health = 0
    if (amount - armor > 0 && amount - armor < health)
      health -= amount - armor
    if (amount > 0 && amount - armor < 0)
      health -= 1
  }

  override def toString = s"""name:    $name
                             |damage:  ${(minHitDamage + maxHitDamage) / 2}
                             |health:  $health
                             |armor:   $armor
                             |EXP:     $experienceReward""".stripMargin

}