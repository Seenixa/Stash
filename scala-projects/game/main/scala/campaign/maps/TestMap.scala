package campaign.maps
import campaign.ApplicationContext
import campaign.characters._

class TestMap(val appCon: ApplicationContext) {

  var end = false
  var choice = 0
  var takeInput = 0
  var characterSetup = false
  var enemySetup = false
  var enemiesSetup = false
  var singleEnemy = new NpcCharacter
  var enemies = List[NpcCharacter]()
  var yourCharacter = new PlayerCharacter

  while (end == false) {
    appCon.printer.quit
    if (choice != 9) {
      appCon.printer.testMenuOne
      choice = appCon.utility.inputNumber
      choice match {
        case 1 => {
          yourCharacter = appCon.game.chooseCharacterClass(appCon)
          println("strength:")
          takeInput = appCon.utility.inputNumber
          if (takeInput != 0){
          while (yourCharacter.unspentSkillPoints < takeInput) {
            appCon.stats.levelTo(yourCharacter, yourCharacter.level + takeInput / 5)
            if (yourCharacter.unspentSkillPoints < takeInput)
              appCon.stats.levelTo(yourCharacter, yourCharacter.level + 1)
          }
          }
          appCon.stats.SpendMoreSkillPoints(yourCharacter, "strength", takeInput)
          println("agility:")
          takeInput = appCon.utility.inputNumber
          while (yourCharacter.unspentSkillPoints < takeInput) {
            appCon.stats.levelTo(yourCharacter, yourCharacter.level + takeInput / 5)
            if (yourCharacter.unspentSkillPoints < takeInput)
              appCon.stats.levelTo(yourCharacter, yourCharacter.level + 1)
          }
          appCon.stats.SpendMoreSkillPoints(yourCharacter, "agility", takeInput)
          println("intelligence:")
          takeInput = appCon.utility.inputNumber
          while (yourCharacter.unspentSkillPoints < takeInput) {
            appCon.stats.levelTo(yourCharacter, yourCharacter.level + takeInput / 5)
            if (yourCharacter.unspentSkillPoints < takeInput)
              appCon.stats.levelTo(yourCharacter, yourCharacter.level + 1)
          }
          appCon.stats.SpendMoreSkillPoints(yourCharacter, "intelligence", takeInput)
          println("vitality:")
          takeInput = appCon.utility.inputNumber
          while (yourCharacter.unspentSkillPoints < takeInput) {
            appCon.stats.levelTo(yourCharacter, yourCharacter.level + takeInput / 5)
            if (yourCharacter.unspentSkillPoints < takeInput)
              appCon.stats.levelTo(yourCharacter, yourCharacter.level + 1)
          }
          appCon.stats.SpendMoreSkillPoints(yourCharacter, "vitality", takeInput)
          println("Items: ")
          appCon.printer.notYet
          characterSetup = true
        }

        case 2 => {
          singleEnemy = new NpcCharacter
          println("enemy health:")
          takeInput = appCon.utility.inputNumber
          singleEnemy.health = takeInput
          singleEnemy.maxHealth = takeInput
          println("enemy average damage:")
          takeInput = appCon.utility.inputNumber
          singleEnemy.minHitDamage = takeInput / 2
          singleEnemy.maxHitDamage = takeInput + takeInput / 2
          println("enemy armor:")
          takeInput = appCon.utility.inputNumber
          singleEnemy.armor = takeInput
          enemySetup = true
        }

        case 3 => {
          var enemy = new NpcCharacter
          enemies = List[NpcCharacter]()
          println("How many enemies? (Max 3)")
          takeInput = appCon.utility.inputNumber
          while (takeInput < 0 || takeInput > 3)
            takeInput = appCon.utility.inputNumber
          for (enemyID <- 0 until takeInput) {
            enemy = new NpcCharacter
            println("enemy health:")
            takeInput = appCon.utility.inputNumber
            enemy.maxHealth = takeInput
            enemy.health = enemy.maxHealth
            println("enemy average damage:")
            takeInput = appCon.utility.inputNumber
            enemy.minHitDamage = takeInput / 2
            enemy.maxHitDamage = takeInput + takeInput / 2
            println("enemy armor:")
            takeInput = appCon.utility.inputNumber
            enemy.armor = takeInput
            enemies = enemy :: enemies
          }
          enemiesSetup = true
        }
        case 4 => {
          if (enemySetup) {
            appCon.fightHandler.fight(yourCharacter, List(singleEnemy))
            enemySetup = false
            if (yourCharacter.health == 0)
              characterSetup = false
          }
        }
        case 5 => {
          if (enemiesSetup) {
            appCon.fightHandler.fight(yourCharacter, enemies)
            enemiesSetup = false
            if (yourCharacter.health == 0)
              characterSetup = false
          }
        }
        case 6 => {
          if (characterSetup)
            appCon.printer.character(yourCharacter)
        }
        case 7 => {
          if (enemySetup)
            appCon.printer.enemy(singleEnemy)
        }
        case 8 => {
          if (enemiesSetup)
            for (enemy <- enemies)
              appCon.printer.enemy(enemy)
        }
        case 9 =>
          end = true
        case _ => appCon.printer.wrongChoice
      }
    }
    if (choice == 9)
      end = true
  }

}