package code.poso

class Basket {
	def addItem(basketItem : BasketItem) {
	  items += basketItem
	}
  
	var items = new scala.collection.mutable.HashSet[BasketItem]
	var deliveryAddress : Address = null
	var billingAddress : Address = null
	var shippingOption : ShippingOption = null
	
	def getTotalCost() : BigDecimal = {
	  BigDecimal(0)
	}
}