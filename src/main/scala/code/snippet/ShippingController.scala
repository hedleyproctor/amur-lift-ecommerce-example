package code.snippet
import net.liftweb.http.StatefulSnippet
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JE._
import com.weiglewilczek.slf4s.Logging
import org.amur.shipping.service.ShippingService
import org.amur.shipping.service.DefaultShippingService
import net.liftweb.common.Empty
import net.liftweb.common.Full
import code.poso.ShippingOption
import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JE.JsRaw


class ShippingController extends StatefulSnippet with Logging {
	private var shippingOptionId = ""
	private var options : Set[ShippingOption] = null
	  
	def render(xhtml : NodeSeq) : NodeSeq = {
	  var ret = xhtml
	  val basketBox = sessionBasket.is
	  basketBox match {
	    case Empty => logger.error("no basket")
	    case Full(basket) => logger.debug("you have a basket")
	    	// get the available shipping options
	    	val shippingService = new DefaultShippingService()
	    	options = shippingService.getShippingOptions(basket)
	    	// convert the shipping options to a format to put in an html select
	    	val selectOptions = options.map(so => (so.id,so.name)).toSeq
	    	// now render the form
	    	val f = 
	    		"#shipping_select" #> (SHtml.select(selectOptions, Empty, shippingOptionId = _) 
	    		    ++ SHtml.hidden(selectShippingOption)) // we need to add this hidden variable as this form will be
	    		    // submitted via ajax, using Lift's form.ajax helper. It uses this hidden variable to know what 
	    		    // function to invoke when the form is submitted
	    	ret = f(xhtml)
	  }
	  // provide the NodeSeq to be returned
	  ret
	}
	
	private def selectShippingOption() : JsCmd = {
	  // get the basket session variable
	  val basketBox = sessionBasket.is
	  basketBox match {
	    case Empty => logger.error("no basket")
	    case Full(basket) => logger.debug("you have a basket")
	    	// add the chosen shipping option to the basket
	    	logger.debug("shipping option id: " + shippingOptionId)
	    	basket.shippingOption = options.filter(_.id == shippingOptionId).first
	    	logger.debug("Now basket shipping option is: " + basket.shippingOption)
	  }
	  
	  // open the next part of the accordion
	  JsRaw("$('#accordion').accordion('activate',2)")
	}
	
	def dispatch = {
   	 	case "render" => render
    }
	
}