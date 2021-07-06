package campaign.characters
import campaign.spells.Spells
import campaign.items.Items
import campaign.enemies.Enemies
import campaign.characters.playableclasses._

class Character {

  var name = ""
  var level: Int = 1
  var armor: Int = 0
  var strength: Int = 0
  var agility: Int = 0
  var intelligence: Int = 0
  var vitality: Int = 0
  var unspentSkillPoints = 0
  var minHitDamage: Int = 0
  var maxHitDamage: Int = 0
  var health: Int = 0
  var experience: Int = 0
  var experienceToNextLevel = 100
  var maxMana: Int = 0
  var mana: Int = 0
  var shield: Int = 0
  var burnDuration = 0
  var poisonDuration = 0
  var chillDuration = 0
  var stunDuration = 0
  val random = scala.util.Random

  var inventory = Map[String, Items]()
  var spellBook = Map[String, Spells]()

  def getBaseStats = {
  }

  def levelUp = {
    while (experience >= experienceToNextLevel) {
      level += 1
      unspentSkillPoints += 5
      experienceToNextLevel += 100 + level * 50
    }
  }

  def chooseCharacterClass(classId: Int): Character = {
    var newChar = new Character
    if (classId > 0 && classId <= 3) {
      classId match {
        case 1 => newChar = new Warrior
        case 2 => newChar = new Rogue
        case 3 => newChar = new Mage
        case _ => throw new Error("Something's wrong, I can feel it.")
      }
    }
    newChar.getBaseStats
    newChar
  }

  def spendSkillPoint(stat: String) = {
    if (unspentSkillPoints > 0) {
      stat match {
        case "strength" =>
          unspentSkillPoints -= 1
          strength += 1
        case "agility" =>
          unspentSkillPoints -= 1
          agility += 1
        case "intelligence" =>
          unspentSkillPoints -= 1
          intelligence += 1
        case "vitality" =>
          unspentSkillPoints -= 1
          vitality += 1
        case _ => println(s"We can't apply your point to $stat")
      }
    } else println("You don't have any skill points left.")
  }

  def updateSpellValues = {
    for (spell <- spellBook) {
      if (spell._1 == "Ambush")
        spell._2.damage = agility * 2
      if (spell._1 == "Poison shot") {
        spell._2.damage = agility
        spell._2.poisonDuration = agility / 5
      }
      if (spell._1 == "Shield wall")
        spell._2.shielding = vitality * 3
      if (spell._1 == "Shield bash") {
        spell._2.damage = minHitDamage
        spell._2.stunDuration = 1
      }
      if (spell._1 == "Fireball") {
        spell._2.damage = intelligence * 2
        spell._2.burnDuration = intelligence / 3
      }
      if (spell._1 == "Frostbolt") {
        spell._2.damage = intelligence * 2
        spell._2.chillDuration = intelligence / 3
      }
    }
  }

  def learnSpell(spell: Spells) = {
    spellBook += (spell.name -> spell)
    updateSpellValues
  }

  def getHit(amount: Int) = {
    if (amount - armor > 0 && amount - armor < health)
      health -= amount - armor
    if (amount - armor > 0 && amount - armor >= health)
      health = 0
    if (amount > 0 && amount - armor < 0)
      health -= 1
  }

  def fightMove(enemies: List[Enemies]): Unit = {
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
        enemies(target - 1).getHit(random.nextInt(maxHitDamage - minHitDamage + 1)
          + minHitDamage)
      }
      case 2 => {
        println(" *** SpellBook ***")
        for (spell <- spellBook) {
          println(s"$counter. ${spell._1}  ${spell._2.damage}")
          counter += 1
        }

        counter = scala.io.StdIn.readInt()
        for (spell <- spellBook) {
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
          spellBook(s"$spellToCast").cast(enemies(target - 1))
        } catch {
          case e: NoSuchElementException => println("You can't cast that.")
        }
      }
      case _ => {
        println("Wrong choice")
        fightMove(enemies)
      }
    }
  }

  def fight(enemies: List[Enemies]) = {
    var counter = 0
    var enemyHealth = 1
    while (enemyHealth > 0 && health > 0 && counter < 30) {
      println(s"Your HP: $health")
      fightMove(enemies)
      enemyHealth = 0
      for (enemy <- enemies) {
        enemyHealth += enemy.health
        getHit(random.nextInt(random.nextInt(maxHitDamage - minHitDamage + 1)
          + minHitDamage))
      }
      counter += 1
    }
  }

  override def toString = s"""level:        $level
                             |class:        ${this.getClass.getSimpleName}
                             |strength:     $strength
                             |agility:      $agility
                             |intelligence: $intelligence
                             |vitality:     $vitality
                             |health:       $health
                             |armor:        $armor
                             |experience:   $experience
                             |""".stripMargin

}