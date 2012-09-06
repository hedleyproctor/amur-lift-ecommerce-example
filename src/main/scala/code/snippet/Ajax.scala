package code.snippet

import _root_.net.liftweb.http.SHtml._
import _root_.net.liftweb.http.js.JE._
import _root_.net.liftweb.http.js.JsCmds._
import scala.xml.NodeSeq
import com.weiglewilczek.slf4s.Logging
import net.liftweb.util._
import Helpers._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.SHtml

class Ajax extends Logging {

    def ajaxFunc1() : JsCmd = JsRaw("alert('Button1 clicked')")
    
  def ajaxFunc2(str: String) : JsCmd = {
    println("Received " + str)
    JsRaw("alert('Button2 clicked')")
  }
    
  def ajaxFunc3() : JsCmd = JsRaw("alert('Button3 clicked')")
  
// define a snippet method
	def render = {
		 "#button1" #> SHtml.ajaxButton("Press me", ajaxFunc1 _) &
            "#button2" #>
              // ajaxCall and ajaxInvoke actually returns a pair (String, JsExp).
              // The String is used for garbage collection, so we only need
              // to use the JsExp element (_2).
              <button onclick={SHtml.ajaxCall(Str("Button-2"), ajaxFunc2 _)._2.toJsCmd}>Press me 2</button> &
            "#button3" #>
              <button onclick={SHtml.ajaxInvoke(ajaxFunc3 _)._2.toJsCmd}>Press me 3</button> &
              "type=submit" #> SHtml.submit("Register", ajaxFunc1, 
	    "onclick" -> JsIf(JsEq(ValById("first_name"), ""), Alert("alert") & JsReturn(false)).toJsCmd) 
	}
}