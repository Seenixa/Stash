package campaign.characters

import campaign.item.Item
import campaign.spells.Spells
import campaign.item.itemList.ItemList

class PlayerCharacter extends Character {

  var strength = 0
  var agility = 0
  var intelligence = 0
  var vitality = 0
  var mana = 0
  var maxMana = 0
  var experience = 0
  var experienceToNextLevel = 150
  var unspentSkillPoints = 0

  var baseSpells = List[Spells]()
  var inventory = Map[String, Item]()
  var spellBook = Map[String, Spells]()
  val itemList = new ItemList

  override def toString = s"""level:        $level
                             |class:        ${this.getClass.getSimpleName}
                             |strength:     $strength
                             |agility:      $agility
                             |intelligence: $intelligence
                             |vitality:     $vitality
                             |health:       $health
                             |mana:         $mana
                             |armor:        $armor
                             |experience:   $experience
                             |""".stripMargin
}