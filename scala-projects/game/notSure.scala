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
    var experience :Int = 0,
    )
  {
    var exptoNextLevel = 100 + level * 50
    var health = 10 * vitality
    this.health = health
    
    def levelUp :Unit ={
      level += 1
    }
    
    def updateValues :Unit ={
    }
    
    def getHit(amount :Int) :Unit ={
      health -= amount
    }
    
    override def toString = s"""level:        $level
                               |class:        ${this.getClass.getSimpleName}
                               |strength:     $strength
                               |agility:      $agility
                               |intelligence: $intelligence
                               |vitality:     $vitality
                               |health:       $health
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
  
  class enemy(
    var name :String,
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
    
    override def toString = s"""Name:         $name
                               |Id:           $id
                               |Strength:     $strength
                               |Agility:      $agility
                               |Intelligence: $intelligence
                               |Vitality:     $vitality
                               |Armor:        $armor
                               |""".stripMargin 
  }
  
  val enemies = Seq(
    new enemy( name = "Green slime", level = 1, hitDamage = 10, health = 100, armor = 0),
    new enemy( name = "Blue slime", level = 2, hitDamage = 20, health = 200, armor = 5),
    new enemy( name = "Red slime", level = 3, hitDamage = 30, health = 300, armor = 10),
    new enemy( name = "Black slime", level = 4, hitDamage = 40, health = 400, armor = 15)
  )
  
  val items = Seq(
    new item( name = "Gauntlets of Strength", strength = 10),
    new item( name = "Slippers of agility", agility = 10),
    new item( name = "Robes of the magi", intelligence = 10),
    new item( name = "Chainmail", armor = 20)
  )  
  
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
  
  def fight(myChar: character, enemy: enemy) :Unit ={
    val enemyStartingHealth = enemy.health
    var choiceOne = 1
    var choiceTwo = 1
    while (myChar.health > 0 && enemy.health > 0){
      println(s"""1. Basic attack
                  |2. Skills
                  |""".stripMargin)
      choiceOne = 1
      if( choiceOne == 1){
        enemy.getHit(myChar.hitDamage)
        if( enemy.health > 0)
          myChar.getHit(enemy.hitDamage)
      }
      println(s"""The enemy's remaining health: ${enemy.health}
                  |Your remaining health: ${myChar.health}
                  |""".stripMargin)
    }
    enemy.health = enemyStartingHealth
  }
  
  def playTheGame :Unit ={
    println(s"""Pick your character!
                |1. Warrior
                |2. Rogue
                |3. Mage
                |""".stripMargin)
    val choice = 1
    val yourCharacter = chooseYourCharacter(choice)
  }
  
  val yourCharacter = chooseYourCharacter(1)
  yourCharacter.updateValues
  println(yourCharacter)
  val enemyCharacter = enemies(1)
  fight (yourCharacter, enemyCharacter)

}
