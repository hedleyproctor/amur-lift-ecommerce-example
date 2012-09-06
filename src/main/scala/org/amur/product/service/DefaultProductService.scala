package org.amur.product.service
import scala.collection.mutable.HashSet
import code.poso.Product
import scala.collection.mutable.HashMap

class DefaultProductService extends ProductService {
  
	override def getDummyProducts() : Iterable[Product] = {
	  return DefaultProductService.getDummyProducts().values
	}
	
	override def getProduct(id : String) : Option[Product] = {
	  return DefaultProductService.getDummyProducts().get(id)
	}
	
}

object DefaultProductService {	  
  val products = new HashMap[String,Product]
  
  def getDummyProducts() : HashMap[String,Product] = {
    if (products.size != 0) {
      return products
    }
	  for (i <- 1 to 30) {
	    val p = new Product()
	    p.id = i.toString()
	    p.name = "Product" + i
	    p.description = "Product " + i + " description"
	    products.put(p.id,p)
	  }
	  return products
	}
  
  // val descriptions = ["Floral dress"]
}
