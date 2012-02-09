package com.darkrockstudios.libs.calendar;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Instances;

public class InstanceUtility
{
	public static ArrayList< Instance > getVisibleInstances( Context context, int hours )
	{
		ArrayList< Instance > instances = new ArrayList< Instance >();

		// Specify the date range you want to search for recurring
		// event instances
		Calendar startTime = Calendar.getInstance();
		long startMillis = startTime.getTimeInMillis();

		Calendar endTime = Calendar.getInstance();
		endTime.add( Calendar.HOUR, hours );
		long endMillis = endTime.getTimeInMillis();

		// Submit the query
		ContentResolver cr = context.getContentResolver();
		Cursor cur = Instances.query( cr, null, startMillis, endMillis );

		while( cur.moveToNext() )
		{
			instances.add( new Instance( cur ) );
		}

		cur.close();

		return instances;
	}

	public static ArrayList< Instance > getAllInstances( Context context, int hours )
	{
		ArrayList< Instance > instances = new ArrayList< Instance >();

		// Specify the date range you want to search for recurring
		// event instances
		Calendar startTime = Calendar.getInstance();
		long startMillis = startTime.getTimeInMillis();

		Calendar endTime = Calendar.getInstance();
		endTime.add( Calendar.HOUR, hours );
		long endMillis = endTime.getTimeInMillis();

		// Submit the query
		Uri.Builder uriBuilder = Instances.CONTENT_URI.buildUpon();
		ContentUris.appendId( uriBuilder, startMillis );
		ContentUris.appendId( uriBuilder, endMillis );
		Uri uri = uriBuilder.build();

		ContentResolver cr = context.getContentResolver();
		Cursor cur = cr.query( uri, null, null, null, Instances.BEGIN + " ASC" );

		while( cur.moveToNext() )
		{
			instances.add( new Instance( cur ) );
		}

		cur.close();

		return instances;
	}
}
