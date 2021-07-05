package campaign

class Rogue extends Character {

  override def getBaseStats = {
    strength = 5
    agility = 10
    intelligence = 7
    vitality = 10
    minHitDamage = (strength + agility) / 2
    maxHitDamage = strength + agility
    health = vitality * 10
    mana = intelligence * 10
    var baseSpells = List[Spells](new Ambush, new PoisonShot)
    for (spell <- baseSpells){
      learnSpell(spell)
      
    }
  }

  
}