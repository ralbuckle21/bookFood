// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.teacher.android.database.teacherUtils;
import com.teacher.android.pojo.CardInfo;
import java.io.File;

// Referenced classes of package com.teacher.android:
//			AssignedActivity

public class CardInfoActivity extends Activity
	implements android.view.View.OnClickListener
{

	RelativeLayout btnAdjust;
	Button btnAssignedCard;
	RelativeLayout btnCard;
	Button btnCardLeft;
	RelativeLayout btnPic;
	CardInfo info;
	ImageView ivImage;
	RelativeLayout layAdjust;
	RelativeLayout layCard;
	RelativeLayout layPic;
	TextView tvAdjust;
	TextView tvInfo;
	TextView tvNoAdjust;
	TextView tvNoPic;

	public CardInfoActivity()
	{
	}

	private String buildCardInfo(CardInfo cardinfo)
	{
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(buildInfoLine(R.string.name, cardinfo.getSCCardName()));
		stringbuilder.append(buildInfoLine(R.string.japan_name, cardinfo.getJPCardName()));
		stringbuilder.append(buildInfoLine(R.string.english_name, cardinfo.getENCardName()));
		stringbuilder.append(buildInfoLine(R.string.type, cardinfo.getSCCardType()));
		Object[] arrayOfObject;
		if (cardinfo.getSCCardType().contains(getResources().getString(0x7f070068)))
		{
			stringbuilder.append(buildInfoLine(R.string.split, ""));
			stringbuilder.append(buildInfoLine(R.string.attribute, cardinfo.getSCCardAttribute()));
			Object aobj[] = new Object[2];
			aobj[0] = Integer.valueOf(cardinfo.getCardStarNum());
			if (!cardinfo.getSCCardType().contains(getResources().getString(R.string.overlay)))
			{
				for(String s = getResources().getString(R.string.lad);; s="")
				{
					aobj[1] = s;
					stringbuilder.append(buildInfoLine(R.string.level, String.format("%d %s", aobj)));
					stringbuilder.append(buildInfoLine(R.string.race, cardinfo.getSCCardRace()));
					stringbuilder.append(buildInfoLine(R.string.attack, cardinfo.getCardAtk2()));
					stringbuilder.append(buildInfoLine(R.string.defense, cardinfo.getCardDef2()));
					stringbuilder.append(buildInfoLine(R.string.split, ""));
					stringbuilder.append(buildInfoLine(R.string.limit, cardinfo.getSCCardBan()));
					stringbuilder.append(buildInfoLine(R.string.pack, cardinfo.getCardBagNum()));
					stringbuilder.append(buildInfoLine(R.string.belongs, cardinfo.getCardCamp()));
					stringbuilder.append(buildInfoLine(R.string.password, cardinfo.getCardPass()));
					stringbuilder.append(buildInfoLine(R.string.rare, cardinfo.getSCCardRare()));
					stringbuilder.append(buildInfoLine(R.string.split, ""));
					stringbuilder.append(buildInfoLine(R.string.effect, cardinfo.getSCCardDepict()));
					return stringbuilder.toString();
				}
			}
		}
		/*if (cardinfo.getSCCardType().contains(getResources().getString(0x7f070068)))
		{
			stringbuilder.append(buildInfoLine(R.string.split, ""));
			stringbuilder.append(buildInfoLine(R.string.attribute, cardinfo.getSCCardAttribute()));
			Object aobj[] = new Object[2];
			aobj[0] = Integer.valueOf(cardinfo.getCardStarNum());
			String s;
			if (cardinfo.getSCCardType().contains(getResources().getString(0x7f07006b)))
				s = getResources().getString(0x7f07006c);
			else
				s = "";
			aobj[1] = s;
			stringbuilder.append(buildInfoLine(R.string.level, String.format("%d %s", aobj)));
			stringbuilder.append(buildInfoLine(R.string.race, cardinfo.getSCCardRace()));
			stringbuilder.append(buildInfoLine(R.string.attack, cardinfo.getCardAtk2()));
			stringbuilder.append(buildInfoLine(R.string.defense, cardinfo.getCardDef2()));
		}
		stringbuilder.append(buildInfoLine(0x7f07006d, ""));
		stringbuilder.append(buildInfoLine(0x7f070059, cardinfo.getSCCardBan()));
		stringbuilder.append(buildInfoLine(0x7f07005c, cardinfo.getCardBagNum()));
		stringbuilder.append(buildInfoLine(0x7f07005b, cardinfo.getCardCamp()));
		stringbuilder.append(buildInfoLine(0x7f07005a, cardinfo.getCardPass()));
		stringbuilder.append(buildInfoLine(0x7f07005d, cardinfo.getSCCardRare()));
		stringbuilder.append(buildInfoLine(0x7f07006d, ""));
		stringbuilder.append(buildInfoLine(0x7f070067, cardinfo.getSCCardDepict()));
		return stringbuilder.toString();*/
		return null;
	}

	private String buildInfoLine(int i, String s)
	{
		String s1 = getResources().getString(i);
		if (i != R.string.split)
			s1 = (new StringBuilder(String.valueOf(s1))).append(": ").append(s).toString();
		return (new StringBuilder(String.valueOf(s1))).append("\n").toString();
	}

	private void doSearch(int i) throws Exception
	{
		info = teacherUtils.getOneCard(this, i);
		if (info == null)
		{
			ivImage.setVisibility(8);
			tvInfo.setText("not found");
			return;
		}
		tvInfo.setText(buildCardInfo(info));
		tvAdjust.setText(info.getCardAdjust());
		TextView textview = tvNoAdjust;
		int j;
		StringBuilder stringbuilder;
		Object aobj[];
		String s;
		if (info.getCardAdjust() == null || info.getCardAdjust().trim().equals(""))
			j = 0;
		else
			j = 8;
		textview.setVisibility(j);
		stringbuilder = new StringBuilder("/sdcard/.teacher/images/");
		aobj = new Object[1];
		aobj[0] = Integer.valueOf(info.getCardID() - 1);
		s = stringbuilder.append(String.format("%d.jpg", aobj)).toString();
		if ((new File(s)).exists())
		{
			android.graphics.Bitmap bitmap = BitmapFactory.decodeFile(s);
			ivImage.setImageBitmap(bitmap);
			ivImage.setVisibility(0);
			tvNoPic.setVisibility(8);
			return;
		} else
		{
			ivImage.setVisibility(8);
			tvNoPic.setVisibility(0);
			return;
		}
	}

	private void navigateTo(int i)
	{
		btnCard.setBackgroundDrawable(null);
		btnAdjust.setBackgroundDrawable(null);
		btnPic.setBackgroundDrawable(null);
		layCard.setVisibility(8);
		layAdjust.setVisibility(8);
		layPic.setVisibility(8);
		switch (i)
		{
		default:
			return;

		case 1: // '\001'
			btnCard.setBackgroundResource(0x7f02000f);
			layCard.setVisibility(0);
			return;

		case 2: // '\002'
			btnAdjust.setBackgroundResource(0x7f02000f);
			layAdjust.setVisibility(0);
			return;

		case 3: // '\003'
			btnPic.setBackgroundResource(0x7f02000f);
			break;
		}
		layPic.setVisibility(0);
	}

	private void setBottomBar()
	{
		int i = (getWindowManager().getDefaultDisplay().getWidth() - 36) / 3;
		android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)btnCard.getLayoutParams();
		layoutparams.width = i;
		btnCard.setLayoutParams(layoutparams);
		android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)btnAdjust.getLayoutParams();
		layoutparams1.width = i;
		btnAdjust.setLayoutParams(layoutparams1);
		android.widget.LinearLayout.LayoutParams layoutparams2 = (android.widget.LinearLayout.LayoutParams)btnPic.getLayoutParams();
		layoutparams2.width = i;
		btnPic.setLayoutParams(layoutparams2);
		((ImageView)btnCard.findViewById(R.id.imgItemIco)).setBackgroundResource(R.drawable.home);
		((ImageView)btnAdjust.findViewById(R.id.imgItemIco)).setBackgroundResource(R.drawable.adjust);
		((ImageView)btnPic.findViewById(R.id.imgItemIco)).setBackgroundResource(R.drawable.pic);
		((TextView)btnCard.findViewById(R.id.tvItemName)).setText(R.string.itm_card);
		((TextView)btnAdjust.findViewById(R.id.tvItemName)).setText(R.string.itm_adjust);
		((TextView)btnPic.findViewById(R.id.tvItemName)).setText(R.string.itm_pic);
	}

	public void onClick(View view)
	{
		switch(view.getId())
		{
		default:
			return;
	    case R.id.btnCardInfo:
	    	navigateTo(1);
			return;
	    case R.id.btnAdjust:
	    	navigateTo(2);
			return;
	    case R.id.btnPic:
	    	navigateTo(3);
			return;
	    case R.id.btnAssignedCard:
	    	if (info != null)
				if (info.getCardUnion() != null && !info.getCardUnion().equals(""))
				{
					Intent intent = new Intent(this, AssignedActivity.class);
					intent.putExtra("name", info.getSCCardName());
					intent.putExtra("union", info.getCardUnion());
					startActivity(intent);
					return;
				} else
				{
					Toast.makeText(this, R.string.no_assigned_card, 1).show();
					return;
				}
	    case R.id.btnCardLeft:
	    	finish();
			return;
		}
		/*
		JVM INSTR lookupswitch 5: default 56
	//	               2131361808: 167
	//	               2131361815: 57
	//	               2131361816: 63
	//	               2131361817: 69
	//	               2131361820: 75;
		   goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
		return;
_L3:
		navigateTo(1);
		return;
_L4:
		navigateTo(2);
		return;
_L5:
		navigateTo(3);
		return;
_L6:
		if (info != null)
			if (info.getCardUnion() != null && !info.getCardUnion().equals(""))
			{
				Intent intent = new Intent(this, com/teacher/android/AssignedActivity);
				intent.putExtra("name", info.getSCCardName());
				intent.putExtra("union", info.getCardUnion());
				startActivity(intent);
				return;
			} else
			{
				Toast.makeText(this, 0x7f07006f, 1).show();
				return;
			}
		  goto _L1
_L2:
		finish();
		return;*/
		
	}

	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		requestWindowFeature(1);
		setContentView(R.layout.cardinfo);
		tvInfo = (TextView)findViewById(R.id.tvInfo);
		tvAdjust = (TextView)findViewById(R.id.tvAdjust);
		ivImage = (ImageView)findViewById(R.id.ivImage);
		tvNoAdjust = (TextView)findViewById(R.id.tvNoAdjust);
		tvNoPic = (TextView)findViewById(R.id.tvNoPic);
		btnCard = (RelativeLayout)findViewById(R.id.btnCardInfo);
		btnAdjust = (RelativeLayout)findViewById(R.id.btnAdjust);
		btnPic = (RelativeLayout)findViewById(R.id.btnPic);
		layCard = (RelativeLayout)findViewById(R.id.lay_card);
		layAdjust = (RelativeLayout)findViewById(R.id.lay_adjust);
		layPic = (RelativeLayout)findViewById(R.id.lay_pic);
		btnAssignedCard = (Button)findViewById(R.id.btnAssignedCard);
		btnCardLeft = (Button)findViewById(R.id.btnCardLeft);
		setBottomBar();
		btnCard.setOnClickListener(this);
		btnAdjust.setOnClickListener(this);
		btnPic.setOnClickListener(this);
		btnAssignedCard.setOnClickListener(this);
		btnCardLeft.setOnClickListener(this);
		if (getIntent() != null)
			try {
				doSearch(getIntent().getIntExtra("CardId", -1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		navigateTo(1);
	}
}
