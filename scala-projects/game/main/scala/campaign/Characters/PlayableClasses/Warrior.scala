package campaign.characters.playableclasses
import campaign.characters.Character
import campaign.spells.SpellHandler
import campaign.spells.Spells
import campaign.spells.playablespells._
import campaign.io.Printer

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
    for (spell <- baseSpells) {
      spellHandler.learnSpell(this, spell)
    }
  }

  override def updateStats = {
    stats.levelUp(this)
    minHitDamage = strength
    maxHitDamage = strength * 2
    maxMana = intelligence * 10
    maxHealth = vitality * 10
    for (spell <- spellBook)
      spell._2.updateValues(this)
  }

}