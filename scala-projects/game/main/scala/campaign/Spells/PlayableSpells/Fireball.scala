package campaign.spells.playablespells
import campaign.spells.Spells
import campaign.characters.Character

class Fireball extends Spells {

  name = "Fireball"

  override def updateValues(char: Character) = {
    damage = char.intelligence * 2
    burnDuration = char.intelligence / 5
  }

}