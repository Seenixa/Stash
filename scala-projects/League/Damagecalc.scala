object damagetest extends App{
  import scala.collection.mutable.ArrayBuffer
  
  class character(
    val level :Int = 0,
    val ranged :Boolean = false,
    val attackDamage :Float = 0,
    val abilityPower :Float = 0,
    val health :Float = 0,
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
  
  class modifiers(
    val botrk :Boolean = false,
    val nashorsTooth :Boolean = false,
    val witsEnd :Boolean = false,
    val guinsoosRageblade :Boolean = false,
    val krakenSlayer :Boolean = false
  )
  
  def averageDamagePerHit( champion :character, targetArmor :Float, targetMagicResist :Float, targetHealth :Float) :Float ={
    val physicalDamageReduction = 100 / (100 + (targetArmor * ((100 - champion.percentArmorPenetration) / 100)) - champion.flatArmorPenetration)
    val magicDamageReduction = 100 / (100 + (targetMagicResist * ((100 - champion.percentMagicPenetration) / 100)) - champion.flatMagicPenetration)
    var damage = champion.attackDamage * (1 + ((champion.criticalStrikeChance / 100)) * ( champion.criticalStrikeDamage / 100)) * physicalDamageReduction
    val witsEndDamage = (( champion.level * 3.8F) + 15) * magicDamageReduction
    val nashorsToothDamage = (( champion.abilityPower * 0.2F) + 15) * magicDamageReduction
    val guinsooOnHitDamage = (champion.criticalStrikeChance * 2) * physicalDamageReduction
    val krakenSlayerDamage = (60 + (0.45F * champion.attackDamage)) / 3
    var borkDamage = 0F
    if( champion.mods.botrk == true){
      if( champion.ranged == true)
        borkDamage = ( targetHealth * 0.06F) * physicalDamageReduction
      else
        borkDamage = ( targetHealth * 0.1F) * physicalDamageReduction
      damage += borkDamage
    }
    if( champion.mods.krakenSlayer == true)
      damage += krakenSlayerDamage
    if( champion.mods.nashorsTooth == true)
      damage += nashorsToothDamage
    if( champion.mods.witsEnd == true)
      damage += witsEndDamage
    if( champion.mods.guinsoosRageblade == true){
      damage = guinsooOnHitDamage + ( guinsooOnHitDamage / 3) + (champion.attackDamage * physicalDamageReduction)
      if( champion.mods.nashorsTooth == true)
        damage += nashorsToothDamage + ( nashorsToothDamage / 3)
      if( champion.mods.witsEnd == true)
        damage += witsEndDamage + ( witsEndDamage / 3)
      if( champion.mods.botrk == true)
        damage += (borkDamage + ( borkDamage / 3)).toFloat
      if( champion.mods.krakenSlayer == true)
        damage += krakenSlayerDamage + ( krakenSlayerDamage / 3)
        
    }  
    damage 
  }
  
  def autoAttackDamagePerSecond( champion :character, target :character) :Float ={
    val physicalDamageReduction = 100 / (100 + (target.armor * ((100 - champion.percentArmorPenetration) / 100)) - champion.flatArmorPenetration)
    var borkDamageMax = 0F
    var borkDamageAverage = 0F
    val singleHit = averageDamagePerHit( champion, target.armor, target.magicResist, target.health / 2)
    var damagePerSec = champion.attacksPerSecond * singleHit
    damagePerSec
  }
  
  def damageUntilKill( champion : character, target :character) :ArrayBuffer[Float] ={
    var thisHit = 0F
    var damageSoFar = 0F
    var hits = ArrayBuffer[Float]()
    var remainingHealth = target.health
    while( damageSoFar < target.health){
      thisHit = averageDamagePerHit( champion, target.armor, target.magicResist, remainingHealth) * 1.15F
      damageSoFar += thisHit
      remainingHealth = target.health - damageSoFar
      hits += thisHit
    }
    hits
  }
  
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
    criticalStrikeChance = 60,
    criticalStrikeDamage = 100,
    mods = new modifiers(
      botrk = true,
      nashorsTooth = false,
      witsEnd = false,
      guinsoosRageblade = true,
      krakenSlayer = false
      )
    )
  
  val targetChamp = new character( 
    health = 4000,
    armor = 300,
    magicResist = 0,
    )
  println(s"""AA damage: ${averageDamagePerHit(mychamp, targetChamp.armor, targetChamp.magicResist, targetChamp.health).toInt}
              |DPS: ${autoAttackDamagePerSecond(mychamp, targetChamp).toInt}""".stripMargin)
  var numberOfHits = 0
  for( hits <- damageUntilKill(mychamp, targetChamp)){
    numberOfHits += 1
    println(s"Damage on hit: ${hits.toInt}")
  }
  println(s"Number of hits to kill: $numberOfHits")
  val timeUntilKill = numberOfHits / mychamp.attacksPerSecond
  println(s"Time it takes to kill: ${(timeUntilKill * 10).toInt / 10F}  seconds")
}






