import java.util.List;

import models.Appointment;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

/**
 * 
 * @author Chrysa Papadaki - papapdaki.chr@gmail.com
 *
 */
public class FeedbackPaymentTest extends FunctionalTest{

	@Test
    public void retrieveAppointmentList() {
        List<Appointment> appointment =  Appointment.findAll();
        assertNotNull(appointment);
    }
	
	@Test
    public void testThatFeedbackIndexWorks() {
        Response response = GET("/Feedback");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
	
	@Test
    public void testThatFeedbackSubmitWorks() {
        Response response = GET("/Feedback");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
}
