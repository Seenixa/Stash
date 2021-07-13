package campaign.characters.playableclasses
import campaign.characters.Character
import campaign.spells.SpellHandler
import campaign.spells.Spells
import campaign.spells.playablespells._
import main.scala.campaign.io.Printer

class Rogue extends Character {

  strength = 5
  agility = 10
  intelligence = 7
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