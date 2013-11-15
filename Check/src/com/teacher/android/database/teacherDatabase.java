// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android.database;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import java.io.File;

import com.teacher.android.R;

public class teacherDatabase
{

	private SQLiteDatabase database;

	public teacherDatabase(Context context)
		throws Exception
	{
		if (!(new File("/sdcard/.teacher/teacher.db")).exists())
		{
			throw new Exception(context.getResources().getString(R.string.error_no_database));
		} else
		{
			database = SQLiteDatabase.openDatabase("/sdcard/.teacher/teacher.db", null, 1);
			return;
		}
	}

	public Cursor doQuery(Uri uri, String as[], String s, String as1[], String s1)
	{
		int i = -99;
		long l = ContentUris.parseId(uri);
		i = (int)l;
//_L2:
	try{
		if (i == -99)
		{
			database.close();
			return null;
		}
		if (i == -4)
			return database.rawQuery("select CardID from YGODATA order by CardID desc limit 0,1", null);
		if (i == -3)
			return database.rawQuery("select * from YGOEFFECT", null);
		if (i == -2)
			return database.rawQuery("select _id, SCCardName, SCCardType from YGODATA order by _id desc limit 0,100 ", null);
		if (i == -1)
			return database.query("YGODATA", as, s, as1, null, null, s1);
		if (i >= 0)
		{
			SQLiteDatabase sqlitedatabase = database;
			String as2[] = new String[1];
			as2[0] = String.valueOf(i);
			return sqlitedatabase.rawQuery("select * from YGODATA where CardID=?", as2);
		} else
		{
			return null;
		}
	}
		catch(Exception exception){
			exception.printStackTrace();
			return null;
		}
		/*Exception exception;
		exception;
		if (true) goto _L2; else goto _L1*/
//_L1:
	}
}
