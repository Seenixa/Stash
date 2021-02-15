object damagetest extends App{
    
  class character(
    val level :Int = 0,
    val ranged :Boolean = false,
    val attackDamage :Float = 0,
    val abilityPower :Float = 0,
    val health :Float = 0,
    val armor :Float = 0,
    val magicResist :Float = 0,
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
    var damage = champion.attackDamage * (1 +((champion.criticalStrikeChance / 100)) * ( champion.criticalStrikeDamage / 100))
    println(s"""$damage
            |
            |""".stripMargin)
    if( champion.mods.botrk == true){
      if( champion.ranged == true)
        damage += ( target.health * 0.06).toFloat
      if( champion.ranged == false)
        damage += ( target.health * 0.1).toFloat
    }
    if( champion.mods.nashorsTooth == true)
      damage += ( champion.abilityPower * 0.2).toFloat + 15
    if( champion.mods.witsEnd == true)
      damage += (( champion.level * 3.8).toFloat + 15)
    if( champion.mods.guinsoosRageblade == true){
      damage = champion.attackDamage + ( champion.criticalStrikeChance * 2) + ((champion.criticalStrikeChance * 2) / 3)
      if( champion.mods.nashorsTooth == true)
        damage += (( champion.abilityPower * 0.2) + 15).toFloat + ((( champion.abilityPower * 0.2) + 15) / 3).toFloat
      if( champion.mods.witsEnd == true)
        damage += (( champion.level * 3.8) + 15).toFloat + ((( champion.level * 3.8) + 15) / 3).toFloat
      if( champion.mods.botrk == true){
        if( champion.ranged == true)
          damage += ( target.health * 0.06).toFloat + ((( target.health * 0.06) / 3).toFloat)
        if( champion.ranged == false)
          damage += ( target.health * 0.1).toFloat + ((( target.health * 0.1) / 3).toFloat)
      }  
    }  
    damage 
  }
  
  val mychamp = new character(     
    level = 10,
    ranged = false,
    attackDamage = 100,
    abilityPower = 100,
    health = 1000,
    armor = 10,
    magicResist = 0,
    attacksPerSecond = 0,
    criticalStrikeChance = 50,
    criticalStrikeDamage = 100,
    mods = new modifiers(
      botrk = false,
      nashorsTooth = false,
      witsEnd = false,
      guinsoosRageblade = true
      )
    )
  println(s"${averageDamagePerHit(mychamp, mychamp)}")
}
