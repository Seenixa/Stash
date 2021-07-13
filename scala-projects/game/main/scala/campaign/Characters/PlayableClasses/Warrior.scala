package campaign.characters.playableclasses
import campaign.characters.Character
import campaign.spells.SpellHandler
import campaign.spells.Spells
import campaign.spells.playablespells._
import main.scala.campaign.io.Printer

class Warrior extends Character {

  strength = 10
  agility = 7
  intelligence = 5
  vitality = 10
  unspentSkillPoints = 0
  experienceToNextLevel = 150
  maxMana = intelligence * 10
  mana = maxMana
  shield = 0

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