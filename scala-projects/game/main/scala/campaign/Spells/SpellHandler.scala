package campaign.spells
import campaign.characters._
import campaign.io.Printer

class SpellHandler(
  val printer: Printer) {

  def cast(caster: PlayerCharacter, spell: Spells, target: NpcCharacter): Int = {
    if (caster.stunDuration <= 0) {
      if (!caster.spellBook.contains(spell.name))
        printer.notLearned
      if (spell.manaCost > caster.mana)
        printer.notEnoughMana(spell)
      if (caster.spellBook.contains(spell.name) && spell.manaCost <= caster.mana) {
        printer.casting(spell)
        if (spell.damage > 0) {
          printer.spellHit(spell)
          target.health -= spell.damage
        }
        target.burnDuration += spell.burnDuration
        target.chillDuration += spell.chillDuration
        target.poisonDuration += spell.poisonDuration
        target.stunDuration += spell.stunDuration
        caster.shield += spell.shielding
        caster.mana -= spell.manaCost
        if (caster.health + spell.healing >= caster.maxHealth)
          caster.health = caster.maxHealth
        else caster.health += spell.healing
      }
    }
    spell.damage
  }

  def learnSpell(char: PlayerCharacter, spell: Spells) = {
    char.spellBook += (spell.name -> spell)
    spell.updateValues(char)
  }

}