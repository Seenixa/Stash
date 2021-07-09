package campaign.characters
import campaign.items.Items
import campaign.spells.Spells
import campaign.ApplicationContext

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
  var experienceToNextLevel = 150
  var maxMana: Int = 0
  var mana: Int = 0
  var shield: Int = 0
  var burnDuration = 0
  var poisonDuration = 0
  var chillDuration = 0
  var stunDuration = 0
  val appCon = new ApplicationContext
  val printer = appCon.printer
  val stats = appCon.stats
  val spellHandler = appCon.spellHandler
  val random = scala.util.Random

  var inventory = Map[String, Items]()
  var spellBook = Map[String, Spells]()

  def getBaseStats = {
  }

  def updateStats = {
  }

  def pierceGetHit(amount: Int) = {
    if (amount > 0 && amount >= health)
      health = 0
    if (amount > 0 && amount < health)
      health -= amount
  }

  def getHit(amount: Int) = {
    var shielded = 0
    var hit = amount - armor
    if (shield > 0) {
      if (amount > 0 && hit >= shield) {
        shield = 0
        shielded = hit - shield
      }
      if (amount > 0 && hit < shield)
        shield -= hit
      if (amount > 0 && hit <= 0) {
        shield -= 1
        shielded = 1
      }
    }
    if (shield == 0) {
      if (amount > 0 && hit - shielded >= health)
        health = 0
      if (amount > 0 && hit - shielded < health)
        health -= (hit - shielded)
      if (amount > 0 && hit <= 0 && shielded != 1)
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
                             |mana:         $mana
                             |armor:        $armor
                             |experience:   $experience
                             |""".stripMargin

}