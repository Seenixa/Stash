object damagetest extends App{
  
  // flat values //
  
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
      guinsoosRageblade = false
      )
    )
  
  class modifiers(
    val botrk :Boolean = false,
    val nashorsTooth :Boolean = false,
    val witsEnd :Boolean = false,
    val guinsoosRageblade :Boolean = false 
  )
  
  def averageDamagePerHit( champion :character, target :character) :Float ={
    val physicalDamageReduction = 100 / (100 + (target.armor * ((100 - champion.percentArmorPenetration) / 100)) - champion.flatArmorPenetration)
    val magicDamageReduction = 100 / (100 + (target.magicResist * ((100 - champion.percentMagicPenetration) / 100)) - champion.flatMagicPenetration)
    var damage = champion.attackDamage * (1 + ((champion.criticalStrikeChance / 100)) * ( champion.criticalStrikeDamage / 100)) * physicalDamageReduction
    val witsEndDamage = (( champion.level * 3.8F) + 15) * magicDamageReduction
    val nashorsToothDamage = (( champion.abilityPower * 0.2F) + 15) * magicDamageReduction
    val guinsooOnHitDamage = champion.criticalStrikeChance * 2
    var borkDamage = 0F
    if( champion.mods.botrk == true){
      if( champion.ranged == true){
        borkDamage = ( target.health * 0.06F) * physicalDamageReduction
      }
      else{
        borkDamage = ( target.health * 0.1F) * physicalDamageReduction
      }
      damage += borkDamage
    }
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
      if( champion.mods.botrk == true){
          damage += (borkDamage + ( borkDamage / 3)).toFloat
      }  
    }  
    damage 
  }
  
  def autoAttackDamagePerSecond( champion :character, target :character) :Float ={
    val singleHit = averageDamagePerHit( champion, target)
    var damagePerSec = champion.attacksPerSecond * singleHit
    damagePerSec
  }
  
  
  val mychamp = new character(     
    level = 10,
    ranged = false,
    attackDamage = 100,
    abilityPower = 100,
    health = 1000,
    armor = 0,
    magicResist = 0,
    flatArmorPenetration = 0,
    percentArmorPenetration = 0,
    flatMagicPenetration = 0,
    percentMagicPenetration = 0,
    attacksPerSecond = 1.5F,
    criticalStrikeChance = 60,
    criticalStrikeDamage = 100,
    mods = new modifiers(
      botrk = true,
      nashorsTooth = true,
      witsEnd = true,
      guinsoosRageblade = false
      )
    )
  println(s"""AA damage: ${averageDamagePerHit(mychamp, mychamp).toInt}
              |DPS: ${autoAttackDamagePerSecond(mychamp, mychamp).toInt}""".stripMargin)
}
