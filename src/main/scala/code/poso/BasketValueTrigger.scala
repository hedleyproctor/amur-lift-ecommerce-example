package code.poso

class BasketValueTrigger extends ShippingOptionTrigger {

  def getType(): String = { "BASKET_VALUE" }

  def isTriggered(basket: Basket): Boolean = { false }

}