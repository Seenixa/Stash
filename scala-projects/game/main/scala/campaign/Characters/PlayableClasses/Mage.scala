package campaign.characters.playableclasses
import campaign.characters.Character
import campaign.spells.SpellHandler
import campaign.spells.Spells
import campaign.spells.playablespells._
import campaign.io.Printer

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
    for (spell <- baseSpells) {
      spellHandler.learnSpell(this, spell)
    }
  }
  
  override def updateStats = {
    stats.levelUp(this)
    minHitDamage = intelligence / 2
    maxHitDamage = intelligence
    maxMana = intelligence * 10
    maxHealth = vitality * 10
    for (spell <- spellBook)
      spell._2.updateValues(this)
  }

}