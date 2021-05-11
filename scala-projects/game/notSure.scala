object game extends App{
  import scala.collection.mutable.ArrayBuffer
  val random = scala.util.Random
  
  var itemIdCounter = 0
  var enemyIdCounter = 0  
  val monsterTypes = Seq[enemies](
    new enemies(name = "Green slime", level = 1, hitDamage = 10, armor = 0, health = 100, typeId = 1, experience = 50),
    new enemies(name = "Blue slime", level = 2, hitDamage = 20, armor = 5, health = 200, typeId = 2, experience = 100),
    new enemies(name = "Red slime", level = 3, hitDamage = 30, armor = 10, health = 300, typeId = 3, experience = 150),
    new enemies(name = "Black slime", level = 4, hitDamage = 40, armor = 15, health = 400, typeId = 4, experience = 200),
  )
  
  val itemTypes = Seq[item](
    new item(name = "Gauntlets of Strength", strength = 10, typeId = 1),
    new item(name = "Slippers of Agility", agility = 10, typeId = 2),
    new item(name = "Robes of the Magi", intelligence = 10, typeId = 3),
    new item(name = "Healthstone", vitality = 10, typeId = 4),
    new item(name = "Chainmail", armor = 10, typeId = 5)
  )
  
  class character(
    var level :Int = 1,
    var armor :Int = 0,
    var strength :Int = 0,
    var agility :Int = 0,
    var intelligence :Int = 0,
    var vitality :Int = 0,
    var hitDamage :Int = 0,
    var health :Int = 0,
    var experience :Int = 0,
    )
  {
    var inventory = ArrayBuffer[item]()
    
    var itemStats = new item()
    
    def addItem( itemId: Int) :Unit ={
      for( itemType <- itemTypes){
        if( itemType.typeId == itemId){
          inventory += itemType.addValues(itemId)
        }
      }
      updateItemValues
    }
    
    def updateItemValues: Unit ={
      itemStats = new item()
      for(item <- inventory){
        itemStats.strength += item.strength
        itemStats.agility += item.agility
        itemStats.intelligence += item.intelligence
        itemStats.vitality += item.vitality
        itemStats.armor += item.armor
      }      
    }
    
    var exptoNextLevel = 100 + level * 50
    health = 10 * vitality
    
    def levelUp :Unit ={
      level += 1
      exptoNextLevel = 100 + level * 50
    }
    
    def updateLevel: Unit ={
      while( experience >= exptoNextLevel){
        experience -= exptoNextLevel
        levelUp
        println(s"""
          |Congratulations! You have reached level $level!
          |""".stripMargin)
      }   
    }
    
    def updateValues :Unit ={
    }
    
    def castAbility(choice: Int, enemy: enemies) :Unit ={
    }
    
    def getHit(amount :Int) :Unit ={
      health -= amount
    }
    
    def fight(enemy: enemies) :Unit ={
      var choiceOne = 1
      var choiceTwo = 1
      var turnCounter = 0
      var storeHitDamage = enemy.hitDamage
      val race = this.getClass.getSimpleName
      println(s"""$this
                |$enemy""".stripMargin)
      while (health > 0 && enemy.health > 0 && turnCounter < 10){
        turnCounter += 1
        println(s"Turn: $turnCounter")
        println(s"""1. Basic attack
                    |2. Skills
                    |""".stripMargin)       
        choiceOne = 2 // scala.io.StdIn.readInt()
        if( choiceOne == 1){
          if( this.hitDamage > enemy.armor)
            enemy.getHit(hitDamage - enemy.armor)
          else
            enemy.getHit(1)
        }
        else if( choiceOne == 2){
          do{
          race match{
            case "warrior" =>
              println(s"""1. Shield Wall
                         |2. Shield Bash
                         |3. back
                         |""".stripMargin)
              choiceTwo = 2 // scala.io.StdIn.readInt()
            case "rogue" =>
              println(s"""1. Poison Dagger
                         |2. Ambush
                         |3. back
                         |""".stripMargin)
              choiceTwo = 1 // scala.io.StdIn.readInt()
            case "mage" =>
              println(s"""1. Fireball
                         |2. Frostbolt
                         |3. back
                         |""".stripMargin)
              choiceTwo = 4 // scala.io.StdIn.readInt()
            case _ => 
              println(s"What the fuck are you doing?! How are you not any one of the classes wtf man?")
            
              }
            if( choiceTwo > 3 || choiceTwo < 1){
              println("Your choice is impossible.")
              choiceTwo = 1 // scala.io.StdIn.readInt()
            }
          } while( choiceTwo > 3 || choiceTwo < 1)
          castAbility(choiceTwo, enemy)
        }
        if( enemy.burn > 0){
          enemy.health -= this.intelligence
          enemy.burn -= 1
        }
        if( enemy.poison > 0){
          enemy.health -= this.agility
          enemy.poison -= 1
        }
        if( enemy.chill > 0){
          enemy.hitDamage = (enemy.hitDamage * 0.8F).toInt
          enemy.chill -= 1
        }      
        if( enemy.health > 0){
          if( enemy.hitDamage > this.armor)
            this.getHit(enemy.hitDamage - this.armor)
          else
            this.getHit(1)
          enemy.hitDamage = storeHitDamage
        }
        if( enemy.health > 0 && health > 0)
          println(s"""Your remaining health: $health
                      |The enemy's remaining health: ${enemy.health}
                      |""".stripMargin)
        else if( enemy.health <= 0){
          println(s"""Your remaining health: $health
                      |The enemy has been defeated.""".stripMargin)
          this.experience += enemy.experience
          updateValues
        }
        if( health <= 0)
          println(s"""You have been defeated.
                      |The enemy's remaining health: ${enemy.health}""".stripMargin)
      }
    }
    
    override def toString = s"""level:        $level
                               |class:        ${this.getClass.getSimpleName}
                               |strength:     $strength
                               |agility:      $agility
                               |intelligence: $intelligence
                               |vitality:     $vitality
                               |health:       $health
                               |armor:        $armor
                               |experience:   $experience
                               |nextlevelup:  $exptoNextLevel
                               |""".stripMargin  
  }
  
  class warrior(
    var shieldBash :Int = 0,
    var shieldWall :Int = 0,
  ) extends character
  {    
    override def updateValues :Unit ={
      updateLevel
      updateItemValues
      armor = 20 + itemStats.armor
      strength = 20 + (level * 5) + itemStats.strength
      agility = 10 + (level * 3) + itemStats.agility
      intelligence = 5 + (level * 2) + itemStats.intelligence
      vitality = 30 + (level * 10) + itemStats.vitality
      health = vitality * 10
      hitDamage = strength * 2
      shieldBash = strength + armor
      shieldWall = (health * 0.25).toInt
    }
    
    override def castAbility(choice: Int, enemy: enemies) :Unit={
      if( choice == 1)
        castShieldWall
      if( choice == 2)
        castShieldBash(enemy)
    }
    
    def castShieldWall :Unit ={
      println("Casting Shield Wall.")
      health += shieldWall
    }
    
    def castShieldBash(enemy: enemies) :Unit ={
      println("Casting Shield Bash")
      if( shieldBash > enemy.armor)
        enemy.getHit(shieldBash - enemy.armor)
      else
        enemy.getHit(1)
    }   
  }
  
  class rogue(
    var poisonDuration :Int = 0,
    var poisonDamage :Int = 0,
    var ambushDamage :Int = 0
  ) extends character
  {  
    override def updateValues :Unit ={
      updateLevel
      updateItemValues
      armor = 10 + itemStats.armor
      strength = 10 + (level * 2) + itemStats.strength
      agility = 20 + (level * 5) + itemStats.agility
      intelligence = 5 + (level * 3) + itemStats.intelligence
      vitality = 20 + (level * 5) + itemStats.vitality
      health = vitality * 10
      hitDamage = strength + agility
      poisonDamage = agility
      poisonDuration = ( intelligence / 5)
      ambushDamage = agility * 2
    }
    
    override def castAbility(choice: Int, enemy: enemies) :Unit ={
      if( choice == 1)
        castPoisonDagger(enemy)
      if( choice == 2)
        castAmbush(enemy)
    }
    
    def castPoisonDagger(enemy: enemies) :Unit ={
      println("Casting Poison Dagger")
      if( hitDamage > enemy.armor)
        enemy.getHit(hitDamage - enemy.armor)
      else
        enemy.getHit(1)
      enemy.poison += poisonDuration
    }
    
    def castAmbush(enemy: enemies) :Unit ={
      println("Casting Ambush")
      if( ambushDamage > enemy.armor)
        enemy.getHit(ambushDamage - enemy.armor)
      else
        enemy.getHit(1)
    }  
  }
  
  class mage(
    var fireballDamage :Int = 0,
    var fireballBurnDuration :Int = 0,
    var frostboltDamage :Int = 0,
    var frostboltChillDuration :Int = 0
  ) extends character
  {
    override def updateValues :Unit ={
      updateLevel
      armor = 5 + itemStats.armor
      strength = 5 + (level * 2) + itemStats.strength
      agility = 10 + (level * 2) + itemStats.agility
      intelligence = 20 + (level * 5) + itemStats.intelligence
      vitality = 15 + (level * 4) + itemStats.vitality
      health = vitality * 10
      hitDamage = intelligence
      fireballDamage = intelligence * 4
      frostboltDamage = intelligence * 3
      fireballBurnDuration = ( intelligence / 10)
      frostboltChillDuration = ( intelligence / 10)
    }
    
    override def castAbility(choice: Int, enemy: enemies) :Unit ={
      if( choice == 1)
        castFireball(enemy)
      if( choice == 2)
        castFrostbolt(enemy)
    }
    
    def castFireball(enemy: enemies) :Unit ={
      println("Casting Fireball")
      if( fireballDamage > enemy.armor)
        enemy.getHit(fireballDamage - enemy.armor)
      else
        enemy.getHit(1)
      enemy.burn += 1
    }
    
    def castFrostbolt(enemy: enemies) :Unit ={
      println("Casting Frostbolt")
      if( frostboltDamage > enemy.armor)
        enemy.getHit(frostboltDamage - enemy.armor)
      else
        enemy.getHit(1)
      enemy.chill += 1
    }    
  }
  
  class enemies(                                                      /* Base enemy class */
    var name :String = "",
    var level :Int = 0,
    var hitDamage :Int = 0,
    var armor :Int = 0,
    var health :Int = 0,
    var typeId :Int = 0,
    var experience :Int = 0
  ){
    def nextId :Int ={
      enemyIdCounter += 1
      enemyIdCounter
    }
    
    val id = nextId
    var burn = 0
    var chill = 0
    var poison = 0
    
    override def toString = s"""Name:         $name
                               |Id:           $id
                               |Damage:       $hitDamage
                               |Health:       $health
                               |Armor:        $armor
                               |""".stripMargin
    
    def addValues(Id :Int) :enemies ={                              /* Generates the value for the enemy of your choice */
      for( enemyType <- monsterTypes){
        if( enemyType.typeId == Id){
          name = enemyType.name
          level = enemyType.level
          hitDamage = enemyType.hitDamage
          armor = enemyType.armor
          health = enemyType.health
          typeId = enemyType.typeId
          experience = enemyType.experience
        }
      }
      this
    } 
    
    def getHit(amount :Int) :Unit ={
      health -= amount
    }  
  }
      
  class item(                                                     /* Base item class */
    var name :String = "",
    var strength :Int = 0,
    var agility :Int = 0,
    var intelligence :Int = 0,
    var vitality :Int = 0,
    var armor :Int = 0,
    var typeId :Int = 0
  ){
    def nextId :Int ={                                            /* Given an ID to the item */
      itemIdCounter += 1
      itemIdCounter
    }
    val id = nextId
    
    override def toString = 
    s"""Name:         $name
        |${if (strength != 0) "Strength:     " + s"$strength"; else{""} }
        |${if (agility != 0) "Agility:      " + s"$agility"; else{""}}
        |${if (intelligence != 0) "Intelligence: " + s"$intelligence"; else {""}}
        |${if (vitality != 0) "Vitality:     " + s"$vitality"; else {""}}
        |${if (armor != 0) "Armor:        " + s"$armor" else {""}}
        |""".stripMargin
    
    def addValues(Id :Int) :item ={                               /* Generates the value for the item of your choice */
      for( itemType <- itemTypes){
        if( itemType.typeId == Id){
          name = itemType.name
          armor = itemType.armor
          typeId = itemType.typeId
        }
      }
      this
    }   
  }
  
  def chooseYourCharacter(choice: Int) :character ={            /* Character choice */
    var yourCharacter = new character()
    var yourChoice = choice
    var toomuch = false
    while( yourChoice > 3 || yourChoice < 1){                   /* Makes your character a class of your choice */
      println(s"Try again")
      toomuch = true
      yourChoice = 1 // scala.io.StdId.readInt()
    }
    if( yourChoice == 1)
      yourCharacter = new warrior()
    if( yourChoice == 2)
      yourCharacter = new rogue()
    if( yourChoice == 3)
      yourCharacter = new mage()
    yourCharacter.updateValues
    yourCharacter
  }
    
  def playTheGame :Unit ={                                        /* Character choice */
    println(s"""Pick your character!
                |1. Warrior
                |2. Rogue
                |3. Mage
                |""".stripMargin)
    var choice = 2  // scala.io.StdIn.readInt()
    val yourCharacter = chooseYourCharacter(choice)
                                                                  /* Random fights */
    yourCharacter.fight(new enemies().addValues(3))               
    yourCharacter.fight(new enemies().addValues(4))
                                                                  /* test item giveouts */
    println(s"""Choose an item                                    
                |1. Gauntlets of Strength, extra Strength
                |2. Slippers of Agility, extra Agility
                |3. Robes of the Magi, extra Intelligence
                |4. Healthstone, extra Vitality
                |5. Chainmail, extra Armor
                |""".stripMargin)
    choice = 2 // scala.io.StdIn.readInt()
    yourCharacter.addItem(choice)
    
  }
  
  playTheGame
  
  
}







