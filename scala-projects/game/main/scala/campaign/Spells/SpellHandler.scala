package campaign.spells
import campaign.characters.Character
import campaign.enemies.Enemies
import campaign.io.Printer

class SpellHandler(
  val printer: Printer) {

  def cast(caster: Character, spell: Spells, target: Enemies) = {
    if (caster.stunDuration <= 0) {
      if (!caster.spellBook.contains(spell.name))
        printer.notLearned(spell)
      if (spell.manaCost > caster.mana)
        printer.notEnoughMana(spell)
      if (caster.spellBook.contains(spell.name) && spell.manaCost <= caster.mana) {
        printer.casting(spell)
        if (spell.damage > 0) {
          target.getHit(spell.damage)
          printer.spellHit(spell)
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
  }

  def learnSpell(char: Character, spell: Spells) = {
    char.spellBook += (spell.name -> spell)
    updateSpellValues(char)
  }

  def updateSpellValues(char: Character) = {
    for (spell <- char.spellBook) {
      spell._2.updateValues(char)
    }
  }

}