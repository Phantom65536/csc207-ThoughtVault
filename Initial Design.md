# Initial Design
## Entities (from project blueprint and incorporated comments from TA)
Event: 
- ID (int): Unique identifier of the event [IDENTIFIER]
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
- Event (Event): event object [IDENTIFIER]
- Occurences (ArrayList of java.time.LocalDateTime): The list of when to notify the user of this event

Credentials:
- User (User): user object [IDENTIFIER]
- Username (String): Username of the user used during login
- Password (byte[]): The hashed hashed password string
- API key (String): OAuth API key to interact with the the user's Google Calendar

User:
- ID (int): Unique identifier for each user [IDENTIFIER]
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
  - ArrayList<Event> getAllEvents(): get all events to show in listed or calendar view
- External Event Input Boundary:
  - bool importEvent()
  - bool exportEvent()
- Local Event interactor (implementing Local Event Input Boundary):
  - bool createEvent(Event Input Data Object): create a local event
  - bool editEvent(Event Input Data Object)
  - bool deleteEvent(Event Input Data Object)
  - Event getEvent(Identifier Input Data Object)
  - ArrayList<Event> getAllEvents()
- Google Calendar Event interactor (implementing External Event Input Boundary):
  - bool importEvent(Google Calendar Event Input Data Object): import an event from Google Calendar
  - bool exportEvent(Event Input Data Object): export an existing local event to the user's Google Calendar
  - Google Calendar Event getAllEvents(): get the user's events on their Google Calendar so that they can view them and choose which to import
- Notifications Input Boundary:
  - bool setOccurrences()
  - ArraryList<java.time.LocalDateTime> getOccurrences()
- Notifications interactor (implementing Notifications Input Boundary):
  - bool setOccurrences(Notification Input Data Object): add, remove or edit existing occurrences
  - ArraryList<java.time.LocalDateTime> getOccurrences(Identifier Input Data Object): get the list of occurrences associated with this event
- Sign up Input Boundary:
  - bool createUser()
  - bool setCredentials()
- Sign up interactor (implementing Sign up Input Boundary):
  - bool createUser(all details associated with a user): create a User instance and save it in storage device
  - bool setCredentials(all details associated with the user's credentials): save the credentials for the user, hash the password
  - bool createCalendar(API String): create a Google Calendar instance with this OAuth Client ID
- Log in+out Input Boundary:
  - bool logIn()
  - bool logOut()
- Log in+out interactor (implementing Log In+Out Input Boundary):
  - bool logIn(username, password): Match username with a User instance and check if password is correct
  - bool logOut(User): Log out and the system should return to Login page

Data Access (depends on whether we implement it with database tables or JSON files):
- Data Access Interface:
  - Object getEntry()
  - bool addEntry()
  - bool removeEntry()
- Event Data Access Object (associated with Event and Notification entities)
- User Data Access Object (associated with User and Credentials entities)
- Google Calendar Access Object

Data Input classes:
- Identifier input data:
  - Instance attribute: an ID (event ID for entities Notification and Event, user ID for entities User and Credentials)
  - Constructor assigning ID and method getting the ID
- Event/Notification input data:
  - All instance attributes in User/Notification but make them private
  - Constructor assigning values to all instance attributes
  - A method for returning values of all instance attributes
- Google Calendar Event input data:
  - Instance attribute: Google Calendar Event instance
  - Contructor to assign this
  - A method for returning the Event instance
- Signup input data:
  - All instance attributes 
