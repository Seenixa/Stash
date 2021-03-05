object game extends App{
  import scala.collection.mutable.ArrayBuffer
  val random = scala.util.Random
  
  class character(
    var level :Int = 1,
    var damage :Int = 0,
    var armor :Float = 0,
    var speed :Int = 0,
    var maxHealth :Int = 500,
    var health :Int = 0,
    var maxMana :Int = 0,
    var currentMana :Int = 0,
    )
  {
    def basicAttackDamage :Int ={
      var hit = damage + random.nextInt(damage + (damage / 5))
      if( random.nextFloat() < 0.25F)
      hit *= 2
      hit
    }
    
    def getHit( amount :Int) ={
      health -= amount
    }
    
  }
  
  class enemy(
    var level :Int = 1,
    var health :Int = 0,
    var damage :Int = 0,
    var armor :Float = 0,
    var speed :Int = 0,
    var abilityDamage :Int = 0
  )
  
  class mage(
    var fireball :Int = 0,
    var frostbolt :Int = 0,
    var arcaneBlast :Int = 0,
    var earthFist :Int = 0,
    var stats :character = new character(
      damage = 2,
      armor = 5,
      speed = 3
    )
  ){
    fireball = stats.level * 5
    frostbolt = stats.level * 4
    arcaneBlast = 10 + stats.level * 3
    earthFist = stats.damage * 3      
    }
  
    
  class warrior(
    var shieldBash :Int = 0,
    var shieldBlock :Int = 0,
    var stats :character = new character(
      damage = 3,
      armor = 10,
      speed = 1
    )
  ){
    
  }
  
  class rogue(
    var abilityDamage :Int = 0,
    var abilityTwoDamage :Int = 0,
    var stats :character = new character(
      damage = 4,
      armor = 6,
      speed = 6
    )
  ){
    
    
  }
  
  class statusEffect(
    var poison :Boolean = false,
    var chill :Boolean = false,
    var burn :Boolean = false
  ){

  }
}
  
  
  
    
  
