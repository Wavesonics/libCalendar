package com.darkrockstudios.libs.calendar;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.CalendarContract.Instances;

public class Instance implements Parcelable
{
	public long	id;
	public long	begin;
	public long	end;
	public int	endDay;
	public int	endMinute;
	public long	eventId;
	public int	startDay;
	public int	startMinute;

	public Instance(
					long _id,
					long _begin,
					long _end,
					int _endDay,
					int _endMinute,
					long _eventId,
					int _startDay,
					int _startMinute )
	{
		id = _id;
		begin = _begin;
		end = _end;
		endDay = _endDay;
		endMinute = _endMinute;
		eventId = _eventId;
		startDay = _startDay;
		startMinute = _startMinute;
	}

	public Instance( Parcel src )
	{
		id = src.readLong();
		begin = src.readLong();
		end = src.readLong();
		endDay = src.readInt();
		endMinute = src.readInt();
		eventId = src.readLong();
		startDay = src.readInt();
		startMinute = src.readInt();
	}

	public Instance( Cursor cur )
	{
		int colIndex = -1;

		colIndex = cur.getColumnIndex( Instances._ID );
		if( colIndex >= 0 )
		{
			id = cur.getLong( colIndex );
		}

		colIndex = cur.getColumnIndex( Instances.BEGIN );
		if( colIndex >= 0 )
		{
			begin = cur.getLong( colIndex );
		}

		colIndex = cur.getColumnIndex( Instances.END );
		if( colIndex >= 0 )
		{
			end = cur.getLong( colIndex );
		}

		colIndex = cur.getColumnIndex( Instances.END_DAY );
		if( colIndex >= 0 )
		{
			endDay = cur.getInt( colIndex );
		}

		colIndex = cur.getColumnIndex( Instances.END_MINUTE );
		if( colIndex >= 0 )
		{
			endMinute = cur.getInt( colIndex );
		}

		colIndex = cur.getColumnIndex( Instances.EVENT_ID );
		if( colIndex >= 0 )
		{
			eventId = cur.getInt( colIndex );
		}

		colIndex = cur.getColumnIndex( Instances.START_DAY );
		if( colIndex >= 0 )
		{
			startDay = cur.getInt( colIndex );
		}

		colIndex = cur.getColumnIndex( Instances.START_MINUTE );
		if( colIndex >= 0 )
		{
			startMinute = cur.getInt( colIndex );
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder( "-- Instance --\n" );

		builder.append( "begin: " );
		builder.append( begin );
		builder.append( "\n" );

		builder.append( "end: " );
		builder.append( end );
		builder.append( "\n" );

		builder.append( "endDay: " );
		builder.append( endDay );
		builder.append( "\n" );

		builder.append( "endMinute: " );
		builder.append( endMinute );
		builder.append( "\n" );

		builder.append( "eventId: " );
		builder.append( eventId );
		builder.append( "\n" );

		builder.append( "startDay: " );
		builder.append( startDay );
		builder.append( "\n" );

		builder.append( "startMinute: " );
		builder.append( startMinute );
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
		dest.writeLong( begin );
		dest.writeLong( end );
		dest.writeInt( endDay );
		dest.writeInt( endMinute );
		dest.writeLong( eventId );
		dest.writeInt( startDay );
		dest.writeInt( startMinute );
	}

	public static final Parcelable.Creator< Instance >	CREATOR	= new InstanceCreator();

	private static final class InstanceCreator implements Parcelable.Creator< Instance >
	{
		public Instance createFromParcel( Parcel in )
		{
			return new Instance( in );
		}

		public Instance[] newArray( int size )
		{
			return new Instance[size];
		}
	}

	@Override
	public boolean equals( Object aThat )
	{
		if( this == aThat )
			return true;
		if( !( aThat instanceof Instance ) )
			return false;
		Instance that = (Instance) aThat;
		return ( id == that.id && begin == that.begin && end == that.end && endDay == that.endDay
						&& endMinute == that.endMinute && eventId == that.eventId && startDay == that.startDay && startMinute == that.startMinute );
	}
}
