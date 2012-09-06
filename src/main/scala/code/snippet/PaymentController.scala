package code.snippet
import net.liftweb.common.Empty
import net.liftweb.http.StatefulSnippet
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.S
import com.weiglewilczek.slf4s.Logging
import scala.xml.NodeSeq

class PaymentController extends StatefulSnippet with Logging {
  
  private var title = ""
  private var firstName = ""
  private var lastName = ""
  private var address1 = ""
  private var address2 = ""
  private var town = ""
  private var county = ""
  private var postcode = ""
  private var country = ""
    
  private var cardType = ""
  private var cardNumber = ""
  private var cardExpiryYear = ""
  private var cardExpiryMonth = ""
  private var cardSecurityNumber = ""		
  
  private val countries = Seq(("UK","UK"),("France","France"))  
  private val cardTypes = Seq(("Visa","Visa"),("Mastercard","Mastercard"))
    
  def render = {
     // for each input element specified in our html, use the SHtml generator function to set its initial value 
    // to an empty string and associate it with a function so that when the form
    // is submitted, the input is stored in the specified variable
    "#title" #> SHtml.text("", title = _) &
	  "#payment_first_name" #> SHtml.text("", firstName = _) &
	  "#payment_last_name" #> SHtml.text("", lastName = _) &
	  "#payment_address_line_1" #> SHtml.text("", address1 = _) &
	  "#payment_address_line_2" #> SHtml.text("", address2 = _) &
	  "#payment_town" #> SHtml.text("", town = _) &
	  "#payment_county" #> SHtml.text("", county = _ ) &
	  "#payment_postcode" #> SHtml.text("", postcode = _) &
	  "#payment_country" #> SHtml.select(countries, Empty, country = _)
	  "#card_type" #> SHtml.select(cardTypes, Empty, cardType = _) &
	  "#card_number" #> SHtml.text("", cardNumber = _) &
	  "#card_expiry_year" #> SHtml.text("", cardExpiryYear = _) &
	  "#card_expiry_month" #> SHtml.text("", cardExpiryMonth = _) &
	  "#card_security_number" #> SHtml.text("", cardSecurityNumber = _) &
	  // bind the form submission to the function that will process it
	  // for now, don't bother processing the form submission, just take them directly to the order confirmation page
	  "type=submit" #> SHtml.onSubmitUnit(() => S.redirectTo("/order-confirmation"))
  }
  
  def dispatch = {
    case "render" => render
  }
}

