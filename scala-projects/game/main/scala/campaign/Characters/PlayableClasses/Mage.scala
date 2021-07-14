package campaign.characters.playableclasses
import campaign.characters.PlayerCharacter
import campaign.spells.Spells
import campaign.spells.playablespells._

class Mage extends PlayerCharacter {

  strength = 5
  agility = 7
  intelligence = 10
  vitality = 5
  unspentSkillPoints = 0
  maxMana = intelligence * 10
  mana = maxMana
  shield = 0
  baseSpells = List(new Fireball, new Frostbolt)

}