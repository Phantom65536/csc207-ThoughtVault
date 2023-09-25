# csc207-ThoughtVault
# Storage and formatting of events and sub-events:
- Home page shows pinned events
- Each event/sub-event is stored in a JSON object
- The JSON object has attributes title, content, date, starttime, endtime, whole-day, label, pinned, sub-events
  - date, starttime and endtime can be null if the user does not specify the date and time for a certain event
  - whole-day is a boolean variable which can only be checked if the user does not specify starttime and endtime
  - if user specifies date, either all-day has to be checked or starttime and endtime must be filled in
  - label categorizes the event (work, personal) (all sub-events must have the same label as object)
  - pinned is a boolean variable indicating whether this event is important
  - sub-events is a list of event JSON ovjects
# Graphical User Interface:
- List view of the created events (home page)
  - Show titles of each event with up to 2 levels of their subevents in a hierarchical structure
  - Simple search bar (exact match of event/sub-event's title)
  - Filtering by label, date and pinned
  - Sorting by label, date and title
  - A button for user to add a top-level event
  - Show pinned events before other events
- Viewing an event/sub-event in detail
  - Show title and content of the selected event/sub-event
  - Show titles of ancestor and/or descendant events if any
  - Buttons for uesr to choose to delete all its descendant events or only this event
  - A button for user to edit the details of this event
  - A button for user to add a sub-event as a direct child of this event
  - A button for uesr to pin this event
- Editting an event/sub-event
  - User can edit any of the attributes of this event except sub-events (seen above for how to augment the value of this attribute)
- Calendar view of all events
  - View by month, week, day
  - Show title and label of event or sub-event with a date on the calendar (boldfacing pinned events/sub-events)
  - A small listed view showing titles of all pinned events/sub-events (with or without date)
# Use of API: Google Calendar
- A button on home page for user to add their Google account credentials
- Create a top-level event by importing an event from the user's Google Calendar
- Exporting an event/sub-event with date specified to the user's Google Calendar
