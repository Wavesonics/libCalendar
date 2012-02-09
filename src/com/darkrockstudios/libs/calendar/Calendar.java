package com.darkrockstudios.libs.calendar;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.CalendarContract.Calendars;

public class Calendar implements Parcelable
{
	public long		id;
	public String	name;
	public String	accountName;
	public String	accountType;
	public String	displayName;

	public Calendar( long _id, String _name, String _accountName, String _accountType, String _displayName )
	{
		id = _id;
		name = _name;
		accountName = _accountName;
		accountType = _accountType;
		displayName = _displayName;
	}

	public Calendar( Parcel src )
	{
		id = src.readLong();
		name = src.readString();
		accountName = src.readString();
		accountType = src.readString();
		displayName = src.readString();
	}

	public Calendar( Cursor cur )
	{
		int colIndex = -1;

		colIndex = cur.getColumnIndex( Calendars._ID );
		if( colIndex >= 0 )
		{
			id = cur.getLong( colIndex );
		}

		colIndex = cur.getColumnIndex( Calendars.NAME );
		if( colIndex >= 0 )
		{
			name = cur.getString( colIndex );
		}

		colIndex = cur.getColumnIndex( Calendars.ACCOUNT_NAME );
		if( colIndex >= 0 )
		{
			accountName = cur.getString( colIndex );
		}

		colIndex = cur.getColumnIndex( Calendars.ACCOUNT_TYPE );
		if( colIndex >= 0 )
		{
			accountType = cur.getString( colIndex );
		}

		colIndex = cur.getColumnIndex( Calendars.CALENDAR_DISPLAY_NAME );
		if( colIndex >= 0 )
		{
			displayName = cur.getString( colIndex );
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder( "-- Calendar --\n" );

		builder.append( "id: " );
		builder.append( id );
		builder.append( "\n" );

		builder.append( "name: " );
		builder.append( name );
		builder.append( "\n" );

		builder.append( "accountName: " );
		builder.append( accountName );
		builder.append( "\n" );

		builder.append( "accountType: " );
		builder.append( accountType );
		builder.append( "\n" );

		builder.append( "displayName: " );
		builder.append( displayName );
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
		dest.writeString( name );
		dest.writeString( accountName );
		dest.writeString( accountType );
		dest.writeString( displayName );
	}

	public static final Parcelable.Creator< Calendar >	CREATOR	= new CalendarCreator();

	private static final class CalendarCreator implements Parcelable.Creator< Calendar >
	{
		public Calendar createFromParcel( Parcel in )
		{
			return new Calendar( in );
		}

		public Calendar[] newArray( int size )
		{
			return new Calendar[size];
		}
	}

	@Override
	public boolean equals( Object aThat )
	{
		if( this == aThat )
			return true;
		if( !( aThat instanceof Calendar ) )
			return false;
		Calendar that = (Calendar) aThat;
		return ( id == that.id && id == that.id && name.equals( that.name ) && accountName.equals( that.accountName )
						&& accountType.equals( that.accountType ) && displayName.equals( that.displayName ) );
	}
}