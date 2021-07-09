package campaign.test
import campaign.characters.Character
import campaign.enemies.Enemies
import campaign.story.Game
import campaign.fight.FightHandler
import campaign.characters.Stats
import campaign.ApplicationContext

class UnitTest {

  var appCon = new ApplicationContext

  var yourCharacter = new Character
  yourCharacter = appCon.game.chooseCharacterClass
  
  appCon.stats.levelTo(yourCharacter, 30)
  
  val enemy = new Enemies(name = "Whoever", maxHealth = 300, 
      minHitDamage = 10, maxHitDamage = 30, experienceReward = 2)
  val enemy1 = new Enemies(name = "Whatever", maxHealth = 530, 
      minHitDamage = 10, maxHitDamage = 30, experienceReward = 2)
  val enemy2 = new Enemies(name = "Wherever", maxHealth = 50, 
      minHitDamage = 10, maxHitDamage = 30, experienceReward = 2)
  val enemies = List(enemy, enemy1, enemy2)
  
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "vitality", 30)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "strength", 30)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "intelligence", 30)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "agility", 30)
  
  appCon.fightHandler.fight(yourCharacter, enemies)
  appCon.printer.character(yourCharacter)
  

}