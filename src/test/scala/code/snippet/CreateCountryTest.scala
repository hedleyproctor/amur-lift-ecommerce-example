package code.snippet

import org.scalatest.Spec
import scala.xml.NodeSeq
import net.liftweb.http._
import net.liftweb.util._
import net.liftweb.common._
import code.tables.Schema
import net.liftweb.squerylrecord.RecordTypeMode._

class CreateCountryTest extends Spec {
  
  val session = new LiftSession("",StringHelpers.randomString(20),Empty)
  
  describe("CreateCountry snippet") {
    it ("should create a new country") {
      var cc = new CreateCountry
      val xml = <div class="lift:CreateCountry">
	  		Name: <input name="countryName" value="United Kingdom"/><br/>
	  		Iso3: <input name="iso3" value="GBR"/><br/>
	  		Iso2: <input name="iso2" value="GB"/><br/>
	  		<input type="submit" value="Submit"/>
	  	</div>
      S.initIfUninitted(session) {
    	val result = cc.render(xml)
    	println(result)
    	// check that the db has a new country
    	val countries = from(Schema.countries)(s => where (5 > 0) select(s))
      }
    }
  }

}