object game extends App{
  import scala.collection.mutable.ArrayBuffer
  val random = scala.util.Random
  var idCounter = 0
  
  class character(
    var classId :Int = 0,
    var level :Int = 1,
    var armor :Int = 0,
    var strength :Int = 10,
    var agility :Int = 10,
    var intelligence :Int = 10,
    var vitality :Int = 21,
    )
  {
    var health = 10 * vitality
    this.health = health
    
    def levelUp :Unit ={
      this.level += 1
    } 
  }
  
  class warrior(
    var shieldBash :Int = 0,
    var shieldWall :Int = 0,
  ) extends character
  {
    var hitDamage = strength * 2
    this.hitDamage = hitDamage
    this.classId = 1
        
    def updateStats( level :Int) :Unit ={
      this.level = level
      this.armor = 20 
      this.strength = 50 + (level * 5)
      this.agility = 15 + (level * 3) 
      this.intelligence = 10 + (level * 2)
      this.vitality = 30 + (level * 10) 
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
    var hitDamage = strength + agility
    this.hitDamage = hitDamage
    this.classId = 2
      
    def updateStats( level :Int) :Unit ={
      this.level = level
      this.armor = 10
      this.strength = 20 + (level * 2)
      this.agility = 50 + (level * 5) 
      this.intelligence = 10 + (level * 3)
      this.vitality = 20 + (level * 5)
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
    var hitDamage = intelligence
    this.hitDamage = hitDamage
    this.classId = 3
     
    def updateStats( level :Int) :Unit ={
      this.level = level
      this.armor = 5
      this.strength = 10 + (level * 2)
      this.agility = 20 + (level * 2)
      this.intelligence = 50 + (level * 5)
      this.vitality = 15 + (level * 4) 
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
 
  class item(
    val name :String,
    val strength :Int = 0,
    val agility :Int = 0,
    val intelligence :Int = 0,
    val vitality :Int = 0,
    val armor :Int = 0
  ){
    def nextId :Int ={
      idCounter += 1
      idCounter
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
  
  def chooseYourCharacter :character ={
    println(s"""Pick your character!
                |1. Warrior
                |2. Rogue
                |3. Mage""".stripMargin)
    var choice = 2
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
  if( yourCharacter.classId != 0)
    yourCharacter.updateStats
  println(yourCharacter)
  
}

