package campaign.test
import campaign.characters.Character
import campaign.enemies.Enemies
import campaign.story.Game
import main.scala.campaign.fight.FightHandler
import campaign.characters.Stats
import campaign.ApplicationContext

class UnitTest {

  var appCon = new ApplicationContext

  var yourCharacter = new Character
  appCon.stats.levelTo(yourCharacter, 10)
  println("Choose your character class.")
  println("1. Warrior")
  println("2. Rogue")
  println("3. Mage")

  val charChoice = appCon.setup.inputNumber
  yourCharacter = appCon.setup.chooseCharacterClass(charChoice)
  val enemy = new Enemies(name = "Whoever", health = 50, minHitDamage = 10, maxHitDamage = 30)
  val enemy1 = new Enemies(name = "Whatever", health = 50, minHitDamage = 10, maxHitDamage = 30)
  val enemy2 = new Enemies(name = "Wherever", health = 50, minHitDamage = 10, maxHitDamage = 30)
  val enemies = List(enemy, enemy1, enemy2)
  appCon.stats.levelTo(yourCharacter, 100)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "vitality", 30)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "strength", 30)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "intelligence", 30)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "agility", 30)
  appCon.fight.fight(yourCharacter, enemies)
  

}