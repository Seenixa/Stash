package campaign.io

import campaign.spells.Spells
import campaign.characters.Character
import campaign.enemies.Enemies

class Printer {

  def casting(spell: Spells) = {
    println(s"Casting ${spell.name}")
  }

  def notLearned(spell: Spells) = {
    println(s"Your character has not learned ${spell.name} yet.")
  }

  def spellHit(spell: Spells) = {
    println(s"You hit for ${spell.damage}")
  }

  def yourHp(char: Character) = {
    println(s"Your HP: ${char.health}/${char.maxHealth}")
    if (char.shield > 0)
      println(s"Your shield: ${char.shield}")
  }

  def youHit(damage: Int) = {
    println(s"You hit for $damage")
  }

  def youGotHit(damage: Int) = {
    println(s"You got hit for $damage")
  }

  def youDied(char: Character) = {
    println(s"Mission failed. We'll get 'em next time.")
  }

  def fightEnd(char: Character, enemies: List[Enemies]) = {
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
  
  def fightMove(char: Character) = {
    println(s"""|  *** Action ****
                |1. Attack  ${char.minHitDamage} - ${char.maxHitDamage} 
                |2. Skills""".stripMargin)
  }

  def fightTargets(char: Character, enemies: List[Enemies]) = {
    var target = 1
    println("  *** Target ***")
    for (enemy <- enemies) {
      println(s"$target. ${enemy.name}  ${enemy.health} HP")
      target += 1
    }
    println(s"$target. back")
  }

  def alreadyDead(enemy: Enemies) = {
    println(s"Stop! STOP! It's already dead.")
  }

  def enemyDied(enemy: Enemies) = {
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

}