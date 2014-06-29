import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

	/*
	 * Check if static pages are live
	 */
	@Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
	
	@Test
    public void testThatRepairPageWorks() {
        Response response = GET("/Repair");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
	
	
	@Test
    public void testThatQandAPageWorks() {
        Response response = GET("/QandA");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
	
	@Test
    public void testThatAboutUsPageWorks() {
        Response response = GET("/AboutUs");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
    
}