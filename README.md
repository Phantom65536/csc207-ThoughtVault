# csc207-ThoughtVault
This app, designed for efficient event and sub-event management, employs a JSON-based storage system. Users can easily organize and view events, with support for sub-events, labels, and pins. The graphical user interface includes a home page for event lists, detailed event views, editing capabilities, and a calendar view. It integrates with Google Calendar for importing and exporting events. Users can configure the application settings so that they can get notified before a certain event.
## Storage and formatting of events and sub-events:
- Home page shows pinned events
- Each event/sub-event is stored in a JSON object
- The JSON object has attributes title, content, location, date, starttime, endtime, whole-day, label, pinned, sub-events
    - date, starttime and endtime can be null if the user does not specify the date and time for a certain event
    - whole-day is a boolean variable which can only be checked if the user does not specify starttime and endtime
    - if user specifies date, either all-day has to be checked or starttime and endtime must be filled in
    - location stores the physical location or the virtual meeting link of the event
    - label categorizes the event (work, personal) (all sub-events must have the same label as their top-level event)
    - pinned is a boolean variable indicating whether this event/sub-event is important
    - sub-events is a list of event JSON ovjects
## Graphical User Interface:
- Listed view of the created events (home page)
    - Show titles of each event with up to 2 levels of their subevents in a hierarchical structure
    - Simple search bar (exact match of event/sub-event's title)
    - Filtering by label, date and pinned
    - Sorting by label, date and title
    - A button for user to add a top-level event
    - Show pinned events before other events
    - A button for user to pin a listed event/sub-event
- Detailed view of an event/sub-event in detail
    - Show title and content of the selected event/sub-event
    - Show titles of ancestor and/or descendant events, if any, in a hierarchical structure
    - Buttons for uesr to choose to delete all its descendant events or only this event (all its sub-events will move up one level in the latter case)
    - A button for user to edit the details of this event
    - A button for user to add a sub-event as a direct child of this event
    - A button for uesr to pin this event
- Editting view of an event/sub-event
    - User can edit any of the attributes of this event except sub-events
- Calendar view of all events
    - View by month, week, day
    - Show title and label of event or sub-event on the calendar if its date is specified (boldfacing pinned events/sub-events)
    - A small listed view showing titles of all pinned events/sub-events (with or without date)
    - Allow quick edit of events/sub-events including title, date, all-day/starttime/endtime
## Pop-up notifications
- Notify user of upcoming events
- Notification settings: eg send reminders ?? min before event/sub-event starts
## Use of API: Google Calendar
- A demo Google account will be used (Future improvement: allow users to enter their Google account credentials and access their Google calendar)
- Create a top-level event by importing an event from the user's Google Calendar
- Exporting an event/sub-event with date specified to the user's Google Calendar
- The link to Google Calendar API: https://developers.google.com/calendar/api/guides/overview

## Screenshot of using Postman to call the API
![Screenshot of using API](https://raw.githubusercontent.com/Phantom65536/csc207-ThoughtVault/annie/img/API.png)

## Example output of running our Java code
![Screenshot of output](https://raw.githubusercontent.com/Phantom65536/csc207-ThoughtVault/annie/img/output_1.png)
![Screenshot of output 2](https://raw.githubusercontent.com/Phantom65536/csc207-ThoughtVault/annie/img/output_2.png)
![Screenshot of output 3](https://raw.githubusercontent.com/Phantom65536/csc207-ThoughtVault/annie/img/output_3.png)
