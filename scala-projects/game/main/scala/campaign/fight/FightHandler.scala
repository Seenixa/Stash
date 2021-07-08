package campaign.fight
import campaign.characters.Character
import campaign.enemies.Enemies
import campaign.spells.SpellHandler
import campaign.story.Game
import campaign.io._

class FightHandler(
  val utility:      Utility,
  val printer:      Printer,
  val spellHandler: SpellHandler) {

  var random = scala.util.Random

  def fight(char: Character, enemies: List[Enemies]) = {
    var counter = 0
    var enemyHealth = 1
    var enemyHitDamage = 0
    while (enemyHealth > 0 && char.health > 0 && counter < 30) {
      printer.yourHp(char)
      fightMove(char, enemies)
      enemyHealth = 0
      for (enemy <- enemies) {
        enemyHealth += enemy.health
        if (enemy.health > 0) {
          enemyHitDamage = random.nextInt(enemy.maxHitDamage - enemy.minHitDamage + 1) + enemy.minHitDamage
          char.getHit(enemyHitDamage)
          printer.youGotHit(enemyHitDamage)
        }
      }
      if (char.health == 0)
        printer.youDied(char)
      if (enemyHealth == 0){
        printer.fightEnd(char, enemies)
        var enemyExpValue = 0
        for (enemy <- enemies)
          enemyExpValue += enemy.experienceReward
        char.experience += enemyExpValue
        printer.experienceGained(enemyExpValue, char)
        char.updateStats
      }
      counter += 1
    }
  }

  def fightMove(char: Character, enemies: List[Enemies]): Unit = {
    printer.fightMove(char)
    var choice = utility.inputNumber
    var counter = 1
    var target = 1
    var spellChoice = 1
    var spellToCast = ""
    var yourHit = 0
    choice match {
      case 1 => {
        printer.fightTargets(char, enemies)
        target = utility.inputNumber
        if (target == enemies.length + 1)
          fightMove(char, enemies)
        else {
          if (enemies(target - 1).health == 0) {
            printer.alreadyDead(enemies(target - 1))
            fightMove(char, enemies)
          } else {
            yourHit = random.nextInt(char.maxHitDamage - char.minHitDamage + 1) + char.minHitDamage
            enemies(target - 1).getHit(yourHit)
            printer.youHit(yourHit)
            if (enemies(target - 1).health == 0)
              printer.enemyDied(enemies(target - 1))
          }
        }
      }
      case 2 => {
        printer.spellBook(char)
        counter = utility.inputNumber
        for (spell <- char.spellBook) {
          if (spellChoice == counter) {
            spellToCast = spell._1
          }
          spellChoice += 1
        }

        printer.fightTargets(char, enemies)
        target = utility.inputNumber
        if (target == enemies.length + 1)
          fightMove(char, enemies)
        else {
          if (enemies(target - 1).health == 0) {
            printer.alreadyDead(enemies(target - 1))
            fightMove(char, enemies)
          } else {
            try {
              spellHandler.cast(char, char.spellBook(s"$spellToCast"), enemies(target - 1))
              if (enemies(target - 1).health == 0)
                printer.enemyDied(enemies(target - 1))
            } catch {
              case e: NoSuchElementException => printer.notLearned(char.spellBook(s"$spellToCast"))
            }
          }
        }
      }
      case _ => {
        println("Wrong choice")
        fightMove(char, enemies)
      }
    }
  }
}