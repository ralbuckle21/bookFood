// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ConfigUtils
{

	public ConfigUtils()
	{
	}

	public static int getConfig(Context context, String s, int i)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getInt(s, i);
	}

	public static String getConfig(Context context, String s, String s1)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getString(s, s1);
	}

	public static boolean getConfig(Context context, String s, boolean flag)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(s, flag);
	}

	public static void setConfig(Context context, String s, int i)
	{
		PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(s, i).commit();
	}

	public static void setConfig(Context context, String s, String s1)
	{
		PreferenceManager.getDefaultSharedPreferences(context).edit().putString(s, s1).commit();
	}

	public static void setConfig(Context context, String s, boolean flag)
	{
		PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(s, flag).commit();
	}
}
