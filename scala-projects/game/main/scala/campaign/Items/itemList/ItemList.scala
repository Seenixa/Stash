package campaign.item.itemList
import campaign.item.Item
import campaign.characters.PlayerCharacter

class ItemList {

  val empty = new Item(name = "Empty")

  val shortStaff = new Item(
    name = "Short staff",
    itemType = "mageWeapon",
    intelligence = 10)
  val dagger = new Item(
    name = "Dagger",
    itemType = "oneHandWeapon",
    minHitDamage = 10,
    maxHitDamage = 30)
  val shortSword = new Item(
    name = "Short sword",
    itemType = "mainHandWeapon",
    minHitDamage = 15,
    maxHitDamage = 30)
  val rustyShield = new Item(
    name = "Rusty shield",
    itemType = "shield",
    armor = 5)
  val raggedClothes = new Item(
    name = "Ragged clothes",
    itemType = "armor",
    armor = 5)
}