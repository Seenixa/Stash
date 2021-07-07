package campaign.characters.playableclasses
import campaign.characters.Character
import campaign.spells.SpellHandler
import campaign.spells.Spells
import campaign.spells.playablespells._

class Warrior extends Character {

  override def getBaseStats = {
    strength = 10
    agility = 7
    intelligence = 5
    vitality = 10
    minHitDamage = strength
    maxHitDamage = strength * 2
    maxHealth = vitality * 10
    health = maxHealth
    maxMana = intelligence * 10
    mana = maxMana
    var baseSpells = List[Spells](new ShieldBash, new ShieldWall)
    var spellStats = new SpellHandler
    for (spell <- baseSpells) {
      spellStats.learnSpell(this, spell)
    }
  }

  override def updateStats = {
    minHitDamage = strength
    maxHitDamage = strength * 2
    maxHealth = vitality * 10
    health = maxHealth
    maxMana = intelligence * 10
    mana = maxMana
    for (spell <- spellBook)
      spell._2.updateValues(this)
  }

}