package use_case;

import java.io.IOException;
import java.util.ArrayList;

public interface GCalEventInputBoundary {
    boolean importEvent(String eventId) throws IOException;
    boolean exportEvent(int localEventId) throws IOException;
    ArrayList<GCalEventOutputData> getAllEvents() throws IOException;
}
