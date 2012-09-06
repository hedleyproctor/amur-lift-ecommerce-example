package org.amur.product.service

import scala.collection.mutable.HashSet
import code.poso.Product

trait ProductService {
	def getDummyProducts() : Iterable[Product]
	def getProduct(id : String) : Option[Product]
}