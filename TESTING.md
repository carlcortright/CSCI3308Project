## Who
Calvin Ly, John Zavidniak, Carl Cortright, Luke Meszar, Chris Rhoda

## Title
Words of Encouragement

## Vision
Every smartwatch user should wake up with the proper words of encouragement to start their day off right. Not only should their day start off right, but continual encouragement throughout the day will lead to increased happiness and productivity. Motivation: Our motivation for this project is to develop an app that will be used everyday. We want our users to look forward to our messages and hopefully it’ll be a bright spot in their day.

## Automated Tests


## User Acceptance Test Plan 1
| User Case ID | UC-01 |
| ---- | ---- |
| **User Case Name:** | Sports Quote Randomness |
| **Description:** | Ensure that a different quote is presented almost every time the user clicks on a quote category |
| **Users:** | Everyday Android Wear users  |
| **Preconditions:** | The sports table is populated with applicable quotes in the database. The app is opened and on the main page. |
| **Post-Conditions:** | A sports related quote is displayed on the screen |
| **Frequency of Use:** | Daily |

| Flow of Events: | Actor Action | System Response | Comments |
| ---- | ---- | ---- | ---- |
|      | 1. User presses sports quote button | A sports related quote is displayed  |      | 
|      | 2. User presses button to navigate back to main screen | The main screen is displayed, with the 3 options |      |
|      | 3. User presses sports quote button |  A sports related quote is displayed, it is a different quote than before ( 1 )    |      |

| Test Pass?: | Pass  |
| ---- | ---- |
| Notes and Issues |                                                            |

## User Acceptance Test Plan 2
| User Case ID | UC-02 |
| ---- | ---- |
| **User Case Name:** | General quote randomness |
| **Description:** | Ensure that a different general motivational quote is displayed each time the button is clicked, check that button to general quotes works, and check that the button back to the homepage works |
| **Users:** |  Everyday android wear users  |
| **Preconditions:** |  The general quote table in the database is populated with a variety of different quotes, and the app is on the main page |
| **Post-Conditions:** |  A general motivational quote is displayed on the screen   |

| Flow of Events: | Actor Action | System Response | Comments |
| ---- | ---- | ---- | ---- |
|      |  1. User presses general quote button  | A general motivational quote is displayed on screen  |      | 
|      |  2. User presses button to navigate back to the main screen |  The main screen is displayed, with the 3 different options |      |
|      |  3. User presses general quote button  | A general motivational quote is again displayed on the screen, this time it is different than the quote displayed after actor action 1     |      |

| Test Pass?: |  Pass   |
| ---- | ---- |
| **Notes and Issues** |     |

## User Acceptance Test Plan 3
| User Case ID | UC-03 |
| ---- | ---- |
| **User Case Name:** | Educational quote randomness |
| **Description:** | Check that the button to educational quotes works, check that the button back to the homescreen works, and  ensure that a different education related motivational quote is displayed each time the button is pressed  |
| **Users:** | Everyday Android Wear Users |
| **Preconditions:** | The educational quote table in the database is populated with a variety of different quotes, and the app is on the main page |
| **Post-Conditions:** | An encouraging educational quote is displayed on the screen |

| Flow of Events: | Actor Action | System Response | Comments |
| ---- | ---- | ---- | ---- |
|      | 1. User presses educational quote button | An education themed quote is displayed on the screen  |      | 
|      |  2. User presses the button to navigate back to the main screen  | The main screen is displayed, with the 3 different options |      |
|      |  3. User presses educational quote button | Another education themed quote is displayed on the screen, this one different than the one displayed after Actor Action 1   |      |

| Test Pass?: | Pass  |
| ---- | ---- |
| **Notes and Issues** |     |
