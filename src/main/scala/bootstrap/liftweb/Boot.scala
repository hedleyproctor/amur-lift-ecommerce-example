package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import mapper._

import org.h2._
import net.liftweb.squerylrecord.SquerylRecord
import org.squeryl.Session
import org.squeryl.adapters.H2Adapter
import org.squeryl.PrimitiveTypeMode._

import code.model._
import code.lib._

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
  
	Class.forName("org.h2.Driver")
	val session = Session.create(
        java.sql.DriverManager.getConnection("jdbc:h2:mem:dbname;DB_CLOSE_DELAY=-1", "sa", ""),
        new H2Adapter)
  
	SquerylRecord.initWithSquerylSession(session)

	// create the schema
	transaction { code.tables.Schema.create }
		
    // where to search snippet
    LiftRules.addToPackages("code")

    // Build SiteMap
    def sitemap = SiteMap(
      Menu.i("Home") / "index" >> User.AddUserMenusAfter, // the simple way to declare a menu
      Menu.i("Product list") / "products",
      Menu.i("Basket") / "basket",
      Menu.i("Checkout") / "checkout",
      Menu.i("Order confirmation") / "order-confirmation",
      // more complex because this menu allows anything in the
      // /static path to be visible
      Menu(Loc("Static", Link(List("static"), true, "/static/index"), 
	       "Static Content")))

    def sitemapMutators = User.sitemapMutator

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemapMutators(sitemap))

    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // What is the function to test if a user is logged in?
    LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))    

  }
}