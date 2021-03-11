object game extends App{
  import scala.collection.mutable.ArrayBuffer
  val random = scala.util.Random
  
  class character(
    var level :Int = 1,
    var armor :Int = 0,
    var strength :Int = 10,
    var agility :Int = 10,
    var intelligence :Int = 10,
    var vitality :Int = 21,
    var itemValues :itemValues = new itemValues()
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
        
    def updateStats( level :Int) :Unit ={
      this.level = level
      this.armor = 20 + itemValues.armor
      this.strength = 50 + (level * 5) + itemValues.strength
      this.agility = 15 + (level * 3) + itemValues.agility
      this.intelligence = 10 + (level * 2) + itemValues.intelligence
      this.vitality = 30 + (level * 10) + itemValues.vitality
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
      
    def updateStats( level :Int) :Unit ={
      this.level = level
      this.armor = 10 + itemValues.armor
      this.strength = 20 + (level * 2) + itemValues.strength
      this.agility = 50 + (level * 5) + itemValues.agility
      this.intelligence = 10 + (level * 3) + itemValues.intelligence
      this.vitality = 20 + (level * 5) + itemValues.vitality
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
     
    def updateStats( level :Int) :Unit ={
      this.level = level
      this.armor = 5 + itemValues.armor
      this.strength = 10 + (level * 2) + itemValues.strength
      this.agility = 20 + (level * 2) + itemValues.agility
      this.intelligence = 50 + (level * 5) + itemValues.intelligence
      this.vitality = 15 + (level * 4) + itemValues.vitality
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
    val id :Int = 0
  ) extends itemValues
  {
    var itemName :String = ""
    id match{
      case 1 => itemName = "Gauntlets of strength"
      case 2 => itemName = "Slippers of agility"
      case 3 => itemName = "Robe of the magi"
      case 4 => itemName = "Ruby crystal"
      case 5 => itemName = "Chainmail"
      case _ => println("Dude wtf?")
    }
    
    def addItem( itemId : Int, char: character) :Unit ={
      itemId match{
        case 1 => char.itemValues.strength += 10
        case 2 => char.itemValues.agility += 10
        case 3 => char.itemValues.intelligence += 10
        case 4 => char.itemValues.vitality += 10
        case 5 => char.itemValues.armor += 10
        case _ => println("Like... dude? Adding a non-exsistent item wth?")
      }
    }   
  }
  
  class itemValues(
    var strength :Int = 0,
    var agility :Int = 0,
    var intelligence :Int = 0,
    var vitality :Int = 0,
    var armor :Int = 0
  )
    
  def createWarrior :warrior ={
    val yourCharacter = new warrior()
    yourCharacter.updateStats( level = 1)
    yourCharacter
  }
  
  def createRogue :rogue ={
    val yourCharacter = new rogue()
    yourCharacter.updateStats( level = 1)
    yourCharacter
  }

  def createMage :mage ={
    val yourCharacter = new mage()
    yourCharacter.updateStats( level = 1)
    yourCharacter
  }
  
  var Ron = createWarrior
  var Don = createRogue
  var Mon = createMage
  Ron.levelUp
  Ron.updateStats(Ron.level)
  println(s"""$Ron
             |
             |$Don
             |
             |$Mon""".stripMargin)
  
}

