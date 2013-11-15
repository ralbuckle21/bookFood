// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends Activity
	implements android.view.View.OnClickListener
{

	ImageView ivOcgSoft;
	TextView tvAboutApplication;

	public AboutActivity()
	{
	}

	private void openBrowser(String s)
	{
		Uri uri = Uri.parse(s);
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setData(uri);
		startActivity(intent);
	}

	public void onClick(View view)
	{
		switch (view.getId())
		{
		case R.id.tvAboutDate: 
		default:
			return;

		case R.id.tvAboutApplication: 
			openBrowser("http://weibo.com/rarnu");
			return;

		case R.id.ivOcgSoft: 
			openBrowser("http://www.ocgsoft.cn");
			break;
		}
	}

	public void onCreate(Bundle bundle)
	{
		requestWindowFeature(1);
		super.onCreate(bundle);
		setContentView(R.layout.about);
		tvAboutApplication = (TextView)findViewById(0x7f0a0005);
		ivOcgSoft = (ImageView)findViewById(0x7f0a0007);
		tvAboutApplication.setOnClickListener(this);
		ivOcgSoft.setOnClickListener(this);
	}
}
