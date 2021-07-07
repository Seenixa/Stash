package main.scala.campaign.fight
import campaign.characters.Character
import campaign.enemies.Enemies
import campaign.spells.SpellHandler
import campaign.story.Game

class FightHandler(
  var setup:         Game,
  var spellHandling: SpellHandler) {

  var random = scala.util.Random

  def fight(char: Character, enemies: List[Enemies]) = {
    var counter = 0
    var enemyHealth = 1
    var enemyHitDamage = 0
    while (enemyHealth > 0 && char.health > 0 && counter < 30) {
      println(s"Your HP: ${char.health}")
      if (char.shield > 0)
        println(s"Your shield: ${char.shield}")
      fightMove(char, enemies)
      enemyHealth = 0
      for (enemy <- enemies) {
        enemyHealth += enemy.health
        if (enemy.health > 0) {
          enemyHitDamage = random.nextInt(enemy.maxHitDamage - enemy.minHitDamage + 1) + enemy.minHitDamage
          char.getHit(enemyHitDamage)
          println(s"You got hit for $enemyHitDamage")
        }
      }
      if (char.health == 0)
        println("Your character decided to lay down.")
      if (enemyHealth == 0)
        println("They're dead.. All of them.")
      counter += 1
    }
  }

  def fightMove(char: Character, enemies: List[Enemies]): Unit = {
    println(s"""|  *** Action ****
                |1. Attack  ${char.minHitDamage} - ${char.maxHitDamage} 
                |2. Skills""".stripMargin)
    var choice = setup.inputNumber
    var counter = 1
    var target = 1
    var spellChoice = 1
    var spellToCast = ""
    var yourHit = 0
    choice match {
      case 1 => {
        println("  *** Target ***")
        for (enemy <- enemies) {
          println(s"$target. ${enemy.name}  ${enemy.health} HP")
          target += 1
        }
        println(s"$target. Back")
        target = setup.inputNumber
        if (target == enemies.length + 1)
          fightMove(char, enemies)
        else {
          if (enemies(target - 1).health == 0) {
            println(s"Stop! Stop! It's already dead!")
            fightMove(char, enemies)
          } else {
            yourHit = random.nextInt(char.maxHitDamage - char.minHitDamage + 1) + char.minHitDamage
            enemies(target - 1).getHit(yourHit)
            println(s"You hit for $yourHit")
            if (enemies(target - 1).health == 0)
              println(s"And another one bites the dust.")
          }
        }
      }
      case 2 => {
        println(" *** SpellBook ***")
        for (spell <- char.spellBook) {
          println(s"$counter. ${spell._1}  ${spell._2.damage}")
          counter += 1
        }

        counter = setup.inputNumber
        for (spell <- char.spellBook) {
          if (spellChoice == counter) {
            spellToCast = spell._1
          }
          spellChoice += 1
        }

        println("  *** Target ***")
        for (enemy <- enemies) {
          println(s"$target. ${enemy.name}  ${enemy.health} HP")
          target += 1
        }
        println(s"$target. Back")
        target = setup.inputNumber
        if (target == enemies.length + 1)
          fightMove(char, enemies)
        else {
          if (enemies(target - 1).health == 0) {
            println(s"Stop! Stop! It's already dead!")
            fightMove(char, enemies)
          } else {
            try {
              spellHandling.cast(char, char.spellBook(s"$spellToCast"), enemies(target - 1))
              if (enemies(target - 1).health == 0)
                println(s"And another one bites the dust.")
            } catch {
              case e: NoSuchElementException => println("You can't cast that.")
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