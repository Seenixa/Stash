package campaign.characters
import campaign.io.Printer

class Stats (
    val printer: Printer
    ){

  def levelUp(char: Character) = {
    var charLeveled = false
    while (char.experience >= char.experienceToNextLevel) {
      char.level += 1
      char.unspentSkillPoints += 5
      char.experienceToNextLevel += 100 + char.level * 50
      printer.levelUp(char)
      charLeveled = true
    }
    if (charLeveled == true){
      char.updateStats
      printer.unspentSkillPoints(char)
    }
  }
  
  def levelTo(char: Character, level: Int) = {
    var expToLevel = 100
    var difference = 50
    var expToLastLevel = expToLevel + level * difference
    var sumExp = ((expToLevel + expToLastLevel) * (level - 1)) / 2
    char.experience = sumExp
    levelUp(char)
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
        case "vitality" =>
          char.unspentSkillPoints -= 1
          char.vitality += 1
        case _ => println(s"We can't apply your point to $stat")
      }
    } else println("You don't have any skill points left.")
    char.updateStats
  }

  def SpendMoreSkillPoints(char: Character, stat: String, spendTimes: Int) = {
    for (i <- 0 until spendTimes)
      spendSkillPoint(char, stat)
  }
}