import com.google.api.services.calendar.Calendar;
import data_access.EventsDataAccessObject;
import data_access.GCalDataAccessObject;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import use_case.gcalevent.GCalEventDataAccessInterface;
import use_case.gcalevent.GCalEventOutputData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import static org.junit.Assert.*;

public class GCalEventOutputDataTest {

    public GCalEventOutputData setup() throws GeneralSecurityException, IOException, ParseException {
        // Read the apiKey environment variable
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("gradle.properties");
        prop.load(input);
        String APIkey = prop.getProperty("apiKey");

        GCalEventDataAccessInterface userDataAccessObject = new GCalDataAccessObject(APIkey);
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();
        String eventId = "3ddfaspndfpa8qn96drjds2r92";

        GCalEventOutputData gCalEventOutputData = new GCalEventOutputData(eventId, calendar, calendarId);
        return gCalEventOutputData;
    }

    @Test
    public void getEventId() throws GeneralSecurityException, IOException, ParseException {
        GCalEventOutputData gCalEventOutputData = setup();
        Assert.assertEquals("3ddfaspndfpa8qn96drjds2r92", gCalEventOutputData.getEventId());
    }

    @Test
    public void getDate() throws GeneralSecurityException, IOException, ParseException {
        GCalEventOutputData gCalEventOutputData = setup();
        Assert.assertNotNull(gCalEventOutputData.getDate());
    }

    @Test
    public void getTitle() throws GeneralSecurityException, IOException, ParseException {
        GCalEventOutputData gCalEventOutputData = setup();
        Assert.assertEquals("first event", gCalEventOutputData.getTitle());
    }
}