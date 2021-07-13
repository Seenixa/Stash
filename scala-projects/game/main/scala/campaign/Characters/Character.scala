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

  var strength = 0
  var agility = 0
  var intelligence = 0
  var vitality = 0
  var shield = 0
  var mana = 0
  var maxMana = 0
  var health = maxHealth
  var experience = 0
  var experienceToNextLevel = 0
  var unspentSkillPoints = 0
  var experienceReward = level * 25
  var burnDuration = 0
  var poisonDuration = 0
  var chillDuration = 0
  var stunDuration = 0

  var inventory = Map[String, Items]()
  var spellBook = Map[String, Spells]()

}