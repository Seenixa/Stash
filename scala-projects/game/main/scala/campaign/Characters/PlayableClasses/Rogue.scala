package campaign.characters.playableclasses
import campaign.characters.PlayerCharacter
import campaign.spells.Spells
import campaign.spells.playablespells._

class Rogue extends PlayerCharacter {

  strength = 5
  agility = 10
  intelligence = 7
  vitality = 10
  unspentSkillPoints = 0
  experienceToNextLevel = 150
  maxMana = intelligence * 10
  mana = maxMana
  shield = 0
  baseSpells = List(new Ambush, new PoisonShot)
  
  inventory += ("Main-hand Weapon" -> itemList.dagger,
    "Armor" -> itemList.empty,
    "Off-hand Weapon" -> itemList.dagger)


}