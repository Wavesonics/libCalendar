package com.darkrockstudios.libs.calendar;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Calendars;

public class CalendarUtility
{
	private static final String		GOOGLE_TYPE			= "com.google";

	// Projection array. Creating indices for this array instead of doing
	// dynamic lookups improves performance.
	private static final String[]	EVENT_PROJECTION	= new String[] {
			Calendars._ID,
			Calendars.NAME,
			Calendars.ACCOUNT_NAME,
			Calendars.ACCOUNT_TYPE,
			Calendars.CALENDAR_DISPLAY_NAME			};

	public static Calendar[] getGoogleCalendars( Context context, String account )
	{
		Cursor cur = null;
		ContentResolver cr = context.getContentResolver();
		Uri uri = Calendars.CONTENT_URI;
		String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND (" + Calendars.ACCOUNT_TYPE + " = ?))";
		String[] selectionArgs = new String[] { account, GOOGLE_TYPE };
		// Submit the query and get a Cursor object back.
		cur = cr.query( uri, EVENT_PROJECTION, selection, selectionArgs, null );

		ArrayList< Calendar > calendarList = new ArrayList< Calendar >();
		// Use the cursor to step through the returned records
		while( cur.moveToNext() )
		{
			Calendar cal = new Calendar( cur );
			calendarList.add( cal );
		}
		cur.close();

		Calendar[] calendarArray = new Calendar[calendarList.size()];
		return calendarList.toArray( calendarArray );
	}
}
