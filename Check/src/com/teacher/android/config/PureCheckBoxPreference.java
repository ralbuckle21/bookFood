// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android.config;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PureCheckBoxPreference extends CheckBoxPreference
{

	public PureCheckBoxPreference(Context context)
	{
		super(context);
	}

	public PureCheckBoxPreference(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
	}

	public PureCheckBoxPreference(Context context, AttributeSet attributeset, int i)
	{
		super(context, attributeset, i);
	}

	public View getView(View view, ViewGroup viewgroup)
	{
		View view1 = super.getView(view, viewgroup);
		if (view1 != null)
		{
			TextView textview = (TextView)view1.findViewById(0x1020016);
			if (textview != null)
				textview.setTextColor(0xff000000);
		}
		return view1;
	}
}
