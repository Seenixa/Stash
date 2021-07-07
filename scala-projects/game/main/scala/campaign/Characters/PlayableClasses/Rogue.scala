package campaign.characters.playableclasses
import campaign.characters.Character
import campaign.spells.SpellHandler
import campaign.spells.Spells
import campaign.spells.playablespells._

class Rogue extends Character {

  override def getBaseStats = {
    strength = 5
    agility = 10
    intelligence = 7
    vitality = 10
    minHitDamage = (strength + agility) / 2
    maxHitDamage = strength + agility
    maxHealth = vitality * 10
    health = maxHealth
    maxMana = intelligence * 10
    mana = maxMana
    var baseSpells = List[Spells](new Ambush, new PoisonShot)
    var spellStats = new SpellHandler
    for (spell <- baseSpells) {
      spellStats.learnSpell(this, spell)
    }
  }

  override def updateStats = {
    minHitDamage = (strength + agility) / 2
    maxHitDamage = strength + agility
    maxHealth = vitality * 10
    health = maxHealth
    maxMana = intelligence * 10
    mana = maxMana
    for (spell <- spellBook)
      spell._2.updateValues(this)
  }

}