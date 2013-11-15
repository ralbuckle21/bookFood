// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android;

import android.content.res.Resources;
import android.os.Bundle;
import android.preference.*;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.Button;
import com.teacher.android.config.PureListPreference;
import com.teacher.android.config.PurePreference;

public class SettingsActivity extends PreferenceActivity
	implements android.preference.Preference.OnPreferenceChangeListener, android.view.View.OnClickListener
{

	Button btnSetLeft;
	PurePreference pFit;
	PureListPreference pHome;

	public SettingsActivity()
	{
	}

	private void checkSystem()
	{
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int i = displaymetrics.widthPixels;
		int j = displaymetrics.heightPixels;
		float f = displaymetrics.density;
		int k = displaymetrics.densityDpi;
		int l = 0;
		if (i >= 320)
		{
			l = 0;
			if (j >= 480)
				l = 0 + 1;
		}
		if (f >= 1.0F)
			l++;
		if (k >= 160)
			l++;
		switch (l)
		{
		default:
			return;

		case 0: // '\0'
			pFit.setSummary(R.string.pref_fit_s0);
			return;

		case 1: // '\001'
			pFit.setSummary(R.string.pref_fit_s1);
			return;

		case 2: // '\002'
			pFit.setSummary(R.string.pref_fit_s2);
			return;

		case 3: // '\003'
			pFit.setSummary(R.string.pref_fit_s3);
			break;
		}
	}

	public void onClick(View view)
	{
		switch (view.getId())
		{
		default:
			return;

		case R.id.btnSetLeft: 
			finish();
			break;
		}
	}

	public void onCreate(Bundle bundle)
	{
		requestWindowFeature(1);
		super.onCreate(bundle);
		setContentView(R.layout.preference);
		addPreferencesFromResource(R.xml.settings);
		pHome = (PureListPreference)findPreference(getResources().getString(R.string.id_home));
		pHome.setSummary(getResources().getStringArray(R.array.entry_name)[Integer.parseInt(pHome.getValue())]);
		pHome.setOnPreferenceChangeListener(this);
		pFit = (PurePreference)findPreference(getResources().getString(R.string.id_fit));
		btnSetLeft = (Button)findViewById(R.id.btnSetLeft);
		btnSetLeft.setOnClickListener(this);
		checkSystem();
	}

	public boolean onPreferenceChange(Preference preference, Object obj)
	{
		if (preference.equals(pHome))
		{
			preference.setSummary(getResources().getStringArray(R.array.entry_name)[Integer.parseInt((String)obj)]);
			((ListPreference)preference).setValueIndex(Integer.parseInt((String)obj));
		}
		return false;
	}
}
