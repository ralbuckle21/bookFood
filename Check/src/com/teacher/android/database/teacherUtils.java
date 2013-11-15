// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android.database;

import android.app.Activity;
import android.content.ContentUris;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;

import com.teacher.android.define.FieldDefine;
import com.teacher.android.pojo.CardInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.teacher.android.database:
//			teacherProvider

public class teacherUtils
{

	public teacherUtils()
	{
	}

	public static void closeDatabase(Activity activity)
	{
		activity.managedQuery(ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -99L), null, null, null, null);
	}

	public static CardInfo cursorToCardInfo(Cursor cursor)
		throws Exception
	{
		CardInfo cardinfo = new CardInfo();
		Class class1 = cardinfo.getClass();
		Field afield[] = class1.getDeclaredFields();
		int i = afield.length;
		int j = 0;
		do
		{
			if (j >= i)
				return cardinfo;
			Field field = afield[j];
			String s = (new StringBuilder("set")).append(field.getName()).toString();
			Class aclass[] = new Class[1];
			aclass[0] = field.getType();
			Method method = class1.getMethod(s, aclass);
			if (field.getType().getName().contains("String"))
			{
				Object aobj1[] = new Object[1];
				aobj1[0] = cursor.getString(cursor.getColumnIndex(field.getName()));
				method.invoke(cardinfo, aobj1);
			} else
			{
				Object aobj[] = new Object[1];
				aobj[0] = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(field.getName())));
				method.invoke(cardinfo, aobj);
			}
			j++;
		} while (true);
	}

	public static Cursor getAssignedCards(Activity activity, String s)
	{
		String s1 = (new StringBuilder("CardID in (")).append(s).append(")").toString();
		android.net.Uri uri = ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -1L);
		String as[] = new String[3];
		as[0] = FieldDefine.DataFields[0];
		as[1] = FieldDefine.DataFields[5];
		as[2] = FieldDefine.DataFields[10];
		return activity.managedQuery(uri, as, s1, null, null);
	}

	public static Cursor getBannedCards(Activity activity)
	{
		String as[] = new String[1];
		as[0] = activity.getResources().getString(0x7f070033);
		android.net.Uri uri = ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -1L);
		String as1[] = new String[3];
		as1[0] = FieldDefine.DataFields[0];
		as1[1] = FieldDefine.DataFields[5];
		as1[2] = FieldDefine.DataFields[10];
		return activity.managedQuery(uri, as1, "SCCardBan=?", as, "ENCardType asc");
	}

	public static Cursor getCards(Activity activity, String s, String s1, int i, String s2, String s3, String s4, String s5, 
			String s6, String s7, String s8, String s9)
	{
		String s10 = "1=1";
		boolean flag = s.equals("");
		int j = 0;
		if (!flag)
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and SCCardType like ?").toString();
			j = 0 + 1;
		}
		if (!s1.equals(""))
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and SCCardAttribute=?").toString();
			j++;
		}
		if (!s2.equals(""))
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and SCCardRace=?").toString();
			j++;
		}
		if (i != 0)
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and CardStarNum=?").toString();
			j++;
		}
		if (!s3.equals(""))
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and (SCCardName like ? or CardOnceName like ? or CardAbbrName like ?)").toString();
			j += 3;
		}
		if (!s4.equals(""))
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and (CardEfficeType like ? or CardEfficeType like ? or CardEfficeType like ? or CardEfficeType=?)").toString();
			j += 4;
		}
		if (!s7.equals(""))
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and SCCardRare like ?").toString();
			j++;
		}
		if (!s8.equals(""))
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and CardCamp=?").toString();
			j++;
		}
		int k;
		if (!s5.equals(""))
		{
			String as[];
			android.net.Uri uri;
			String as1[];
			int l;
			int i1;
			int j1;
			int k1;
			int l1;
			if (isNumeric(s5))
				s10 = (new StringBuilder(String.valueOf(s10))).append(" and CardAtk=?").toString();
			else
				s10 = (new StringBuilder(String.valueOf(s10))).append(" and CardAtk2=?").toString();
			j++;
		}
		if (!s6.equals(""))
		{
			if (isNumeric(s6))
				s10 = (new StringBuilder(String.valueOf(s10))).append(" and CardDef=?").toString();
			else
				s10 = (new StringBuilder(String.valueOf(s10))).append(" and CardDef2=?").toString();
			j++;
		}
		if (!s9.equals(""))
		{
			s10 = (new StringBuilder(String.valueOf(s10))).append(" and SCCardBan=?").toString();
			j++;
		}
		String[] as = new String[j];
		if (!s.equals(""))
		{
			as[0] = (new StringBuilder("%")).append(s).append("%").toString();
			k = 0 + 1;
		} else
		{
			k = 0;
		}
		if (!s1.equals(""))
		{
			as[k] = s1;
			k++;
		}
		if (!s2.equals(""))
		{
			as[k] = s2;
			k++;
		}
		if (i != 0)
		{
			as[k] = String.valueOf(i);
			k++;
		}
		if (!s3.equals(""))
		{
			as[k] = (new StringBuilder("%")).append(s3).append("%").toString();
			int k1 = k + 1;
			as[k1] = (new StringBuilder("%")).append(s3).append("%").toString();
			int l1 = k1 + 1;
			as[l1] = (new StringBuilder("%")).append(s3).append("%").toString();
			k = l1 + 1;
		}
		if (!s4.equals(""))
		{
			as[k] = (new StringBuilder("%,")).append(s4).append(",%").toString();
			int l = k + 1;
			as[l] = (new StringBuilder(String.valueOf(s4))).append(",%").toString();
			int i1 = l + 1;
			as[i1] = (new StringBuilder("%,")).append(s4).toString();
			int j1 = i1 + 1;
			as[j1] = s4;
			k = j1 + 1;
		}
		if (!s7.equals(""))
		{
			as[k] = (new StringBuilder("%")).append(s7).append("%").toString();
			k++;
		}
		if (!s8.equals(""))
		{
			as[k] = s8;
			k++;
		}
		if (!s5.equals(""))
		{
			as[k] = s5;
			k++;
		}
		if (!s6.equals(""))
		{
			as[k] = s6;
			k++;
		}
		if (!s9.equals(""))
		{
			as[k] = s9;
			//int  = k + 1;
		}
		Uri uri = ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -1L);
		String[] as1 = new String[3];
		as1[0] = FieldDefine.DataFields[0];
		as1[1] = FieldDefine.DataFields[5];
		as1[2] = FieldDefine.DataFields[10];
		return activity.managedQuery(uri, as1, s10, as, null);
	}

	public static List getEffectList(Activity activity)
	{
		Cursor cursor;
		ArrayList arraylist;
		cursor = activity.managedQuery(ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -3L), null, null, null, null);
		arraylist = null;
		if (cursor == null) return arraylist; 
		else{
//_L1:
		arraylist = new ArrayList();
		cursor.moveToFirst();
		}
