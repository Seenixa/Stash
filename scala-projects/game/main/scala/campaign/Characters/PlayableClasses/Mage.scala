package campaign.characters.playableclasses
import campaign.characters.Character
import campaign.spells.SpellHandler
import campaign.spells.Spells
import campaign.spells.playablespells._

class Mage extends Character {

  override def getBaseStats = {
    strength = 5
    agility = 7
    intelligence = 10
    vitality = 5
    minHitDamage = intelligence / 2
    maxHitDamage = intelligence
    maxHealth = vitality * 10
    health = maxHealth
    maxMana = intelligence * 10
    mana = maxMana
    var baseSpells = List[Spells](new Fireball, new Frostbolt)
    var spellStats = new SpellHandler
    for (spell <- baseSpells) {
      spellStats.learnSpell(this, spell)
    }
  }
  
  override def updateStats = {
    minHitDamage = intelligence / 2
    maxHitDamage = intelligence
    maxHealth = vitality * 10
    health = maxHealth
    maxMana = intelligence * 10
    mana = maxMana
    for (spell <- spellBook)
      spell._2.updateValues(this)
  }

}