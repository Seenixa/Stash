package campaign.characters.playableclasses
import campaign.characters.Character
import campaign.spells.Spells
import campaign.spells.playablespells._
import campaign.spells.SpellHandler

class Warrior extends Character {

  override def getBaseStats = {
    strength = 10
    agility = 7
    intelligence = 5
    vitality = 10
    minHitDamage = strength
    maxHitDamage = strength * 2
    health = vitality * 10
    mana = intelligence * 10
    var baseSpells = List[Spells](new ShieldBash, new ShieldWall)
    var spellStats = new SpellHandler
    for (spell <- baseSpells) {
      spellStats.learnSpell(this, spell)
    }
  }
  
  
  
}