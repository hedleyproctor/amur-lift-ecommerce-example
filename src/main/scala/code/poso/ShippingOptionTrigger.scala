package code.poso

trait ShippingOptionTrigger {
	def getType() : String
	def isTriggered(basket : Basket) : Boolean	
}