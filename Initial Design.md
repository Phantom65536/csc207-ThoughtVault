# Initial Design
## Entities (from project blueprint and incorporated comments from TA)
Event: 
- Name (string): The title or name of the event
- User (User): The user that this event belongs to
- Date (java.time.LocalDate): The date of the event
- StartTime (java.time.LocalTime): The time when the event occurs (default value: 00:00)
- EndTime (java.time.LocalTime): The time when the event ends (default value: 23:59)
- Location (String): The physical location or the virtual meeting link of the event
- Description (String): A description of the event
- Label (enum): Label or category associated with the event (default enum values: personal or work)
- Pinned (boolean): Indicates whether the event is pinned for quick access
- Sub-events (ArrayList of Event): The child events of this event
*Note:*
*- Sub-events have same structure as events*
*- Let's not deal with recurring events first (possibly imported from Google Calendar)*
*- Event is displayed as "all-day" if StartTime and EndTime are indicated as 00:00 and 23:59 respectively*

Notification:
- Event (Event): The event to be notified
- Occurences (ArrayList of java.time.LocalDateTime): The list of when to notify the user of this event

Credentials:
- ID (int): The ID of the user that this Credentials instance is associated with
- Username (String): Username of the user used during login
- Password (byte[]): The hashed hashed password string
- API key (String): OAuth API key to interact with the the user's Google Calendar

User:
- ID (int): Unique identifier for each user
- Name (string): Name of the user 
- UserCredential (Credentials): The Credential instance associated with the user
- Calendar (com.google.api.services.calendar.Calendar): The Google Calendar instance associated with the user obtained from Google
