package code.snippet

import net.liftweb.util._
import Helpers._
import net.liftweb.http._
import net.liftweb.common.Logger
import code.tables.Schema
import net.liftweb.squerylrecord.RecordTypeMode._
import code.model.Country
import com.weiglewilczek.slf4s.Logging

class CreateCountry extends StatefulSnippet with Logging {
	
    private var name = ""
    private var iso3 = ""
    private var iso2 = ""
	private val whence = S.referer openOr "/"
  
	def render = 
		//  info("in render")
	  // render transforms NodeSeq -> NodeSeq, but the easiest way to do it is to use a CSS Selector transform
	   "name=countryName" #> SHtml.text("", name = _) &
	   "name=iso3" #> SHtml.text("", iso3 = _) &
	   "name=iso2" #> SHtml.text("", iso2 = _) &
	  "name=date" #> new java.util.Date().toString() &
	  "type=submit" #> SHtml.onSubmitUnit(process)
  
	  
	private def process() = 
	{
	  // create the country using squeryl
	  logger.info("Creating new country")
	  /*
	  transaction {
		 val country = Country.createRecord.name(name).iso3(iso3).iso2(iso2)
		Schema.countries.insert(country)
		// info("New country created")
		// val countries = from(Schema.countries)(s => where (5 > 0) select(s))
		// info("Countries retrieved from db: " + countries)
	  }*/
	  S.redirectTo(whence)  
	}
	  
	def dispatch = { 
		case "render" => render 
	}
}