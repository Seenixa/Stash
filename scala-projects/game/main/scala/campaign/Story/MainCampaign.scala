package campaign.story

import campaign.ApplicationContext

object MainCampaign {
  def main (args: Array[String]) = {
    
    val appCon = new ApplicationContext
    appCon.game.start
    
  }
}