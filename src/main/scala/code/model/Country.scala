package code.model

import net.liftweb.record.{MetaRecord,Record} 
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord

class Country extends Record[Country] with KeyedRecord[Long] {
	override def meta = Country
	override val idField = new LongField(this)
	val name = new StringField(this,100)
	val iso2 = new StringField(this,2)
	val iso3= new StringField(this,3)
}

object Country extends Country with MetaRecord[Country]{
  
}