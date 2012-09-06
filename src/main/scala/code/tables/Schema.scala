package code.tables

import code.model._

object Schema extends org.squeryl.Schema {
	val countries = table[Country]
}