package com.darkrockstudios.libs.calendar;

import java.util.ArrayList;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Entity;
import android.content.EntityIterator;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.CalendarContract;

public class EventUtility
{
	// Projection array. Creating indices for this array instead of doing
	// dynamic lookups improves performance.
	private static final String[]	EVENT_PROJECTION	= new String[] { CalendarContract.Events._ID, // 0
			CalendarContract.Events.CALENDAR_ID,
			CalendarContract.Events.EVENT_TIMEZONE,
			CalendarContract.Events.DTSTART,
			CalendarContract.Events.DTEND,
			CalendarContract.Events.DURATION,
			CalendarContract.Events.TITLE,
			CalendarContract.Events.DESCRIPTION		};

	public static ArrayList< Event > getEvents( Context context, long calId )
	{
		ArrayList< Event > events = new ArrayList< Event >();

		// Run query
		ContentResolver cr = context.getContentResolver();

		String selection = "(" + CalendarContract.Events.CALENDAR_ID + " = ?)";
		String[] selectionArgs = new String[] { calId + "" };
		// Submit the query and get a Cursor object back.
		Cursor cur = cr.query( CalendarContract.Events.CONTENT_URI, EVENT_PROJECTION, selection, selectionArgs, null );

		EntityIterator it = CalendarContract.EventsEntity.newEntityIterator( cur, cr );
		while( it.hasNext() )
		{
			Entity entity = it.next();
			events.add( new Event( entity ) );
		}
		cur.close();

		return events;
	}

	public static Event getEvent( Context context, long eventId )
	{
		Event event = null;

		Uri uri = CalendarContract.Events.CONTENT_URI;
		Builder builder = uri.buildUpon();
		ContentUris.appendId( builder, eventId );
		uri = builder.build();

		// Run query
		ContentResolver cr = context.getContentResolver();
		Cursor cur = cr.query( uri, EVENT_PROJECTION, null, null, null );
		EntityIterator it = CalendarContract.EventsEntity.newEntityIterator( cur, cr );
		if( it.hasNext() )
		{
			event = new Event( it.next() );
		}
		cur.close();

		return event;
	}
}
