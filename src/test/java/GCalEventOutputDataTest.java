import com.google.api.services.calendar.Calendar;
import data_access.EventsDataAccessObject;
import data_access.GCalDataAccessObject;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import use_case.GCalEventDataAccessInterface;
import use_case.GCalEventOutputData;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.Assert.*;

public class GCalEventOutputDataTest {

    public GCalEventOutputData setup() throws GeneralSecurityException, IOException, ParseException {
        // String jsonCredentials = "{\"installed\":{\"client_id\":\"795633948902-lnv99a5r05977jor1cm9harbqhq4kcv2.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400118\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
        String APIkey = "{\"installed\":{\"client_id\":\"676658923300-jefh7ko5cp9n7cf92vj427ltrd0rumo4.apps.googleusercontent.com\",\"project_id\":\"thought-vault-400423\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
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
//        System.out.print(gCalEventOutputData.getTitle());
        Assert.assertEquals("3ddfaspndfpa8qn96drjds2r92", gCalEventOutputData.getEventId());
    }

    @Test
    public void getDate() throws GeneralSecurityException, IOException, ParseException {
        GCalEventOutputData gCalEventOutputData = setup();
        Assert.assertNotNull(gCalEventOutputData.getDate());

//        gCalEventOutputData.getEventAttributes();
//        gCalEventOutputData.getEventAttributes().forEach((key, value) -> System.out.println(key + " " + value));
    }

    @Test
    public void getTitle() throws GeneralSecurityException, IOException, ParseException {
        GCalEventOutputData gCalEventOutputData = setup();
        Assert.assertEquals("first event", gCalEventOutputData.getTitle());
    }
}