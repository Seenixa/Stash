package campaign.characters.playableclasses
import campaign.characters.PlayerCharacter
import campaign.spells.Spells
import campaign.spells.playablespells._

class Warrior extends PlayerCharacter {

  strength = 10
  agility = 7
  intelligence = 5
  vitality = 10
  unspentSkillPoints = 0
  experienceToNextLevel = 150
  maxMana = intelligence * 10
  mana = maxMana
  shield = 0
  baseSpells = List(new ShieldBash, new ShieldWall)
  
  inventory += ("Main-hand Weapon" -> itemList.shortSword,
    "Armor" -> itemList.empty,
    "Shield" -> itemList.rustyShield)

}