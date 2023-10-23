# Initial Design
## Entities (from project blueprint and incorporated comments from TA)
Event: 
- ID (int): Unique identifier of the event
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
1) *Sub-events have same structure as events*
2) *Let's not deal with recurring events first (possibly imported from Google Calendar)*
3) *Event is displayed as "all-day" if StartTime and EndTime are indicated as 00:00 and 23:59 respectively*

Notification:
- EventID (int): The event ID of the event to be notified
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

## Classes and methods
Use case interactors:
- Local Event Input Boundary:
  - bool createEvent(): create and save an event
  - bool editEvent(): edit an existing event and save its changes
  - bool deleteEvent(): delete an existing event
  - Event getEvent(): return an Event instance when the user wants to view its details
- External Event Input Boundary:
  - bool importEvent()
  - bool exportEvent()
- Local Event interactor (implementing Local Event Input Boundary):
  - bool createEvent(all details associated with an event): create a local event
  - bool editEvent(an existing Event instance)
  - bool deleteEvent(an existing Event instance)
  - Event getEvent()
- Google Calendar Event interactor (implementing External Event Input Boundary):
  - bool importEvent(Google Calendar Event instance): import an event from Google Calendar
  - bool exportEvent(local Event instance): export an existing local event to the user's Google Calendar
- Notifications Input Boundary:
  - bool setOccurrences()
  - ArraryList<java.time.LocalDateTime> getOccurrences()
- Notifications interactor (implementing Notifications Input Boundary):
  - bool setOccurrences(local Event instance, ArrayList of occurences datetime): add, remove or edit existing occurrences
  - ArraryList<java.time.LocalDateTime> getOccurrences(): get the list of occurrences associated with this event
- Sign up Input Boundary:
  - bool createUser()
  - bool setCredentials()
- Sign up interactor (implementing Sign up Input Boundary):
  - bool createUser(all details associated with a user): create a User instance and save it in storage device
  - bool setCredentials(all details associated with the user's credentials): save the credentials for the user, hash the password
  - bool createCalendar(API String): create a Google Calendar instance with this OAuth Client ID
- Log in/out Input Boundary:
  - bool logIn()
  - bool logOut()
- Log in/out interactor (implementing Log In/Out Input Boundary):
  - bool logIn(username, password): Match username with a User instance and check if password is correct
  - bool logOut(User): Log out and the system should return to Login page

Data Acess (depends on whether we implement it with database tables or JSON files):
- Data Access Interface:
  - Object getEntry()
  - bool addEntry()
  - bool removeEntry()
- Event Data Access Object
- Notification Data Access Object
- User Data Access Object