_L5:
		if (!cursor.isAfterLast())
		{
			arraylist.add(cursor.getString(cursor.getColumnIndex(FieldDefine.EffectFields[2])));
			cursor.moveToNext();
		}
		else return arraylist;
//_L2:
	//	return arraylist;
/*_L3:
		arraylist.add(cursor.getString(cursor.getColumnIndex(FieldDefine.EffectFields[2])));
		cursor.moveToNext();*/
		//if (true) goto _L5; else goto _L4
//_L4:
return null;
	}

	public static int getLastCardId(Activity activity)
	{
		Cursor cursor = activity.managedQuery(ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -4L), null, null, null, null);
		int i = 0;
		if (cursor != null)
		{
			cursor.moveToFirst();
			boolean flag = cursor.isAfterLast();
			i = 0;
			if (!flag)
				i = cursor.getInt(0);
		}
		return i;
	}

	public static Cursor getLatest100(Activity activity)
	{
		return activity.managedQuery(ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -2L), null, null, null, null);
	}

	public static Cursor getLimit1Cards(Activity activity)
	{
		String as[] = new String[1];
		as[0] = activity.getResources().getString(0x7f070034);
		android.net.Uri uri = ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -1L);
		String as1[] = new String[3];
		as1[0] = FieldDefine.DataFields[0];
		as1[1] = FieldDefine.DataFields[5];
		as1[2] = FieldDefine.DataFields[10];
		return activity.managedQuery(uri, as1, "SCCardBan=?", as, "ENCardType asc");
	}

	public static Cursor getLimit2Cards(Activity activity)
	{
		String as[] = new String[1];
		as[0] = activity.getResources().getString(0x7f070035);
		android.net.Uri uri = ContentUris.withAppendedId(teacherProvider.CONTENT_URI, -1L);
		String as1[] = new String[3];
		as1[0] = FieldDefine.DataFields[0];
		as1[1] = FieldDefine.DataFields[5];
		as1[2] = FieldDefine.DataFields[10];
		return activity.managedQuery(uri, as1, "SCCardBan=?", as, "ENCardType asc");
	}

	public static CardInfo getOneCard(Activity activity, int i) throws Exception
	{
		Cursor cursor;
		CardInfo cardinfo;
		cursor = activity.managedQuery(ContentUris.withAppendedId(teacherProvider.CONTENT_URI, i), null, null, null, null);
		cardinfo = null;
		if (cursor == null) //goto _L2; else goto _L1
		{
			return cardinfo;
		}
		else
		{
			cursor.moveToFirst();
			boolean flag = cursor.isAfterLast();
			cardinfo = null;
			//if (flag) break;
				//break MISSING_BLOCK_LABEL_54;
			CardInfo cardinfo1 = cursorToCardInfo(cursor);
			cardinfo = cardinfo1;
		}
/*_L1:
		cursor.moveToFirst();
		boolean flag = cursor.isAfterLast();
		cardinfo = null;
		if (flag)
			break MISSING_BLOCK_LABEL_54;
		CardInfo cardinfo1 = cursorToCardInfo(cursor);
		cardinfo = cardinfo1;
_L4:
		cursor.close();
_L2:
		return cardinfo;*/
		//Exception exception;
		//exception;
		//cardinfo = null;
		//if (true) goto _L4; else goto _L3
//_L3:
		return cardinfo;
	}

	private static boolean isNumeric(String s)
	{
		return s.matches("\\d*");
	}
}
