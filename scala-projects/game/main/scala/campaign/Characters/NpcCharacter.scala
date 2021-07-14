package campaign.characters

class NpcCharacter extends Character {
  
  var experienceReward = level * 25
  
  override def toString = {
    s"""|Name:        $name
        |Avg. damage: ${(minHitDamage + maxHitDamage) / 2}
        |health:      $health
        |armor:       $armor""".stripMargin
  }
}