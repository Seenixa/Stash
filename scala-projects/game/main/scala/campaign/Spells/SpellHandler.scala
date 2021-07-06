package campaign.spells
import campaign.characters.Character
import campaign.enemies.Enemies

class SpellHandler {

  def cast(caster: Character, spell: Spells, target: Enemies) = {
    if (!caster.spellBook.contains(spell.name))
      println("Spell not learned.")
    if (caster.spellBook.contains(spell.name)) {
      println(s"Casting $spell.name.")
      target.getHit(spell.damage)
      target.burnDuration += spell.burnDuration
      target.chillDuration += spell.chillDuration
      target.poisonDuration += spell.poisonDuration
      target.stunDuration += spell.stunDuration
      caster.shield += spell.shielding
      if (caster.health + spell.healing >= caster.maxHealth)
        caster.health = caster.maxHealth
      else caster.health += spell.healing
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