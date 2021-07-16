package campaign.spells.playablespells
import campaign.characters.PlayerCharacter
import campaign.spells.Spells

class Fireball extends Spells {

  name = "Fireball"
  damage = 50
  burnDuration = 3
  manaCost = 30

  override def updateValues(char: PlayerCharacter) = {
    damage = char.intelligence * 2
    burnDuration = char.intelligence / 5
  }

}