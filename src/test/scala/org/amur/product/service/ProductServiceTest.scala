package org.amur.product.service

import org.scalatest.Spec


class ProductServiceTest extends Spec {
	describe ("ProductService") {
	  it ("should return 30 dummy products") {
	    val productService = new DefaultProductService()
	    val products = productService.getDummyProducts()
	    println(products)
	    println("Number of products returned: " + products.size)
	    assert(products.size == 30)
	  }
	}
}