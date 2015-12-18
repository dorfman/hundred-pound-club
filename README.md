# Hundred Pound Club

Tells you on which dates you reached each hundredth milestone when summing the one rep maxes of your bench press, squat, and deadlift.
Made this for myself and five friends.
Wrote it in Java because most computers run Java and it's easy to distribute a jar file.

## Assumptions and Requirements

* FitNotes (Available only on Android) https://play.google.com/store/apps/details?id=com.github.jamesgay.fitnotes&hl=en
* Dropbox (both on your phone and on your computer)
* Java 8
* Microsoft Excel (Preferably at least 2013)

## How To Run

1. In FitNotes, click on the top right drop down menu and go to 'Settings'.
Under 'Data', click on 'Spreadsheet Export'. Export just the Workout Data
and do not include the timestamp in the file name. Click on 'Cloud' and save
it to a folder on your Dropbox.
2. Copy jar file to the same directory where "FitNotes_Export.csv" is located
in your Dropbox.
3. Double click on the jar file.

## How to View Results

Open either csv file in Excel. Click on the Insert tab. Click on the 
Recommended Charts button. Click on the third option.

"Three_Graph.csv" holds data for all three lifts. When graphed, they appear
stacked above each other.

"Sum_Graph.csv" holds the sum of all three lifts and just shows the
cumulative trend.