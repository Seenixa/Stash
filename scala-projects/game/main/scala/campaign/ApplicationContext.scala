package campaign

import campaign.characters.Stats
import campaign.io._
import campaign.spells.SpellHandler
import campaign.gameMain.Game
import campaign.fight.FightHandler
import campaign.io.Printer

class ApplicationContext {
  val printer = new Printer
  val utility = new Utility(printer)
  val stats = new Stats(printer)
  val game = new Game(printer, utility)
  val spellHandler = new SpellHandler(printer)
  val fightHandler = new FightHandler(utility, printer, spellHandler)

}