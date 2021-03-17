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
    var experience = 0,
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
    
  }
  
  class warrior(
    var shieldBash :Int = 0,
    var shieldWall :Int = 0,
  ) extends character
  {
        
    override def updateValues :Unit ={
      armor = 20 
      strength = 50 + (level * 5)
      agility = 15 + (level * 3) 
      intelligence = 10 + (level * 2)
      vitality = 30 + (level * 10) 
      health = vitality * 10
      hitDamage = strength * 2
      shieldBash = strength + armor
      shieldWall = (health * 0.25).toInt
    }
    
    override def toString = s"""level:        $level
                               |class:        warrior
                               |strength:     $strength
                               |agility:      $agility
                               |intelligence: $intelligence
                               |vitality:     $vitality
                               |health:       $health""".stripMargin
  }
  
  class rogue(
    var poisonDuration :Int = 0,
    var poisonDamage :Int = 0
  ) extends character
  {  
    
    override def updateValues :Unit ={
      armor = 10
      strength = 20 + (level * 2)
      agility = 50 + (level * 5) 
      intelligence = 10 + (level * 3)
      vitality = 20 + (level * 5)
      health = vitality * 10
      hitDamage = strength + agility
      poisonDamage = agility
      poisonDuration = ( intelligence / 5)
    }    
    
    override def toString = s"""level:        $level
                               |class:        rogue
                               |strength:     $strength
                               |agility:      $agility
                               |intelligence: $intelligence
                               |vitality:     $vitality
                               |health:       $health""".stripMargin 
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
      strength = 10 + (level * 2)
      agility = 20 + (level * 2)
      intelligence = 50 + (level * 5)
      vitality = 15 + (level * 4) 
      health = vitality * 10
      hitDamage = intelligence
      fireballDamage = intelligence * 5
      frostboltDamage = intelligence * 3
      fireballBurnDuration = ( intelligence / 10)
      frostboltChillDuration = ( intelligence / 10)
    }
    
    override def toString = s"""level:        $level
                               |class:        mage
                               |strength:     $strength
                               |agility:      $agility
                               |intelligence: $intelligence
                               |vitality:     $vitality
                               |health:       $health""".stripMargin    
  }
  
  class enemy(
    val name :String,
    val level :Int = 0
    val hitDamage :Int = 0,
    val armor :Int = 0,
    val health :Int = 0
  ){
    def nextId :Int ={
      enemyIdCounter += 1
      enemyIdCounter
    }
    val id = nextId
    val expReward = level * 50
    
    override def toString = s"""Name:         $name
                               |Id:           $id
                               |Damage:       $hitDamage
                               |Health:       $health
                               |Armor:        $armor
                               |Experience:   $expReward""".stripMargin    
  }
 
  class item(
    val name :String,
    val strength :Int = 0,
    val agility :Int = 0,
    val intelligence :Int = 0,
    val vitality :Int = 0,
    val armor :Int = 0
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
                               |Armor:        $armor""".stripMargin 
  }

  val items = Seq(
    new item( name = "Gauntlets of Strength", strength = 10),
    new item( name = "Slippers of agility", agility = 10),
    new item( name = "Robes of the magi", intelligence = 10),
    new item( name = "Chainmail", armor = 20)
  )
  
  val enemies = Seq(
    new enemy( name = "Green slime", level = 1, hitDamage = 10, health = 100, armor = 0)
    new enemy( name = "Blue slime", level = 2, hitDamage = 20, health = 200, armor = 5)
    new enemy( name = "Red slime", level = 3, hitDamage = 30, health = 300, armor = 10)
    new enemy( name = "Black slime", level = 4, hitDamage = 40, health = 400, armor = 15)
  )
  
  def chooseYourCharacter :character ={
    println(s"""Pick your character!
                |1. Warrior
                |2. Rogue
                |3. Mage""".stripMargin)
    var choice = 1
    var yourCharacter = new character()
    if( choice == 1)
      yourCharacter = new warrior()
    if( choice == 2)
      yourCharacter = new rogue()
    if( choice == 3)
      yourCharacter = new mage()
    yourCharacter
  }

  
  val yourCharacter = chooseYourCharacter
  yourCharacter.levelUp
  yourCharacter.updateValues
  println(s"$yourCharacter")
  yourCharacter.levelUp
  yourCharacter.updateValues
  println(s"$yourCharacter")
  yourCharacter.levelUp
  yourCharacter.updateValues
  println(s"$yourCharacter")

  
  

  
}
