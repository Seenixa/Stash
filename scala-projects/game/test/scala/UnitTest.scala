package campaign

class UnitTest {
  var yourCharacter = new Character
  println("Choose your character class.")
  println("1. Warrior")
  println("2. Rogue")
  println("3. Mage")
  var characterChoice = 1 // scala.io.StdIn.readInt()
  yourCharacter = yourCharacter.chooseCharacterClass(characterChoice)

  yourCharacter.spendSkillPoint("strength")
  yourCharacter.spendSkillPoint("wasdsa")

  yourCharacter.experience += 300
  yourCharacter.levelUp
  yourCharacter.spendSkillPoint("awad")
  yourCharacter.spendSkillPoint("strength")
  println(yourCharacter)

  var enemy1 = new Enemies(name = "Akarmi", health = 300, minHitDamage = 1, maxHitDamage = 5)
  var enemy2 = new Enemies(name = "Akarmi", health = 300, minHitDamage = 1, maxHitDamage = 5)
  var enemy = new Enemies(name = "Akarmi", health = 400, minHitDamage = 1, maxHitDamage = 5)
  var enemyList = List(enemy)
  
  yourCharacter.agility = 150
  yourCharacter.learnSpell(new Ambush)

  
  yourCharacter.fight(enemyList)

  /*  try {
    yourCharacter.spellBook("Ambush").cast(enemy)
  } catch {
    case e: NoSuchElementException => {
      println("You have not learned that spell.")
    }
  }*/

  println(enemy.health)

  println(yourCharacter.spellBook)

}