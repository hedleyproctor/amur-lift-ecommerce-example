package code.snippet

import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import org.amur.product.service.DefaultProductService
import net.liftweb.http.SHtml
import net.liftweb.http.S
import net.liftweb.common.Empty
import code.poso.Basket
import net.liftweb.common.Full
import code.poso.BasketItem
import net.liftweb.common.Logger
import com.weiglewilczek.slf4s.Logging

class ProductListController extends Logging {
	
	val productService = new DefaultProductService()
  
	def render(in : NodeSeq) : NodeSeq = {
	  val products = productService.getDummyProducts()
	  
	  // create some product details for each product
	  // in addition, create a form for adding the product to the basket
	  def t = ".product_details" #> products.map{p =>
		  ".product_name *" #> p.name &
		  ".product_description *" #> p.description &
		  // bind the form submission to the function that will process it
		  "type=submit" #> SHtml.onSubmitUnit(() => process(p.id))
	  }
	  t(in)
	}
	
	def process(productId : String) = {
	  logger.debug("process method. adding item to bag. Product id: " + productId)
	  // check that we have a basket stored in the session. 
	  sessionBasket.is match {
	    case Empty =>  val basket = new Basket()
	    				sessionBasket.set(Full(basket))
	    				addItem(basket,productId)
	    case Full(basket) => addItem(basket,productId)
	  }
	  logger.debug("now basket box = " + sessionBasket.is)
	  
	  S.redirectTo("/basket")
	}
	
	def addItem(basket : Basket, productId : String) = {
	  val p = productService.getProduct(productId)
	  p match {
	    case Some(product) => logger.debug("Found product " + productId + " for adding to basket")
	      basket.addItem(new BasketItem(product))
	    case None => error("Can't find product " + productId + " to add to basket")
	  }
	}
}