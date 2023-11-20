# JSON files:
## Events:
- id (int)
- name (String)
- userid (int)
- date (String in ISO_Local_Date format)
- starttime (String in ISO_Local_Time format)
- endtime (String in ISO_Local_Time format)
- location (String) 
- description (String)
- label (String)
- pinned (boolean)
- sub-events (JSONArray of int)

## Notifications:
- eventid (int)
- occurrences (JSONArray of String in ISO_Local_Date_Time format)

## Users:
- id (int)
- username (String)
- password (String)
- gcal-credential (String)