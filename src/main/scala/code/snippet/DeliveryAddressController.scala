package code.snippet
import scala.xml.NodeSeq
import net.liftweb.http.StatefulSnippet
import net.liftweb.util._
import Helpers._
import net.liftweb.http._
import org.amur.product.service.DefaultProductService
import code.poso.BasketItem
import net.liftweb.common.Empty
import net.liftweb.common.Full
import code.poso.Basket
import com.weiglewilczek.slf4s.Logging
import code.poso.Address
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JE._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.SHtml._

class DeliveryAddressController extends StatefulSnippet with Logging {
  
	private var title = ""
	private var firstName = ""
	private var lastName = ""
	private var addressLine1 = ""
	private var addressLine2 = ""
	private var town = ""
	private var county = ""
	private var postcode = ""
	private var country = ""
	
	  private val countries = Seq(("UK","UK"),("France","France"))
	
	def render = {
	  // delivery address
	  // for each input element specified in our html, use the SHtml generator function to set its initial value 
    // to an empty string and associate it with a function so that when the form
    // is submitted, the input is stored in the specified variable
	  "#title" #> SHtml.text("", titleTest(_)) &
	  "#first_name" #> SHtml.text("", firstName = _) &
	  "#last_name" #> SHtml.text("", lastName = _) &
	  "#address_line_1" #> SHtml.text("", addressLine1 = _) &
	  "#address_line_2" #> SHtml.text("", addressLine2 = _) &
	  "#town" #> SHtml.text("", town = _) &
	  "#county" #> SHtml.text("", county = _ ) &
	  "#postcode" #> SHtml.text("", postcode = _) &
	  // this form will be submitted via ajax using Lift's form.ajax helper, so we need to add a hidden 
	  // variable to the form which tells Lift what function to invoke when the form is submitted
	  "#country" #> (SHtml.select(countries, Empty, country = _) ++ SHtml.hidden(selectDeliveryAddress))
	}
	
	private def titleTest(s : String) = {
	  logger.debug("in titleTest. string is: " + s)
	}
	
	private def selectDeliveryAddress() : JsCmd = {
	  val basketBox = sessionBasket.is
	  basketBox match {
	    case Empty => logger.error("no basket")
	    case Full(basket) => logger.debug("you have a basket")
	    logger.debug("First name is now: " + firstName)
	    logger.debug("Country is now " + country)
	    basket.deliveryAddress = new Address(title,firstName,lastName,addressLine1,addressLine2,town,county,postcode,country)
	  }
	  // open the next part of the accordion
	  JsRaw("$('#accordion').accordion('activate',1)")
	}
	
	def dispatch = {
   	 	case "render" => render
    }
}