import net.liftweb.record.{MetaRecord,Record} 
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord

class Basket extends Record[Basket] with KeyedRecord[Long] {
	override def meta = Basket
	override val idField = new LongField(this)
	
}

object Basket extends Basket with MetaRecord[Basket] {
  
}