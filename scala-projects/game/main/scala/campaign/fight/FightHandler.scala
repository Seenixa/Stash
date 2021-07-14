package campaign.fight
import campaign.characters._
import campaign.spells.SpellHandler
import campaign.io._

class FightHandler(
  val utility:      Utility,
  val printer:      Printer,
  val spellHandler: SpellHandler) {

  var random = scala.util.Random

  def fight(char: PlayerCharacter, enemies: List[NpcCharacter]) = {
    var counter = 0
    var enemyHealth = 1
    var enemyHitDamage = 0
    while (enemyHealth > 0 && char.health > 0 && counter < 30) {
      printer.yourHp(char)
      printer.yourMana(char)
      fightMove(char, enemies)
      enemyHealth = 0
      for (enemy <- enemies) {
        enemyHealth += enemy.health
        if (enemy.health > 0) {
          enemyHitDamage = random.nextInt(enemy.maxHitDamage - enemy.minHitDamage + 1) + enemy.minHitDamage
          enemyHitDamage = applyStatusEffects(enemy, enemyHitDamage)
          getHit(char, enemyHitDamage)
          printer.youGotHit(enemyHitDamage - char.armor)
        }
      }
      if (char.health == 0)
        printer.youDied(char)
      if (enemyHealth == 0) {
        printer.fightEnd(char, enemies)
        var enemyExpValue = 0
        for (enemy <- enemies)
          enemyExpValue += enemy.experienceReward
        char.experience += enemyExpValue
        printer.experienceGained(enemyExpValue, char)
      }
      counter += 1
    }
  }

  def fightMove(char: PlayerCharacter, enemies: List[NpcCharacter]): Unit = {
    var enemyHealth = 0
    for (enemy <- enemies)
      enemyHealth += enemy.health
    if (enemyHealth <= 0)
      return
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
            yourHit = applyStatusEffects(char, yourHit)
            getHit(enemies(target - 1), yourHit)
            printer.youHit(yourHit - enemies(target - 1).armor)
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
          if (target > enemies.length) {
            printer.notValidTarget
          } else {
            if (enemies(target - 1).health == 0) {
              printer.alreadyDead(enemies(target - 1))
              fightMove(char, enemies)
            } else {
              try {
                spellHandler.cast(char, char.spellBook(s"$spellToCast"), enemies(target - 1))
                if (char.spellBook(s"$spellToCast").manaCost > char.mana)
                  fightMove(char, enemies)
                if (enemies(target - 1).health == 0)
                  printer.enemyDied(enemies(target - 1))
              } catch {
                case _: Throwable => printer.notLearned
              }
            }
          }
        }
      }
      case 3 => {
        printer.turnSkipped
      }
      case _ => {
        printer.wrongChoice
        fightMove(char, enemies)
      }
    }
  }

  def applyStatusEffects(char: Character, damage: Int): Int = {
    var hit = damage
    if (char.burnDuration > 0) {
      pierceGetHit(char, char.maxHealth / 10)
      char.burnDuration -= 1
      if (char.getClass.getSimpleName == "NpcCharacter")
        printer.enemyBurning(char)
      else
        printer.charBurning(char)
    }
    if (char.chillDuration > 0) {
      hit = char.minHitDamage
      char.chillDuration -= 1
    }
    if (char.poisonDuration > 0) {
      pierceGetHit(char, 10)
      char.poisonDuration -= 1
      if (char.getClass.getSimpleName == "NpcCharacter")
        printer.enemyPoisoned(char)
      else
        printer.charPoisoned(char)
    }
    if (char.stunDuration > 0) {
      hit = 0
      char.stunDuration -= 1
      if (char.getClass.getSimpleName == "NpcCharacter")
        printer.enemyStunned(char)
      else
        printer.charStunned(char)
    }
    hit
  }

  def getHit(char: Character, amount: Int) = {
    var shielded = 0
    var hit = amount - char.armor
    if (char.shield > 0) {
      if (amount > 0 && hit >= char.shield) {
        char.shield = 0
        shielded = hit - char.shield
      }
      if (amount > 0 && hit < char.shield)
        char.shield -= hit
      if (amount > 0 && hit <= 0) {
        char.shield -= 1
        shielded = 1
      }
    }
    if (char.shield == 0) {
      if (amount > 0 && hit - shielded >= char.health)
        char.health = 0
      if (amount > 0 && hit - shielded < char.health)
        char.health -= (hit - shielded)
      if (amount > 0 && hit <= 0 && shielded != 1)
        char.health -= 1
    }
  }

  def pierceGetHit(char: Character, amount: Int) = {
    if (amount > 0 && amount >= char.health)
      char.health = 0
    if (amount > 0 && amount < char.health)
      char.health -= amount
  }

}