package campaign.characters
import campaign.characters.playableclasses._
import campaign.spells.Spells

class Stats {

  def levelUp(char: Character) = {
    while (char.experience >= char.experienceToNextLevel) {
      char.level += 1
      char.unspentSkillPoints += 5
      char.experienceToNextLevel += 100 + char.level * 50
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
        case "vitality" =>
          char.unspentSkillPoints -= 1
          char.vitality += 1
        case _ => println(s"We can't apply your point to $stat")
      }
    } else println("You don't have any skill points left.")
  }

}