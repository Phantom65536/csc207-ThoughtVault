package use_case;

import java.io.IOException;

public interface ExternalEventInputBoundary {
    boolean importEvent(String eventId) throws IOException;
    boolean exportEvent(String eventId) throws IOException;
}
