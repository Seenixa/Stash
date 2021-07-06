package campaign.test
import campaign.characters.Character
import campaign.enemies.Enemies
import main.scala.campaign.fight.FightHandler
import campaign.story.Game

class UnitTest {

  var setup = new Game
  var fight = new FightHandler

  var yourCharacter = new Character
  println("Choose your character class.")
  println("1. Warrior")
  println("2. Rogue")
  println("3. Mage")

  val charChoice = scala.io.StdIn.readInt()
  yourCharacter = setup.chooseCharacterClass(charChoice)
  val enemy = new Enemies(health = 300, minHitDamage = 10, maxHitDamage = 30)
  val enemies = List(enemy)
  fight.fight(yourCharacter, enemies)
  println(yourCharacter)
  println(yourCharacter.spellBook)

}