libCalendar
===========
Adam Brown [ Wavesonics ]


About:
------
Android 4.0 (Ice Cream Sandwich) introduced a public Content Provider for Android's local Calendar database. It's fairly full featured, but can be a pain to use if you are only performing simple operations.

In the interest of being able to write simple concise code, I began writing this very hacky set of wrappers. They are by no means meant to provide full access to every feature of the Content Provider. But they do have higher level functions to ease some of the pain points (and quantities of code) that are a part of Android's Content Provider system.

This library is a work in progress, the code isn't pretty. Pull requests are greatly appreciated.


Features so far:
----------------
* Data container classes for:
	* Calendar
	* Event
	* Instance
* All data containers are Parcelable, and have proper .equals() and .toString() overrides
* Utility classes for:
	* Calendar
	* Event
	* Instance
* iCalUtility for parsing iCal strings into useful data