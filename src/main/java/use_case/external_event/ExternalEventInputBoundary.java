package use_case.external_event;

import java.io.IOException;

public interface ExternalEventInputBoundary {
    boolean importEvent(ExternalEventInputData externalEventInputData) throws IOException;
    boolean exportEvent(ExternalEventInputData externalEventInputData) throws IOException;
}
