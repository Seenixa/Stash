package campaign.item

class Item(

  val name:         String = "",
  val itemType:     String = "",
  val minHitDamage: Int    = 0,
  val maxHitDamage: Int    = 0,
  val strength:     Int    = 0,
  val agility:      Int    = 0,
  val intelligence: Int    = 0,
  val vitality:     Int    = 0,
  val armor:        Int    = 0) {

  override def toString = s"${this.name}"
  
}