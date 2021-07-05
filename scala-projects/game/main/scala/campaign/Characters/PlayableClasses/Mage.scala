package campaign

class Mage extends Character {

  override def getBaseStats = {
    strength = 5
    agility = 7
    intelligence = 10
    vitality = 5
    minHitDamage = intelligence / 2
    maxHitDamage = intelligence
    health = vitality * 10
    mana = intelligence * 10
    var baseSpells = List[Spells](new Fireball, new Frostbolt)
    for (spell <- baseSpells)
      learnSpell(spell)
  }
  
}