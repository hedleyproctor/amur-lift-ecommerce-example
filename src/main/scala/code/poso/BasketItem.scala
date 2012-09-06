package code.poso

class BasketItem {
	var product = new Product()
	var price = 0
	var tax = 0
	var duty = 0
	var totalPrice = 0
	
	def this(product : Product) {
	  this()
	  this.product = product
	}
}