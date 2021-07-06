package campaign.characters
import campaign.spells.Spells
import campaign.items.Items
import campaign.enemies.Enemies
import campaign.characters.playableclasses._

class Character {

  var name = ""
  var level: Int = 1
  var armor: Int = 0
  var strength: Int = 0
  var agility: Int = 0
  var intelligence: Int = 0
  var vitality: Int = 0
  var unspentSkillPoints = 0
  var minHitDamage: Int = 0
  var maxHitDamage: Int = 0
  var maxHealth: Int = 0
  var health: Int = 0
  var experience: Int = 0
  var experienceToNextLevel = 100
  var maxMana: Int = 0
  var mana: Int = 0
  var shield: Int = 0
  var burnDuration = 0
  var poisonDuration = 0
  var chillDuration = 0
  var stunDuration = 0
  val random = scala.util.Random

  var inventory = Map[String, Items]()
  var spellBook = Map[String, Spells]()

  def getBaseStats = {
  }

  def getHit(amount: Int) = {
    var shielded = 0
    if (shield > 0) {
      if (amount - armor > 0 && amount - armor >= shield) {
        shield = 0
        shielded = (amount - armor) - shield
      }
      if (amount - armor > 0 && amount - armor < shield)
        shield -= amount - armor
      if (amount - armor > 0 && amount - armor <= 0) {
        shield -= 1
        shielded = 1
      }
    }
    if (shield == 0) {
      if (amount - armor > 0 && (amount - armor) - shielded >= health)
        health = 0
      if (amount - armor > 0 && (amount - armor) - shielded < health)
        health -= (amount - shielded) - armor
      if (amount > 0 && amount - armor <= 0 && shielded != 1)
        health -= 1
    }
  }

  override def toString = s"""level:        $level
                             |class:        ${this.getClass.getSimpleName}
                             |strength:     $strength
                             |agility:      $agility
                             |intelligence: $intelligence
                             |vitality:     $vitality
                             |health:       $health
                             |armor:        $armor
                             |experience:   $experience
                             |""".stripMargin

}