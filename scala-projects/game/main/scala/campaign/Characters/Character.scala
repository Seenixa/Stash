package campaign.characters
import campaign.items.Items
import campaign.spells.Spells
import campaign.ApplicationContext

class Character(
  var name:         String = "",
  var armor:        Int    = 0,
  var minHitDamage: Int    = 0,
  var maxHitDamage: Int    = 0,
  var maxHealth:    Int    = 0,
  var level:        Int    = 1) {

  var shield = 0
  var health = maxHealth
  var burnDuration = 0
  var poisonDuration = 0
  var chillDuration = 0
  var stunDuration = 0


}