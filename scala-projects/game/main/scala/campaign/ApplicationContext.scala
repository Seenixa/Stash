package campaign

import main.scala.campaign.fight.FightHandler
import campaign.story.Game
import campaign.spells.SpellHandler
import campaign.characters.Stats

class ApplicationContext {
  val setup = new Game
  val spell = new SpellHandler
  val fight = new FightHandler(setup, spell)
  val stats = new Stats
}