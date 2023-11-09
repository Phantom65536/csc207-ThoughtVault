# Initial Design
## Entities (from project blueprint and incorporated comments from TA)
LocalEvent: 
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

LocalCredentials:
- User (User): user object [IDENTIFIER]
- Username (String): Username of the user used during login
- Password (byte[]): The hashed password string
- API key (String): OAuth API key to interact with the the user's Google Calendar

User:
- ID (int): Unique identifier for each user [IDENTIFIER]
- Name (string): Name of the user 
- Calendar (com.google.api.services.calendar.Calendar): The Google Calendar instance associated with the user obtained from Google

## Classes and methods
Use case interactors:
- Local Event Input Boundary:
  - bool createEvent(): create and save an event
  - bool editEvent(): edit an existing event and save its changes
  - bool deleteEvent(): delete an existing event
  - [Event Output Data, Notification Output Data] getEvent(): return an Event instance when the user wants to view its details
  - ArrayList of Event Output Data getAllEvents(): get all events to show in listed or calendar view
- External Event Input Boundary:
  - bool importEvent()
  - bool exportEvent()
- Local Event interactor (implementing Local Event Input Boundary):
  - bool createEvent(Event Input Data Object): create a local event
  - bool editEvent(Event Input Data Object)
  - bool deleteEvent(Event Input Data Object)
  - Event Output Data, Notification Output Data getEvent(EventID)
  - ArrayList of Event Output Data getAllEvents()
- Google Calendar Event interactor (implementing External Event Input Boundary):
  - bool importEvent(GoogleCalendarEventID): import an event from Google Calendar
  - bool exportEvent(Event Input Data Object): export an existing local event to the user's Google Calendar
  - ArrayList of Google Calendar Event Output Data getAllEvents(): get the user's events on their Google Calendar so that they can view them and choose which to import
- Notifications Input Boundary:
  - bool setOccurrences()
  - ArrayList<Java.time.localDateTime> getOccurrences()
- Notifications interactor (implementing Notifications Input Boundary):
  - bool setOccurrences(Notification Input Data Object): add, remove or edit existing occurrences
  - ArrayList<Java.time.localDateTime> getOccurrences(EventID): get the list of occurrences associated with this event
- Sign up Input Boundary:
  - bool createUser()
- Sign up interactor (implementing Sign up Input Boundary):
  - bool createUser(Signup Data Input Object): create a User instance and save it in storage device
  - private bool setCredentials(Username, password, API key): save the credentials for the user, hash the password
  - private bool createCalendar(API String): create a Google Calendar instance with this OAuth Client ID
- Log in+out Input Boundary:
  - User Output Data logIn()
  - bool logOut()
- Log in+out interactor (implementing Log In+Out Input Boundary):
  - User Output Data logIn(Login Data Input Object): Match username with a User instance and check if password is correct
  - bool logOut(userID): Log out and the system should return to Login page

Data Access (depends on whether we implement it with database tables or JSON files):
- Data Access Interface:
  - Object getEntry()
  - bool addEntry()
  - bool removeEntry()
- Event Data Access Object (associated with Event entities)
- Notification Data Access Object (associated with Notification entities)
  - A special row for saving general notification occurrences which applies to all events without specific notification settings
- User Data Access Object (associated with User and Credentials entities)
- Google Calendar Access Object

Data Input classes:
- Event/Notification input data:
  - All instance attributes in User/Notification but make them private
  - Constructor assigning values to all instance attributes
  - A method for returning values of all instance attributes
- Signup input data:
  - Private instance attributes: Name, Username, Password, API key
  - Constructor assigning these
  - A method for returning these
- Login input data:
  - Private instance attributes: Username, Password
  - Constructor assigning these
  - A method for returning these

Controllers:
- Event controller:
  - Methods to create, edit, delete, get or get all event(s) in which the corresponsing method in Local Event Input Boundary is called
- Google Calendar Event controller
- Notifications controller
- Signup controller
- Login+out controller

Output Data classes:
- Google Calendar Event output data:
  - Private instance attribute: important deatils in Google Calendar Event instance
  - Contructor to assign this
  - A method for returning the Event instance
- Event/Notification/User output data:
  - Private instance attribute: all instance attributes in Event/Notification/User
 
Presenters and Output Boundaries:

View models and Views (Details of each view described in README.md):
- Calendar view
- Listed view
- Detailed view of an event (specific notifications can be configured here)
- Add/Edit view of an event (only details associated with the entity Event can be augmented here)
- General Notifications settings
- Signup view
- Login view
