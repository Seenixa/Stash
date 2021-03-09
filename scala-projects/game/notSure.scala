object game extends App{
  import scala.collection.mutable.ArrayBuffer
  val random = scala.util.Random
  
  class character(
    var level :Int = 1,
    var armor :Int = 0,
    var strength :Int = 10,
    var agility :Int = 10,
    var intelligence :Int = 10,
    var vitality :Int = 10
    )
  {
    var health = 10 * vitality
    this.health = health
  }
  
  class warrior(
    var shieldBash :Int = 0,
    var shieldWall :Int = 0
  ) extends character
  {
    var hitDamage = strength * 2
    this.hitDamage = hitDamage
    
    def updateValues :Unit = {
      shieldBash = strength + armor
      shieldWall = (health * 125) / 100
      health = vitality * 10
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
    var hitDamage = strength * 2
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
  
  var Whoever = new warrior()
  Whoever.updateValues
  Whoever.vitality = 25
  println(s"$Whoever \nThe value of shield Wall is: ${Whoever.shieldWall}")
  
  
  
  
}
