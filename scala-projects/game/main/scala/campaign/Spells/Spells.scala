package campaign.spells
import campaign.characters.PlayerCharacter


class Spells {
  var name = ""
  var damage = 0
  var healing = 0
  var shielding = 0
  var manaCost = 0
  var chillDuration = 0
  var burnDuration = 0
  var poisonDuration = 0
  var stunDuration = 0

  def updateValues(char: PlayerCharacter): Unit = {
    
  }

  override def toString = s"$name 	$damage".stripMargin

}