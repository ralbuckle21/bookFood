// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.teacher.android.database.teacherUtils;
import com.teacher.android.define.FieldDefine;

// Referenced classes of package com.teacher.android:
//			CardInfoActivity

public class AssignedActivity extends Activity
	implements android.view.View.OnClickListener, android.widget.AdapterView.OnItemClickListener
{

	SimpleCursorAdapter adaAssigned;
	Button btnAssignedLeft;
	Cursor cAssigned;
	ListView lvAssignedCard;
	TextView tvAssignedMasterName;

	public AssignedActivity()
	{
		cAssigned = null;
		adaAssigned = null;
	}

	public void onClick(View view)
	{
		switch (view.getId())
		{
		default:
			return;

		case R.id.btnAssignedLeft: 
		}
		finish();
	}

	public void onCreate(Bundle bundle)
	{
		requestWindowFeature(1);
		super.onCreate(bundle);
		setContentView(R.layout.assignedcard);
		tvAssignedMasterName = (TextView)findViewById(R.id.tvAssignedMasterName);
		btnAssignedLeft = (Button)findViewById(R.id.btnAssignedLeft);
		lvAssignedCard = (ListView)findViewById(R.id.lvAssignedCard);
		btnAssignedLeft.setOnClickListener(this);
		lvAssignedCard.setOnItemClickListener(this);
		String s = getResources().getString(R.string.assigned_to);
		Object aobj[] = new Object[1];
		aobj[0] = getIntent().getStringExtra("name");
		String s1 = String.format(s, aobj);
		tvAssignedMasterName.setText(s1);
		cAssigned = teacherUtils.getAssignedCards(this, getIntent().getStringExtra("union"));
		Cursor cursor = cAssigned;
		String as[] = new String[2];
		as[0] = FieldDefine.DataFields[5];
		as[1] = FieldDefine.DataFields[10];
		adaAssigned = new SimpleCursorAdapter(this, R.layout.carditem, cursor, as, new int[] {
			R.id.tvCardName, R.id.tvCardType
		});
		lvAssignedCard.setAdapter(adaAssigned);
	}

	public void onItemClick(AdapterView adapterview, View view, int i, long l)
	{
		//Intent intent = new Intent(this, com/teacher/android/CardInfoActivity);
		//Intent intent = new Intent();
		//intent.setClass(this,CardInfoActivity.class);
		Intent intent = new Intent(this, CardInfoActivity.class);
		cAssigned.moveToPosition(i);
		intent.putExtra("CardId", cAssigned.getInt(0));
		startActivity(intent);
	}
}
