object damagetest extends App{
  import scala.collection.mutable.ArrayBuffer
  val random = scala.util.Random
    
  class character(
    val level :Int = 0,
    val ranged :Boolean = false,
    val attackDamage :Float = 0,
    val abilityPower :Float = 0,
    var health :Float = 0,
    val armor :Float = 0,
    val magicResist :Float = 0,
    val flatArmorPenetration :Float = 0,
    val percentArmorPenetration :Float = 0,
    val flatMagicPenetration :Float = 0,
    val percentMagicPenetration :Float = 0,
    val attacksPerSecond :Float = 0,
    val criticalStrikeChance :Float = 0,
    val criticalStrikeDamage :Float = 0,
    val mods :modifiers = new modifiers(
      botrk = false,
      nashorsTooth = false,
      witsEnd = false,
      guinsoosRageblade = false,
      krakenSlayer = false
      )
    ) 
  {
    def gethit( amount :Float) :Unit ={
      this.health -= amount
    }
    
    def damageOfAttack :Float ={
      val rand = random.nextFloat()
      var damage = this.attackDamage
      if( rand < this.criticalStrikeChance / 100F)
        damage *= ( this.criticalStrikeDamage / 100F)
      damage
    }
    
    def onHitMagicDamage :Float ={
      val nashorsToothDamage = abilityPower * 0.2F + 15
      val witsEndDamage = level * 3.8F + 15
      var magicDamage = 0F 
      if( this.mods.nashorsTooth)
        magicDamage += nashorsToothDamage
      if( this.mods.witsEnd)
        magicDamage += witsEndDamage
      magicDamage  
    }
    
    def onHitPhysicalDamage( opponent :character) :Float ={
      var borkDamage = 0F
      var physicalDamage = 0F
      if( this.mods.botrk){
        if( this.ranged)
          borkDamage = opponent.health * 0.06F
        if( this.ranged == false)
          borkDamage = opponent.health * 0.1F
        physicalDamage += borkDamage
      }
      val guinsooDamage = this.criticalStrikeChance * 2
      if( this.mods.guinsoosRageblade)
        physicalDamage += guinsooDamage
      physicalDamage
    }
    
    def realDamageOfAttack( opponent :character, numberOfHit :Int) :Float ={
      val physicalDamageReduction = 100 / ( 100 + ( opponent.armor * (( 100 - this.percentArmorPenetration) / 100)) - this.flatArmorPenetration)
      val magicDamageReduction = 100 / ( 100 + ( opponent.magicResist * (( 100 - this.percentMagicPenetration) / 100)) - this.flatMagicPenetration)
      var damage = 0F
      damage = (damageOfAttack + onHitPhysicalDamage( opponent)) * physicalDamageReduction +
                onHitMagicDamage * magicDamageReduction
      if( this.mods.guinsoosRageblade && ( numberOfHit % 3 == 0)){
        damage += onHitPhysicalDamage(opponent) * physicalDamageReduction + 
                  onHitMagicDamage * magicDamageReduction
      }
      if( numberOfHit % 3 == 0 && this.mods.krakenSlayer)
        damage += 60 + (0.45F * this.attackDamage)
      damage
    }
    
    def fight( opponent :character) :ArrayBuffer[Float] ={
      var timesHit = 0
      var thisHit = 0F
      var hits = ArrayBuffer[Float]()
      while( opponent.health > 0){
        timesHit += 1
        thisHit = realDamageOfAttack( opponent, timesHit)
        hits += thisHit
        opponent.gethit(thisHit)
      }
      hits
    }
  }
  
  class modifiers(
    val botrk :Boolean = false,
    val nashorsTooth :Boolean = false,
    val witsEnd :Boolean = false,
    val guinsoosRageblade :Boolean = false,
    val krakenSlayer :Boolean = false
  )
  
  val mychamp = new character(     
    level = 10,
    ranged = false,
    attackDamage = 200,
    abilityPower = 100,
    flatArmorPenetration = 0,
    percentArmorPenetration = 0,
    flatMagicPenetration = 0,
    percentMagicPenetration = 0,
    attacksPerSecond = 1.5F,
    criticalStrikeChance = 100,
    criticalStrikeDamage = 200,
    mods = new modifiers(
      botrk = true,
      nashorsTooth = false,
      witsEnd = false,
      guinsoosRageblade = true,
      krakenSlayer = true
    )
  )
  
  val targetChamp = new character( 
    health = 4000,
    armor = 300,
    magicResist = 0,
  )
  
  def printFight :Unit ={
    val targetHealthAtStart = targetChamp.health
    val theFight = mychamp.fight(targetChamp)
    val numberOfHits = theFight.length
    val fightLengthSeconds = numberOfHits / mychamp.attacksPerSecond
    val damagePerSecond = targetHealthAtStart / fightLengthSeconds
    for( hits <- theFight){
      println(s"${hits.toInt}")
    }
    println(s"""The number of hits needed to kill: $numberOfHits
               |Your average DPS in the fight was: $damagePerSecond""".stripMargin)
  }
  
  printFight
}
