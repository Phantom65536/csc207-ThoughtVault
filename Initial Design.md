# Initial Design
## Entities (from project blueprint and incorporated comments from TA)
### Note:
- Name (String): Unique title or name of the event [IDENTIFIER]
- User (String): The user that this event belongs to
- Date (java.time.LocalDate): The date of the event
- Description (String): A description of the event
- Label (enum): Label or category associated with the event (default enum values: personal or work)
- Pinned (boolean): Indicates whether the event is pinned for quick access

### Event:
Inherits from Note
- StartTime (java.time.LocalTime): The time when the event occurs (default value: 00:00)
- EndTime (java.time.LocalTime): The time when the event ends (default value: 23:59)
- Location (String): The physical location or the virtual meeting link of the event
- Description (String): A description of the event
- Sub-events (ArrayList of String): The child events of this event

*Note:* 
1) *Sub-events have same structure as events*
2) *Let's not deal with recurring events first (possibly imported from Google Calendar)*
3) *Event is displayed as "all-day" if StartTime and EndTime are indicated as 00:00 and 23:59 respectively*

### Credentials:
- Username (String): Unique username of the user used during login [IDENTIFIER]
- Password (byte[]): The hashed password string
- Credential (Credential): Credential object to interact with user's google calendar via OAuth client ID.

### User:
- Name (String): Unique name of the user [IDENTIFIER]
- Calendar (Calendar): The user's Google Calendar object

## Classes and methods
### Input boundaries
#### Note input boundaries
- bool createNote() Input Boundary: create and save a note
- bool editNote() Input Boundary: edit an existing note and save its changes
- deleteNote() Input Boundary: delete an existing note
- [note Output Data, Notification Output Data] getNote() Input Boundary: check whether the note belongs to the user and return its corresponding note instance when the user wants to view its details
- ArrayList of Note Output Data getAllNotes() Input Boundary: get all notes to show in list view
#### Event input boundaries
- bool createEvent() Input Boundary: create and save an event
- bool editEvent() Input Boundary: edit an existing event and save its changes
#### Sign up input boundaries
- bool displaySignUpView()
- bool signUp()
#### Log in input boundaries
- bool displayLogInView()
- User Output Data logIn()
#### Log out input boundaries
- bool logOut()

### Use case interactors
#### Note interactors
- bool createNote(note Input Data Object) Interactor: create a local note
- bool editNote(note Input Data Object) Interactor
- bool deleteNote(note Input Data Object) Interactor
- note Output Data getNote(noteID, UserID) Interactor
- ArrayList of Note Output Data getAllNotes(UserID) Interactor
#### Event interactors
- bool importEvent(EventID) Interactor: import an event from Google Calendar
- bool exportEvent(Event Input Data Object) Interactor: export an existing local event to the user's Google Calendar
- ArrayList of Google Calendar events Output Data getAllEvents() Interactor: get the user's Google Calendar events so that they can view them and choose which to import
#### Sign up interactors
- bool displaySignUpView() Interactor
- bool createUser(Signup Data Input Object) Interactor: create a User instance and save it
  - private bool setCredentials(Username, password, Credential) Interactor: save the credentials for the user, hash the password
  - private bool isCredentialValid(Credential) Interactor: check if the Credential object is valid
#### Log in interactors
- bool displayLogInView() Interactor
- User Output Data logIn(Login Data Input Object) Interactor: Match username with a User instance and check if password is correct
#### Log out interactors
- bool logOut() Interactor: Log out and the system should return to Login page

### Data Access (depends on whether we implement it with database tables or JSON files):
- Data Access Interface:
  - Object getEntry()
  - bool addEntry()
  - bool removeEntry()
- Note Data Access Object (associated with note entities)
- Event Data Access Object (associated with event entities)
- User Data Access Object (associated with User and Credentials entities)
- Google Calendar Access Object

### Data Input classes:
- Note input data:
  - All instance attributes in User but make them private
  - Constructor assigning values to all instance attributes
  - A method for returning values of all instance attributes
- Signup input data:
  - Private instance attributes: Name, Username, Password, RepeatPassword, API key
  - Constructor assigning these
  - A method for returning these
- Login input data:
  - Private instance attributes: Username, Password
  - Constructor assigning these
  - A method for returning these

### Controllers:
- Note controller:
  - Methods to create, edit, delete, get or get all note(s) in which the corresponding method in Local note Input Boundary is called
- Google Calendar note controller
- Signup controller
- Log in controller
- Log out controller

### Output Data classes:
- Google Calendar note output data:
  - Private instance attribute: important details in Google Calendar note instance
  - Constructor to assign this
  - A method for returning the details
- Note output data:
  - Private instance attribute: all instance attributes in LocalNote/Notification
  - noteId and its corresponding name for associated note in Notification and sub-notes in LocalNote
- User output data:
  - Private instance attributes: Name, UserID, GcalID associated with them

### Presenters and Output Boundaries:

### View models and Views (Details of each view described in README.md):
- List view
- Detailed view of a note
- Add/Edit view of a note (only details associated with the entity note can be edited here)
- Detailed view of an event
- Add/Edit view of an event (only details associated with the entity event can be edited here)
- Signup view
- Login view
