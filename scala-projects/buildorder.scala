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
      new production( name = "Supply Depot", supply = 14, timing = 6),
      new production( name = "Supply Depot", supply = 14, timing = 5),
      new production( name = "Supply Depot", supply = 14, timing = 7),
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
    
    def checkBuildTimesEarly( time: Int, prod: ArrayBuffer[production]) :Boolean ={
      var nextBuild = false
      for( build <- prod){
        if( timer + 3 == build.timing)
          nextBuild = true
      }
      nextBuild
    }
    
    def checkBuildTimes( time: Int, prod: ArrayBuffer[production]) :Boolean ={
      var nextBuild = false
      for( build <- prod){
        if( timer == build.timing)
          nextBuild = true
      }
      nextBuild
    }
    
    sortBuildings
    
    for(i <- buildings)
    println(i)
    
    var nextBuilding = buildings(0)
    var earlyWarning = buildings(0)
    var writtenMinutes = timer / 60
    var writtenSeconds = timer % 60
    var buildId = 0
    var earlyWarningId = 0
    
    while( timer < lookForLast(buildings) + 5){
      timer += 1
      writtenMinutes = timer / 60
      writtenSeconds = timer % 60
      delay(1)
        println(s"$writtenMinutes : $writtenSeconds")
      if( checkBuildTimesEarly(timer, buildings)){
        println(s"Get ready to build ${earlyWarning.name}.")
        earlyWarningId += 1
        if( earlyWarningId < buildings.length)
          earlyWarning = buildings(earlyWarningId)
      }
      if( checkBuildTimes(timer,buildings)){
        println(s"${nextBuilding.name} building started.")
        buildId += 1
        if( buildId < buildings.length)
          nextBuilding = buildings(buildId)
      }
    }   
  }
   
  startTimer
  
}
