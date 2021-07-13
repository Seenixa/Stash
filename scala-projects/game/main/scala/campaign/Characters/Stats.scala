package campaign.characters
import main.scala.campaign.io.Printer

class Stats(
  val printer: Printer) {

  def levelUp(char: Character): Boolean = {
    var charLeveled = false
    while (char.experience >= char.experienceToNextLevel) {
      char.level += 1
      char.unspentSkillPoints += 5
      char.experienceToNextLevel += 100 + char.level * 50
      printer.levelUp(char)
      charLeveled = true
    }
    if (charLeveled == true) {
      updateStats(char)
      char.health = char.maxHealth
      char.mana = char.maxMana
      printer.unspentSkillPoints(char)
    }
    charLeveled
  }

  def levelTo(char: Character, level: Int) = {
    var expToLevel = 100
    var difference = 50
    var expToLastLevel = expToLevel + level * difference
    var sumExp = ((expToLevel + expToLastLevel) * (level - 1)) / 2
    char.experience = sumExp
    levelUp(char)
  }

  def updateStats(char: Character) = {
    levelUp(char)
    for (spell <- char.spellBook)
      spell._2.updateValues(char)
    char.maxHealth = char.vitality * 10
    char.maxMana = char.intelligence * 10
    char.getClass.getSimpleName match {
      case "Warrior" => {
        char.minHitDamage = char.strength
        char.maxHitDamage = char.strength * 2
      }
      case "Rogue" => {
        char.minHitDamage = char.strength + char.agility / 2
        char.maxHitDamage = char.strength + char.agility / 2
      }
      case "Mage" => {
        char.minHitDamage = char.intelligence / 2
        char.maxHitDamage = char.intelligence
      }
      case _ => printer.cantUpgradeStats(char)
    }
  }

  def spendSkillPoint(char: Character, stat: String) = {
    if (char.unspentSkillPoints > 0) {
      stat match {
        case "strength" =>
          char.unspentSkillPoints -= 1
          char.strength += 1
        case "agility" =>
          char.unspentSkillPoints -= 1
          char.agility += 1
        case "intelligence" =>
          char.unspentSkillPoints -= 1
          char.intelligence += 1
          char.mana += 10
        case "vitality" =>
          char.unspentSkillPoints -= 1
          char.vitality += 1
          char.health += 10
        case _ => println(s"We can't apply your point to $stat")
      }
    } else println("You don't have any skill points left.")
    updateStats(char)
  }

  def SpendMoreSkillPoints(char: Character, stat: String, spendTimes: Int) = {
    for (i <- 0 until spendTimes)
      spendSkillPoint(char, stat)
    printer.spentSkillPoints(stat, spendTimes)
  }
}