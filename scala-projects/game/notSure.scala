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
      this.agility = 20 + (level * 3) + itemValues.agility
      this.intelligence = 10 + (level * 2) + itemValues.intelligence
      this.vitality = 30 + (level * 10) + itemValues.vitality
      shieldBash = strength + armor
      health = vitality * 10
      shieldWall = (health * 0.25).toInt
      hitDamage = strength * 2
    }
    
    override def toString = s"""level:        $level
                               |strength:     $strength
                               |agility:      $agility
                               |intelligence: $intelligence
                               |vitality:     $vitality
                               |health:       $health""".stripMargin
    
  }
  
  class rogue(
    var poisonDuration :Int,
    var poisonDamage :Int
  ) extends character
  {
    var hitDamage = strength + agility
    this.hitDamage = hitDamage    
    
    
    def updateValues :Unit = {
      poisonDamage = agility
      poisonDuration = agility / 10
      health = vitality * 10
      hitDamage = strength + agility
    }    
    
    override def toString = s"""level:        $level
                               |strength:     $strength
                               |agility:      $agility
                               |intelligence: $intelligence
                               |vitality:     $vitality
                               |health:       $health""".stripMargin 
    
  }
  
  class mage(
    var fireballDamage :Int,
    var fireballBurnDuration :Int,
    var frostboltDamage :Int,
    var frostboltChillDuration :Int
  ) extends character
  {
    var hitDamage = intelligence
    this.hitDamage = hitDamage
    
    def updateValues :Unit = {
      fireballDamage = intelligence * 5
      frostboltDamage = intelligence * 3
      fireballBurnDuration = intelligence / 10
      frostboltChillDuration = intelligence / 10
      health = vitality * 10
      hitDamage = intelligence
    }  
    
    override def toString = s"""level:        $level
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
    def addItem( itemId : Int, char: character) :Unit ={
      itemId match{
        case 1 => char.itemValues.strength += 10
        case 2 => char.itemValues.agility += 10
        case 3 => char.itemValues.intelligence += 10
        case 4 => char.itemValues.vitality += 10
        case 5 => char.itemValues.armor += 10
        case _ => println("Dude wtf?")
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
  
  val whatever = new item(1)
  var Whoever = new warrior()
  whatever.addItem( whatever.id, Whoever)
  Whoever.updateStats(level = 10)
  
  
  println(s"$Whoever \nThe value of shield Wall is: ${Whoever.shieldWall}")
  
  
  
  
}
