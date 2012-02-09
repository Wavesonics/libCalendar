package com.darkrockstudios.libs.calendar;

import java.util.Set;
import java.util.Map.Entry;

import android.content.ContentValues;
import android.content.Entity;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.CalendarContract;

public class Event implements Parcelable
{
	public long		id;
	public int		calendar_id;
	public String	event_timezone;
	public long		dt_start;
	public long		dt_end;
	public String	duration;
	public String	title;
	public String	description;

	public Event(
					long _id,
					int _calendar_id,
					String _event_timezone,
					long _dt_start,
					long _dt_end,
					String _duration,
					String _title,
					String _description )
	{
		id = _id;
		calendar_id = _calendar_id;
		event_timezone = _event_timezone;
		dt_start = _dt_start;
		dt_end = _dt_end;
		duration = _duration;
		title = _title;
		description = _description;
	}

	public Event( Parcel src )
	{
		id = src.readLong();
		calendar_id = src.readInt();
		event_timezone = src.readString();
		dt_start = src.readLong();
		dt_end = src.readLong();
		duration = src.readString();
		title = src.readString();
		description = src.readString();
	}

	public Event( Entity entity )
	{
		ContentValues values = entity.getEntityValues();
		Set< Entry< String, Object >> set = values.valueSet();
		for( Entry< String, Object > entry : set )
		{
			handleEntry( entry );
		}
	}

	private void handleEntry( Entry< String, Object > entry )
	{
		final String key = entry.getKey();
		if( key.equals( CalendarContract.Events._ID ) )
		{
			id = (Long) entry.getValue();
		}
		else if( key.equals( CalendarContract.Events.CALENDAR_ID ) )
		{
			calendar_id = (Integer) entry.getValue();
		}
		else if( key.equals( CalendarContract.Events.EVENT_TIMEZONE ) )
		{
			event_timezone = (String) entry.getValue();
		}
		else if( key.equals( CalendarContract.Events.DTSTART ) )
		{
			dt_start = (Long) entry.getValue();
		}
		else if( key.equals( CalendarContract.Events.DTEND ) )
		{
			dt_end = (Long) entry.getValue();
		}
		else if( key.equals( CalendarContract.Events.DURATION ) )
		{
			duration = (String) entry.getValue();// iCalUtility.parseDuration(
													// (String)entry.getValue()
													// );
		}
		else if( key.equals( CalendarContract.Events.TITLE ) )
		{
			title = (String) entry.getValue();
		}
		else if( key.equals( CalendarContract.Events.DESCRIPTION ) )
		{
			description = (String) entry.getValue();
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder( "-- Event --\n" );

		builder.append( "title: " );
		builder.append( title );
		builder.append( "\n" );

		builder.append( "description: " );
		builder.append( description );
		builder.append( "\n" );

		builder.append( "id: " );
		builder.append( id );
		builder.append( "\n" );

		builder.append( "calendar_id: " );
		builder.append( calendar_id );
		builder.append( "\n" );

		builder.append( "event_timezone: " );
		builder.append( event_timezone );
		builder.append( "\n" );

		builder.append( "dt_start: " );
		builder.append( dt_start );
		builder.append( "\n" );

		builder.append( "dt_end: " );
		builder.append( dt_end );
		builder.append( "\n" );

		builder.append( "duration: " );
		builder.append( duration );
		builder.append( "\n" );

		return builder.toString();
	}

	public int describeContents()
	{
		return 0;
	}

	public void writeToParcel( Parcel dest, int flags )
	{
		dest.writeLong( id );
		dest.writeInt( calendar_id );
		dest.writeString( event_timezone );
		dest.writeLong( dt_start );
		dest.writeLong( dt_end );
		dest.writeString( duration );
		dest.writeString( title );
		dest.writeString( description );
	}

	public static final Parcelable.Creator< Event >	CREATOR	= new EventCreator();

	private static final class EventCreator implements Parcelable.Creator< Event >
	{
		public Event createFromParcel( Parcel in )
		{
			return new Event( in );
		}

		public Event[] newArray( int size )
		{
			return new Event[size];
		}
	}

	@Override
	public boolean equals( Object aThat )
	{
		if( this == aThat )
			return true;
		if( !( aThat instanceof Event ) )
			return false;
		Event that = (Event) aThat;
		return ( id == that.id && calendar_id == that.calendar_id && event_timezone.equals( that.event_timezone )
						&& dt_start == that.dt_start && dt_end == that.dt_end && duration.equals( that.duration )
						&& title.equals( that.title ) && description.equals( that.description ) );
	}
}
