// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

// Referenced classes of package com.teacher.android.database:
//			teacherDatabase

public class teacherProvider extends ContentProvider
{

	public static final int ACTIONID_CARDCOUNT = -4;
	public static final int ACTIONID_CLOSEDATABASE = -99;
	public static final int ACTIONID_EFFECTLIST = -3;
	public static final int ACTIONID_SEARCH = -1;
	public static final int ACTIONID_TOP100 = -2;
	public static final Uri CONTENT_URI = Uri.parse("content://com.teacher.card");
	private teacherDatabase database;

	public teacherProvider()
	{
		database = null;
	}

	public int delete(Uri uri, String s, String as[])
	{
		return 0;
	}

	public String getType(Uri uri)
	{
		return null;
	}

	public Uri insert(Uri uri, ContentValues contentvalues)
	{
		return null;
	}

	public boolean onCreate()
	{
		if (database == null)
			try
			{
				database = new teacherDatabase(getContext());
			}
			catch (Exception exception) { }
		return database != null;
	}

	public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
	{
		return database.doQuery(uri, as, s, as1, s1);
	}

	public int update(Uri uri, ContentValues contentvalues, String s, String as[])
	{
		return 0;
	}

}
