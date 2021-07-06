package campaign.spells
import campaign.characters.Character
import campaign.enemies.Enemies

class SpellHandler {

  def cast(caster: Character, spell: Spells, target: Enemies) = {
    if (!caster.spellBook.contains(spell.name))
      println("Spell not learned.")
    if (caster.spellBook.contains(spell.name)) {
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
      if (spell._1 == "Ambush")
        spell._2.damage = char.agility * 2
      if (spell._1 == "Poison shot") {
        spell._2.damage = char.agility
        spell._2.poisonDuration = char.agility / 5
      }
      if (spell._1 == "Shield wall")
        spell._2.shielding = char.vitality * 3
      if (spell._1 == "Shield bash") {
        spell._2.damage = char.minHitDamage
        spell._2.stunDuration = 1
      }
      if (spell._1 == "Fireball") {
        spell._2.damage = char.intelligence * 2
        spell._2.burnDuration = char.intelligence / 3
      }
      if (spell._1 == "Frostbolt") {
        spell._2.damage = char.intelligence * 2
        spell._2.chillDuration = char.intelligence / 3
      }
    }
  }

}