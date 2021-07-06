package main.scala.campaign.fight
import campaign.characters.Character
import campaign.enemies.Enemies
import campaign.spells._

class FightHandler {

  var spellHandling = new SpellHandler
  var random = scala.util.Random

  def fight(char: Character, enemies: List[Enemies]) = {
    var counter = 0
    var enemyHealth = 1
    while (enemyHealth > 0 && char.health > 0 && counter < 30) {
      println(s"Your HP: ${char.health}")
      fightMove(char, enemies)
      enemyHealth = 0
      for (enemy <- enemies) {
        enemyHealth += enemy.health
        char.getHit(random.nextInt(random.nextInt(char.maxHitDamage - char.minHitDamage + 1)
          + char.minHitDamage))
      }
      counter += 1
    }
  }

  def fightMove(char: Character, enemies: List[Enemies]): Unit = {
    println(s"""|  *** Action ****
                |1. Attack
                |2. Skills""".stripMargin)
    var choice = scala.io.StdIn.readInt()
    var counter = 1
    var target = 1
    var spellChoice = 1
    var spellToCast = ""
    choice match {
      case 1 => {
        println("  *** Target ***")
        for (enemy <- enemies) {
          println(s"$target. ${enemy.name}  ${enemy.health} HP")
          target += 1
        }
        target = scala.io.StdIn.readInt()
        enemies(target - 1).getHit(random.nextInt(char.maxHitDamage - char.minHitDamage + 1)
          + char.minHitDamage)
      }
      case 2 => {
        println(" *** SpellBook ***")
        for (spell <- char.spellBook) {
          println(s"$counter. ${spell._1}  ${spell._2.damage}")
          counter += 1
        }

        counter = scala.io.StdIn.readInt()
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
        target = scala.io.StdIn.readInt()
        try {
          spellHandling.cast(char, char.spellBook(s"$spellToCast"), enemies(target - 1))
        } catch {
          case e: NoSuchElementException => println("You can't cast that.")
        }
      }
      case _ => {
        println("Wrong choice")
        fightMove(char, enemies)
      }
    }
  }
}