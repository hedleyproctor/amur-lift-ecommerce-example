package org.amur.shipping.service
import code.poso.Basket
import code.poso.ShippingOption

trait ShippingService {
	def getShippingOptions() : Set[ShippingOption]
	
	def getShippingOptions(basket : Basket) : Set[ShippingOption]
}