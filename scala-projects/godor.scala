object godor extends App{
  import scala.collection.mutable.ArrayBuffer
  
  val melyseg = Array[Int](0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 4, 4, 3, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 0, 2, 0, 4, 0, 2, 1, 4, 0)
  var dataCount = 0
  
  // 1. feladat  Írja ki a képernyőre, hogy az adatforrás hány adatot tartalmaz!
  
  
  for(i <- melyseg)
    dataCount += 1 
  println(s"1. feladat")
  println(s"A fájl adatainak száma: $dataCount\n\n")
  
  
  //2. feladat Olvasson be egy távolságértéket, majd írja a képernyőre, hogy milyen mélyen van a gödör
  //           alja azon a helyen! Ezt a távolságértéket használja majd a 6. feladat megoldása során is!
  
  
  def getDepth( distance: Int) :Int ={
    var depth = 0
    if(distance <= 30 && distance >= 0){
      depth = melyseg(distance)
    }
    depth
  }
  
  var distance = 6 // scala.io.StdIn.readInt()
  println(s"2. feladat")
  println(s"Adjon meg egy távolságértéket! $distance")
  println(s"Ezen a helyen a felszín ${getDepth(distance)} méter mélyen van.\n\n")
  
  
  //3. feladat Határozza meg, hogy a felszín hány százaléka maradt érintetlen és jelenítse meg 2 tizedes
  //           pontossággal! 
  
  
  def getPercentage :Float ={
    var undamaged :Int = 0
    var percentage :Float = 0
    for(i <- melyseg){
      if(i == 0)
        undamaged += 1
    }
    percentage = (undamaged.toFloat / dataCount.toFloat) * 100
    percentage  
  }
  println(s"3. feladat")
  print(s"Az érintetlen terület aránya: ")
  print(f"$getPercentage%1.2f")
  println(s"%\n\n")
  
  
  //4. feladat Írja ki a godrok.txt fájlba a gödrök leírását, azaz azokat a számsorokat, amelyek egy-egy
  //           gödör méterenkénti mélységét adják meg! Minden gödör leírása külön sorba kerüljön! Az
  //           állomány pontosan a gödrök számával egyező számú sort tartalmazzon!
  
  
  //5. feladat Határozza meg a gödrök számát és írja a képernyőre! 
  
  
  def checkForHoles :Int ={
    var numberOfHoles = 0
    for(i <- 0 until melyseg.length - 1){
      if(melyseg(i) == 0 && melyseg(i + 1) != 0)
        numberOfHoles += 1
    }
    numberOfHoles
  }
  
  println(s"5. feladat")
  println(s"A gödrök száma: $checkForHoles")
  
  
  //6. feladat Ha a 2. feladatban beolvasott helyen nincs gödör, akkor „Az adott helyen nincs gödör.”
  //           üzenetet jelenítse meg, ha ott gödör található, akkor határozza meg, hogy
  //              a) mi a gödör kezdő és végpontja! A meghatározott értékeket írja a képernyőre!
  //                 (Ha nem tudja meghatározni, használja a további részfeladatoknál a 7 és 22
  //                 értéket, mint a kezdő és a végpont helyét)
  //              b) a legmélyebb pontja felé mindkét irányból folyamatosan mélyül-e! Azaz a gödör
  //                 az egyik szélétől monoton mélyül egy pontig, és onnantól monoton emelkedik a
  //                 másik széléig. Az eredménytől függően írja ki a képernyőre a „Nem mélyül
  //                 folyamatosan.” vagy a „Folyamatosan mélyül.” mondatot!
  //              c) mekkora a legnagyobb mélysége! A meghatározott értéket írja a képernyőre!
  //              d) mekkora a térfogata, ha szélessége minden helyen 10 méternyi! A meghatározott
  //                 értéket írja a képernyőre!
  //              e) a félkész csatorna esőben jelentős mennyiségű vizet fogad be. Egy gödör annyi
  //                 vizet képes befogadni anélkül, hogy egy nagyobb szélvihar hatására se öntsön
  //                 ki, amennyi esetén a víz felszíne legalább 1 méter mélyen van a külső felszínhez
  //                 képest. Írja a képernyőre ezt a vízmennyiséget! 
  
  var isItaHole = true
  
  if(getDepth(distance) == 0){
    println(s"Az adott helyen nincs gödör.")
    isItaHole = false
  }

  
  // a)
  
  if(isItaHole == true)
    println(s"A gödör kezdete: $getHoleStart méter, a gödör vége: $getHoleEnd méter.")
  
  def getHoleStart :Int ={
    var holeStart = 0
    while( getDepth(distance) != 0 || isItaHole == true){
      distance -= 1
      isItaHole = false
    }
    holeStart = distance + 2
    isItaHole = true
    holeStart
  }
  
  def getHoleEnd :Int ={
    var holeEnd = 0
    while( getDepth(distance) != 0 || isItaHole == true){
      distance += 1
      isItaHole = false
    }
    holeEnd = distance 
    isItaHole = true
    holeEnd
  }
  
  // b)
  
  def checkMonotonityFromTheStart :Int ={
    var endOfLine = getHoleStart
    while(getDepth(endOfLine) <= getDepth(endOfLine + 1))
      endOfLine += 1
    endOfLine += 1
    endOfLine
  }
  
  def checkMonotonityFromTheEnd :Int ={
    var endOfLine = getHoleEnd
    while(getDepth(endOfLine) <= getDepth(endOfLine - 1))
      endOfLine -= 1
    endOfLine
  }
  
  if(checkMonotonityFromTheStart <= checkMonotonityFromTheEnd)
    println(s"A gödör folyamatosan mélyül")
  else
    println(s"A gödör mélyülése nem folyamatos")
  
  
  println(s"$checkMonotonityFromTheStart  $checkMonotonityFromTheEnd")
  
  // c)
  
  def checkMaxDepth :Int ={
    var maxDepth = 0
    for(i <- getHoleStart to getHoleEnd){
      if(maxDepth < getDepth(i))
        maxDepth = getDepth(i)
    }
    maxDepth
  }
  
  println(s"A legnagyobb mélység: $checkMaxDepth")
  
  // d)
  
  def getArea :Int ={
    var area = 0
    for(i <- (getHoleStart - 1) to getHoleEnd){
      area += getDepth(i) * 10
    }
    area
  }
  
  println(s"A térfogata: $getArea m^3")
  
  // e)
  
  def getWaterArea :Int ={
    var area = 0
    for(i <- (getHoleStart - 1) to getHoleEnd){
      if(getDepth(i) > 1)
        area +=  (getDepth(i) - 1) * 10
    }
    area
  }
  
  println(s"A vízmennyiség $getWaterArea m^3")

}





