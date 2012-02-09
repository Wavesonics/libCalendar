package com.darkrockstudios.libs.calendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class iCalUtility
{
	public static long parseDuration( String icalDuration )
	{
		final int GROUP_SECONDS = 1;
		final String PATTERN = "-?P(\\d)+S";

		long durationInSeconds = -1;

		Pattern p = Pattern.compile( PATTERN );
		Matcher m = p.matcher( icalDuration );
		if( m.groupCount() > 1 )
		{
			durationInSeconds = Long.parseLong( m.group( GROUP_SECONDS ) );
		}

		return durationInSeconds;
	}
}
