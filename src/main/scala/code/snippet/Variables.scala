package code.snippet
import net.liftweb.http.SessionVar
import net.liftweb.common.Box
import net.liftweb.common.Empty

object sessionBasket extends SessionVar[Box[code.poso.Basket]](Empty)