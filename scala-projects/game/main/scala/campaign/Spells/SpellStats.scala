package campaign.spells

import campaign.characters.PlayerCharacter
import campaign.io._

class SpellStats(
  val printer: Printer) {

  def updateSpellValues(char: PlayerCharacter) = {
    for (spell <- char.spellBook) {
      spell._1 match {
        case "Ambush" =>
          spell._2.damage = char.agility * 2
        case "Poison shot" =>
          spell._2.damage = char.agility
          spell._2.poisonDuration = char.agility / 5
        case "Fireball" =>
          spell._2.damage = char.intelligence * 2
          spell._2.burnDuration = char.intelligence / 5
        case "Frostbolt" =>
          spell._2.damage = char.intelligence * 2
          spell._2.chillDuration = char.intelligence / 5
        case "Shield bash" =>
          spell._2.damage = char.minHitDamage
          spell._2.stunDuration = 1
        case "Shield wall" =>
          spell._2.shielding = char.vitality * 3
        case _ => printer.wrongChoice
      }
    }
  }

  def learnSpell(char: PlayerCharacter, spell: Spells) = {
    char.spellBook += (spell.name -> spell)
    updateSpellValues(char)
  }

}
