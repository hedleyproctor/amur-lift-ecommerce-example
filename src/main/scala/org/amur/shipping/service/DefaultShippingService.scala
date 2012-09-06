package org.amur.shipping.service
import code.poso.ShippingOption
import code.poso.Basket

class DefaultShippingService extends ShippingService {
	override def getShippingOptions(basket : Basket) = dummyShippingOptions()
	
	override def getShippingOptions() = dummyShippingOptions()
	  
	def dummyShippingOptions() : Set[ShippingOption] = {
	  val s1 = new ShippingOption()
	  s1.id = "1"
	  s1.name = "Standard Delivery"
	  val s2 = new ShippingOption()
	  s2.id = "2"
	  s2.name = "Express Delivery"
	  val options = scala.collection.mutable.Set[ShippingOption]()
	  options += s1
	  options += s2
	  options.toSet	  
	}
}