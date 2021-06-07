object buildorder extends App{
  import scala.collection.mutable.ArrayBuffer  
  
  class production(
    val name :String,
    val timing :Int,
    val supply :Int
  ){
    override def toString = s"$name, $timing"
  }
  
  def startTimer :Unit ={
    
    val buildings = ArrayBuffer[production](
      new production( name = "Supply Depot", supply = 14, timing = 8),
      new production( name = "SCV", supply = 14, timing = 4),
      new production( name = "SCV", supply = 15, timing = 12),
      new production( name = "Barracks", supply = 16, timing = 16)
    )
    var timer = 0
    
    def sortBuildings :Unit ={
      var temp = new production("", 0, 0)
      var swap = true
      do{
        swap = false
        for( i <- 0 until buildings.length - 1){
          if( buildings(i).timing > buildings(i + 1).timing){
            temp = buildings(i)
            buildings(i) = buildings(i + 1)
            buildings(i + 1) = temp
            swap = true
          }
        }
      } while( swap == true)
        
    }
    
    def delay( seconds: Int) :Unit ={
      Thread.sleep(seconds * 1000)
    }
    
    def lookForLast( prod: ArrayBuffer[production]) :Int ={
      var lastBuildTiming = 0
      for( build <- prod){
        if( build.timing > lastBuildTiming)
          lastBuildTiming = build.timing
      }
      lastBuildTiming
    }
    
    def checkBuildTimes( time: Int, prod: ArrayBuffer[production]) :Int ={
      var nextBuild = 0
      for( build <- prod){
        if( timer + 3 == build.timing)
          nextBuild = 1
        if( timer == build.timing)
          nextBuild = 2
      }
      nextBuild
    }
    
    sortBuildings
    
    for(i <- buildings)
    println(i)
    
    var nextBuilding = buildings(0)
    var writtenMinutes = timer / 60
    var writtenSeconds = timer % 60
    var buildId = 0
    
    while( timer < lookForLast(buildings) + 5){
      timer += 1
      writtenMinutes = timer / 60
      writtenSeconds = timer % 60
      delay(1)
      if( checkBuildTimes(timer, buildings) == 0)
        println(s"$writtenMinutes : $writtenSeconds")
      if( checkBuildTimes(timer, buildings) == 1){
        println(s"$writtenMinutes : $writtenSeconds Get ready to build ${nextBuilding.name}.")
      }
      if( checkBuildTimes(timer,buildings) == 2){
        println(s"$writtenMinutes : $writtenSeconds ${nextBuilding.name} building started.")
        buildId += 1
        if( buildId < buildings.length)
          nextBuilding = buildings(buildId)
      }
    }   
  }
   
  startTimer
  
}
