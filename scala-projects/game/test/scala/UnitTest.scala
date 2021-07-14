package campaign.test
import campaign.characters._
import campaign.fight.FightHandler
import campaign.characters.Stats
import campaign.ApplicationContext

class UnitTest (val appCon: ApplicationContext) {

  var yourCharacter = new PlayerCharacter
  yourCharacter = appCon.game.chooseCharacterClass
  
  appCon.stats.levelTo(yourCharacter, 30)
  

  
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "vitality", 30)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "strength", 30)
  appCon.stats.SpendMoreSkillPoints(yourCharacter, "agility", 30)
  
  appCon.printer.character(yourCharacter)
  

}