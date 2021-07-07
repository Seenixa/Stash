package campaign.spells.playablespells
import campaign.characters.Character
import campaign.spells.Spells

class Fireball extends Spells {

  name = "Fireball"

  override def updateValues(char: Character) = {
    damage = char.intelligence * 2
    burnDuration = char.intelligence / 5
  }

}