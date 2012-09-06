package code.poso

class Product {
	var id = ""
	var name = ""
	var description = ""
	var colour = ""
	
	var variants = new scala.collection.mutable.HashSet[ProductVariant]
}