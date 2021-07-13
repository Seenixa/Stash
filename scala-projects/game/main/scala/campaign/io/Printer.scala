package main.scala.campaign.io

import campaign.spells.Spells
import campaign.characters.Character

class Printer {

  def character(char: Character) = {
    println(s"$char")
  }
  
  def cantUpgradeStats(char: Character) = {
    println(s"You can upgrade the stats on this one ${char.getClass.getSimpleName}")
  }
  
  def casting(spell: Spells) = {
    println(s"Casting ${spell.name}")
  }

  def notLearned = {
    println(s"Use spells you already know please.")
  }
  
  def notEnoughMana(spell: Spells) = {
    println(s"You don't have enough mana to cast ${spell.name}")
  }
  
  def spellHit(spell: Spells) = {
    if (spell.damage > 0)
      println(s"You hit for ${spell.damage}")
  }

  def yourHp(char: Character) = {
    println(s"Your HP: ${char.health}/${char.maxHealth}")
    if (char.shield > 0)
      println(s"Your shield: ${char.shield}")
  }
  
  def yourMana(char: Character) = {
    println(s"Your mana: ${char.mana}/${char.maxMana}")
  }

  def youHit(damage: Int) = {
    if (damage > 0)
      println(s"You hit for $damage")
  }

  def youGotHit(damage: Int) = {
    if (damage > 0)
      println(s"You got hit for $damage")
  }

  def youDied(char: Character) = {
    println(s"Mission failed. We'll get 'em next time.")
  }

  def fightEnd(char: Character, enemies: List[Character]) = {
    println(s"Let the bodies hit the FLOOOOOOOR!")
  }
  
  def experienceGained(amount: Int, char: Character) {
    println(s"You've gained $amount experience.")
  }
  
  def levelUp(char: Character) = {
    println(s"Level ${char.level} reached!")
  }

  def unspentSkillPoints(char: Character) = {
    println(s"You have ${char.unspentSkillPoints} unspent skill points.")
  }
  
  def spentSkillPoints(stat: String, times: Int) = {
    println(s"You spent $times points on $stat")
  }
  
  def fightMove(char: Character) = {
    println(s"""|  *** Action ****
                |1. Attack  ${char.minHitDamage} - ${char.maxHitDamage} 
                |2. Skills
                |3. Skip turn""".stripMargin)
  }
  
  def turnSkipped = {
    println("You've skipped your turn.")
  }

  def fightTargets(char: Character, enemies: List[Character]) = {
    var target = 1
    println("  *** Target ***")
    for (enemy <- enemies) {
      println(s"$target. ${enemy.name}  ${enemy.health} HP")
      target += 1
    }
    println(s"$target. back")
  }
  
  def enemyBurning(enemy: Character) = {
    println(s"${enemy.name} got burned for ${enemy.maxHealth / 10} ")
  }
  
  def charBurning(char: Character) = {
    println(s"You got burned for ${char.maxHealth / 10}")
  }
  
  def enemyPoisoned(enemy: Character) = {
    println(s"${enemy.name} does not feel so good.")
  }
  
  def charPoisoned(char: Character) = {
    println(s"Mr. Stark... I don't feel so good.")
  }
  
  def enemyStunned(enemy: Character) = {
    println(s"${enemy.name} felt like skipping a turn.")
  }
  
  def charStunned(char: Character) = {
    println(s"Let's... just not move okay?")
  }
  
  def notValidTarget = {
    println(s"What are you aiming at?")
  }

  def alreadyDead(enemy: Character) = {
    println(s"Stop! STOP! It's already dead.")
  }

  def enemyDied(enemy: Character) = {
    println(s"And another one bites the dust.")
  }

  def spellBook(char: Character) = {
    var counter = 1
    println(" *** SpellBook ***")
    for (spell <- char.spellBook) {
      println(s"$counter. ${spell._1}  ${spell._2.damage}")
      counter += 1
    }
  }

  def characterChoice = {
    println("Choose your character class.")
    println("1. Warrior")
    println("2. Rogue")
    println("3. Mage")
  }

  def wrongChoice = {
    println("Choice doesn't exist, pick again.")
  }
  
  def wrongInputNumber = {
    println("Try inputting a number this time")
  }
  
  def quit = {
    println("To quit the game choose '9'.")
  }
  
  def notYet = {
    println("Is not in the program yet")
  }
  
  def testMenuOne = {
    println(s"""|1. Setup stats for your Character.
                |2. Setup stats for an enemy.
                |3. Setup multiple Enemies.
                |4. Fight single enemy. (if setup with option "2")
                |5. Fight group of enemies. (if setup with option "3")
                |9. Quit""".stripMargin)
  }

}