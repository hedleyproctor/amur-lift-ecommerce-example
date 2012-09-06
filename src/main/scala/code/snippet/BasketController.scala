package code.snippet
import scala.xml.NodeSeq
import org.amur.product.service.DefaultProductService
import net.liftweb.util.Helpers._
import net.liftweb.http.LiftSession
import net.liftweb.http.S
import net.liftweb.common.Full
import net.liftweb.common.Empty
import code.poso.Basket
import code.poso.BasketItem
import net.liftweb.common.Box
import net.liftweb.http.SHtml
import com.weiglewilczek.slf4s.Logging

class BasketController extends Logging {
	def render(in : NodeSeq) : NodeSeq = {
	  
	  val basketBox = sessionBasket.is
	  basketBox match {
	    case Empty => logger.debug("no basket. creating basket for next time")
	    	val productService = new DefaultProductService()
	    	val product1 = productService.getProduct("1")
	  
	    	product1 match {
	    		case Some(product1) => 
	    			val userBasket = new Basket
	    			val basketItem = new BasketItem
	    			basketItem.product = product1
	    			userBasket.addItem(basketItem)
	    			// set into the session var
	    			sessionBasket.set(Full(userBasket)) 
	  		} 
	    	return in
	    case Full(basket) => logger.debug("you have a basket")
	    		for (bi <- basket.items) {
	    			logger.debug("Basket item. Product id: " + bi.product.id + " Name: " + bi.product.name)
	    		}
	    	  def t = "#basket_item" #> basket.items.map{bi =>
	    	  	"#name" #> bi.product.name &
	    	  	"#description" #> bi.product.description &
	    	  	"#price" #> "£49.99"
	    	  }
	    	  t(in)
	  }

	}
}