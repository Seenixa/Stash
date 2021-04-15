object game extends App{
  import scala.collection.mutable.ArrayBuffer
  val random = scala.util.Random
  var itemIdCounter = 0
  var enemyIdCounter = 0
  
  class character(
    var level :Int = 1,
    var armor :Int = 0,
    var strength :Int = 0,
    var agility :Int = 0,
    var intelligence :Int = 0,
    var vitality :Int = 0,
    var hitDamage :Int = 0,
    var health :Int = 0,
    var experience :Int = 0,
    )
  {
    var exptoNextLevel = 100 + level * 50
    health = 10 * vitality
    var items = ArrayBuffer[item]()
    
    def levelUp :Unit ={
      level += 1
      updateValues
    }
    
    def updateValues :Unit ={
    }
    
    def getHit(amount :Int) :Unit ={
      health -= amount
    }
    
    def fight(enemy: enemies) :Unit ={
      var choiceOne = 1
      var choiceTwo = 1
      while (health > 0 && enemy.health > 0){
        println(s"""1. Basic attack
                    |2. Skills
                    |""".stripMargin)
        choiceOne = 2 // scala.io.StdIn.readInt()
        if( choiceOne == 1)
          enemy.getHit(hitDamage)
        else if( choiceOne == 2){
          if( this.getClass.getSimpleName == "warrior"){
            println(s"""1. Shield Wall
                       |2. Shield Bash""".stripMargin)
            if( choiceTwo == 1)
              
          }
            
        }
        if( enemy.health > 0)
          this.getHit(enemy.hitDamage)
        if( enemy.health > 0 && health > 0)
          println(s"""Your remaining health: $health
                      |The enemy's remaining health: ${enemy.health}
                      |""".stripMargin)
        else if( enemy.health < 0)
          println(s"""Your remaining health: $health
                      |The enemy has been defeated.""".stripMargin)
        if( health <= 0)
          println(s"""You have been defeated.
                      |The enemy's remaining health: ${enemy.health}""".stripMargin)

        
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
                               |""".stripMargin  
  }
  
  class warrior(
    var shieldBash :Int = 0,
    var shieldWall :Int = 0,
  ) extends character
  {    
    override def updateValues :Unit ={
      armor = 20 
      strength = 20 + (level * 5)
      agility = 10 + (level * 3) 
      intelligence = 5 + (level * 2)
      vitality = 30 + (level * 10) 
      health = vitality * 10
      hitDamage = strength * 2
      shieldBash = strength + armor
      shieldWall = (health * 0.25).toInt
    }
  }
  
  class rogue(
    var poisonDuration :Int = 0,
    var poisonDamage :Int = 0
  ) extends character
  {  
    override def updateValues :Unit ={
      armor = 10
      strength = 10 + (level * 2)
      agility = 20 + (level * 5) 
      intelligence = 5 + (level * 3)
      vitality = 20 + (level * 5)
      health = vitality * 10
      hitDamage = strength + agility
      poisonDamage = agility
      poisonDuration = ( intelligence / 5)
    }    
  }
  
  class mage(
    var fireballDamage :Int = 0,
    var fireballBurnDuration :Int = 0,
    var frostboltDamage :Int = 0,
    var frostboltChillDuration :Int = 0
  ) extends character
  {
    override def updateValues :Unit ={
      armor = 5
      strength = 5 + (level * 2)
      agility = 10 + (level * 2)
      intelligence = 20 + (level * 5)
      vitality = 15 + (level * 4) 
      health = vitality * 10
      hitDamage = intelligence
      fireballDamage = intelligence * 5
      frostboltDamage = intelligence * 3
      fireballBurnDuration = ( intelligence / 10)
      frostboltChillDuration = ( intelligence / 10)
    }   
  }
  
  class enemies(
    var name :String = "",
    var level :Int = 0,
    var hitDamage :Int = 0,
    var armor :Int = 0,
    var health :Int = 0,
    var typeId :Int = 0
  ){
    def nextId :Int ={
      enemyIdCounter += 1
      enemyIdCounter
    }
    val id = nextId
    
    override def toString = s"""Name:         $name
                               |Id:           $id
                               |Damage:       $hitDamage
                               |Health:       $health
                               |Armor:        $armor
                               |""".stripMargin
    
    def getHit(amount :Int) :Unit ={
      health -= amount
    }   
  }
  
  class greenSlime() extends enemies
  {
    name = "Green Slime"
    level = 1
    hitDamage = 10
    armor = 0
    health = 100
    typeId = 1
  }
  
  class blueSlime() extends enemies
  {
    name = "Blue Slime"
    level = 2
    hitDamage = 20
    armor = 5
    health = 200
    typeId = 2
  }

  class redSlime() extends enemies
  {
    name = "Red Slime"
    level = 3
    hitDamage = 30
    armor = 10
    health = 300
    typeId = 3
  }
  
  class blackSlime() extends enemies
  {
    name = "Black Slime"
    level = 4
    hitDamage = 40
    armor = 15
    health = 400
    typeId = 4
  }
 
  class item(
    var name :String = "",
    var strength :Int = 0,
    var agility :Int = 0,
    var intelligence :Int = 0,
    var vitality :Int = 0,
    var armor :Int = 0,
    var typeId :Int = 0
  ){
    def nextId :Int ={
      itemIdCounter += 1
      itemIdCounter
    }
    val id = nextId
    
    override def toString = 
    s"""Name:         $name
        |Id:           $id
        ${if (strength != 0) "|Strength:     " + s"$strength"; else{""} }
        ${if (agility != 0) "|Agility:      " + s"$agility"; else{""}}
        |${if (intelligence != 0) "Intelligence: " + s"$intelligence"; else {""}}
        |${if (vitality != 0) "Vitality:     " + s"$vitality"; else {""}}
        |${if (armor != 0) "Armor:        " + s"$armor" else {""}}
        |""".stripMargin 
  }
  
  class gauntletOfStrength() extends item
  {
    name = "Gauntlets of Strength"
    strength = 10
  }
  
  class slippersOfAgility() extends item
  {
    name = "Slippers of Agility"
    agility = 10
  }
  
  class robesOfTheMagi() extends item
  {
    name = "Robes of the Magi"
    intelligence = 10
  }
  
  class healthStone() extends item
  {
    name = "Healthstone"
    vitality = 10
  }
  
  class chainmail() extends item
  {
    name = "Chainmail"
    armor = 10
  }
  
  def chooseYourCharacter(choice: Int) :character ={
    var yourCharacter = new character()
    if( choice == 1)
      yourCharacter = new warrior()
    if( choice == 2)
      yourCharacter = new rogue()
    if( choice == 3)
      yourCharacter = new mage()
    yourCharacter
  }
    
  def playTheGame :Unit ={
    println(s"""Pick your character!
                |1. Warrior
                |2. Rogue
                |3. Mage
                |""".stripMargin)
    val choice = 1  // scala.io.StdIn.readInt()
    val yourCharacter = chooseYourCharacter(choice)
  }
  
  val yourCharacter = chooseYourCharacter(1)
  yourCharacter.updateValues
  println(yourCharacter)
  yourCharacter.levelUp
  println(yourCharacter)
  
  val itemList = Seq(
    new gauntletOfStrength(),
    new slippersOfAgility(),
    new robesOfTheMagi(),
    new healthStone(),
    new chainmail()
  )
  
  val monster = new blackSlime()
  yourCharacter.fight(monster)
}
