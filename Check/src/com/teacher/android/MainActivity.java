// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.*;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import com.teacher.android.config.ConfigUtils;
import com.teacher.android.database.teacherUtils;
import com.teacher.android.define.CardConstDefine;
import com.teacher.android.define.FieldDefine;
import com.teacher.android.network.NetworkUtils;
import com.teacher.android.pojo.UpdateInfo;
import java.io.*;
import java.util.List;

// Referenced classes of package com.teacher.android:
//			CoinDiceActivity, CardInfoActivity, SettingsActivity, AboutActivity, 
//			UpdateActivity

public class MainActivity extends Activity
	implements android.widget.AdapterView.OnItemClickListener, android.view.View.OnClickListener, android.widget.AdapterView.OnItemSelectedListener
{

	static int Player1Life = 8000;
	static int Player2Life = 8000;
	static int apkVersion = 9;
	SimpleCursorAdapter adapterSearchResult;
	SimpleCursorAdapter adapterTop100;
	Button btnCoin;
	Button btnDice;
	RelativeLayout btnHome;
	Button btnLeft;
	RelativeLayout btnList;
	Button btnP1Add;
	Button btnP1Divide;
	Button btnP1Double;
	Button btnP1Half;
	Button btnP1Minus;
	Button btnP1Opt;
	Button btnP1Set;
	Button btnP2Add;
	Button btnP2Divide;
	Button btnP2Double;
	Button btnP2Half;
	Button btnP2Minus;
	Button btnP2Opt;
	Button btnP2Set;
	Button btnRestartDuel;
	Button btnRight;
	RelativeLayout btnSearch;
	Button btnSearchCard;
	Button btnSearchReset;
	RelativeLayout btnTool;
	Cursor c;
	Cursor cBanned;
	Cursor cLimit1;
	Cursor cLimit2;
	Cursor cSearchResult;
	int currentHomePage;
	EditText etCardAttack;
	EditText etCardDefense;
	EditText etCardName;
	EditText etP1Life;
	EditText etP2Life;
	RelativeLayout layHome;
	RelativeLayout layLimit;
	RelativeLayout layList;
	RelativeLayout laySearch;
	RelativeLayout layTool;
	RelativeLayout layTop100;
	ListView lvBanned;
	ListView lvCard;
	ListView lvLimit1;
	ListView lvLimit2;
	ListView lvList;
	Spinner spCardAttribute;
	Spinner spCardBelongs;
	Spinner spCardEffect;
	Spinner spCardLevel;
	Spinner spCardLimit;
	Spinner spCardRace;
	Spinner spCardRare;
	Spinner spCardTunner;
	Spinner spCardType;
	ScrollView svLimit;
	TextView tvListNoCard;
	TextView tvPageName;
	TextView tvPlayer1Life;
	TextView tvPlayer2Life;
	WebView wvUpdateHistory;

	public MainActivity()
	{
		currentHomePage = 1;
		c = null;
		cSearchResult = null;
		cBanned = null;
		cLimit1 = null;
		cLimit2 = null;
		adapterSearchResult = null;
		adapterTop100 = null;
	}

	private void checkUpdateT()
	{
		(new Thread(new Runnable() {
			//final MainActivity this$0;
			
			private final Handler h=new Handler();
			public void run()
			{
				int i = teacherUtils.getLastCardId(MainActivity.this);
				UpdateInfo updateinfo = NetworkUtils.findUpdate(MainActivity.apkVersion, i);
				Message message = new Message();
				message.what = 1;
				message.obj = updateinfo;
				//h.sendMessage(message);
				h.sendMessage(message);
			}

			
		})).start();
	}

	public static int dipToPx(float f, int i)
	{
		return (int)(0.5F + f * (float)i);
	}

	private void doSearchCard()
	{
		String s = etCardName.getText().toString();
		String s1 = "";
		if (spCardType.getSelectedItemPosition() != 0)
			s1 = (String)spCardType.getSelectedItem();
		String s2 = "";
		if (spCardAttribute.getSelectedItemPosition() != 0)
			s2 = (String)spCardAttribute.getSelectedItem();
		int i = spCardLevel.getSelectedItemPosition();
		int j = 0;
		if (i != 0)
			j = Integer.parseInt((String)spCardLevel.getSelectedItem());
		String s3 = "";
		if (spCardRare.getSelectedItemPosition() != 0)
			s3 = (String)spCardRare.getSelectedItem();
		String s4 = "";
		if (spCardRace.getSelectedItemPosition() != 0)
			s4 = (String)spCardRace.getSelectedItem();
		String s5 = "";
		if (spCardBelongs.getSelectedItemPosition() != 0)
			s5 = (String)spCardBelongs.getSelectedItem();
		String s6 = etCardAttack.getText().toString();
		String s7 = etCardDefense.getText().toString();
		String s8 = "";
		if (spCardEffect.getSelectedItemPosition() != 0)
		{
			s8 = String.valueOf(spCardEffect.getSelectedItemPosition());
			if (spCardEffect.getSelectedItemPosition() < 10)
				s8 = (new StringBuilder("0")).append(s8).toString();
		}
		String s9 = "";
		if (spCardLimit.getSelectedItemPosition() != 0)
			s9 = (String)spCardLimit.getSelectedItem();
		cSearchResult = teacherUtils.getCards(this, s1, s2, j, s4, s, s8, s6, s7, s3, s5, s9);
		Cursor cursor = cSearchResult;
		String as[] = new String[2];
		as[0] = FieldDefine.DataFields[5];
		as[1] = FieldDefine.DataFields[10];
		adapterSearchResult = new SimpleCursorAdapter(this, R.layout.carditem, cursor, as, new int[] {
			R.id.tvCardName, R.id.tvCardType
		});
		lvList.setAdapter(adapterSearchResult);
		TextView textview = tvListNoCard;
		int k;
		if (adapterSearchResult.getCount() == 0)
			k = 0;
		else
			k = 8;
		textview.setVisibility(k);
		navigateTo(3);
	}

	private void doSearchReset()
	{
		spCardAttribute.setSelection(0);
		spCardBelongs.setSelection(0);
		spCardEffect.setSelection(0);
		spCardLevel.setSelection(0);
		spCardLimit.setSelection(0);
		spCardRace.setSelection(0);
		spCardRare.setSelection(0);
		spCardTunner.setSelection(0);
		spCardType.setSelection(0);
		etCardName.setText("");
		etCardAttack.setText("");
		etCardDefense.setText("");
	}

	private void navigateHomeTo(int i)
	{
		currentHomePage = i;
		layTop100.setVisibility(8);
		layLimit.setVisibility(8);
		wvUpdateHistory.setVisibility(8);
		btnLeft.setText("");
		btnRight.setText("");
		switch (i)
		{
		default:
			return;

		case 0: // '\0'
			btnLeft.setText(R.string.btn_page_3);
			btnRight.setText(R.string.btn_page_2);
			layLimit.setVisibility(0);
			svLimit.post(new Runnable() {

				//final MainActivity this$0;

				public void run()
				{
					svLimit.scrollTo(0, 0);
				}

			
			/*{
				this$0 = MainActivity.this;
				super();
			}*/
			});
			return;

		case 1: // '\001'
			btnLeft.setText(R.string.btn_page_1);
			btnRight.setText(R.string.btn_page_3);
			layTop100.setVisibility(0);
			return;

		case 2: // '\002'
		}
		btnLeft.setText(R.string.btn_page_2);
		btnRight.setText(R.string.btn_page_1);
		wvUpdateHistory.setVisibility(0);
	}

	private void navigateTo(int i)
	{
		btnLeft.setVisibility(8);
		btnRight.setVisibility(8);
		btnHome.setBackgroundDrawable(null);
		btnSearch.setBackgroundDrawable(null);
		btnList.setBackgroundDrawable(null);
		btnTool.setBackgroundDrawable(null);
		layHome.setVisibility(8);
		laySearch.setVisibility(8);
		layList.setVisibility(8);
		layTool.setVisibility(8);
		switch (i)
		{
		default:
			return;

		case 1: // '\001'
			btnHome.setBackgroundResource(R.drawable.item_focus);
			layHome.setVisibility(0);
			tvPageName.setText(R.string.page_home);
			btnLeft.setVisibility(0);
			btnRight.setVisibility(0);
			return;

		case 2: // '\002'
			btnSearch.setBackgroundResource(R.drawable.item_focus);
			laySearch.setVisibility(0);
			tvPageName.setText(R.string.page_search);
			return;

		case 3: // '\003'
			btnList.setBackgroundResource(R.drawable.item_focus);
			layList.setVisibility(0);
			tvPageName.setText(R.string.page_list);
			return;

		case 4: // '\004'
			//btnTool.setBackgroundResource(R.drawable.item_focus);
			//break;
		}
		btnTool.setBackgroundResource(R.drawable.item_focus);
		layTool.setVisibility(0);
		tvPageName.setText(R.string.page_tool);
	}

	private void setBottomBar()
	{
		int i = (getWindowManager().getDefaultDisplay().getWidth() - 48) / 4;
		android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)btnHome.getLayoutParams();
		layoutparams.width = i;
		btnHome.setLayoutParams(layoutparams);
		android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)btnSearch.getLayoutParams();
		layoutparams1.width = i;
		btnSearch.setLayoutParams(layoutparams1);
		android.widget.LinearLayout.LayoutParams layoutparams2 = (android.widget.LinearLayout.LayoutParams)btnList.getLayoutParams();
		layoutparams2.width = i;
		btnList.setLayoutParams(layoutparams2);
		android.widget.LinearLayout.LayoutParams layoutparams3 = (android.widget.LinearLayout.LayoutParams)btnTool.getLayoutParams();
		layoutparams3.width = i;
		btnTool.setLayoutParams(layoutparams3);
		((ImageView)btnHome.findViewById(R.id.imgItemIco)).setBackgroundResource(R.drawable.home);
		((ImageView)btnSearch.findViewById(R.id.imgItemIco)).setBackgroundResource(R.drawable.search);
		((ImageView)btnList.findViewById(R.id.imgItemIco)).setBackgroundResource(R.drawable.list);
		((ImageView)btnTool.findViewById(R.id.imgItemIco)).setBackgroundResource(R.drawable.tool);
		((TextView)btnHome.findViewById(R.id.tvItemName)).setText(R.string.itm_home);
		((TextView)btnSearch.findViewById(R.id.tvItemName)).setText(R.string.itm_search);
		((TextView)btnList.findViewById(R.id.tvItemName)).setText(R.string.itm_list);
		((TextView)btnTool.findViewById(R.id.tvItemName)).setText(R.string.itm_tool);
	}

	private void setLifePoint(TextView textview, int i)
	{
		textview.setText(String.valueOf(i));
		if (i >= 4000)
		{
			textview.setTextColor(0xff006400);
			return;
		}
		if (i < 1000)
		{
			textview.setTextColor(0xffff0000);
			return;
		} else
		{
			textview.setTextColor(-29696);
			return;
		}
	}

	private void setLimitCards()
	{
		lvBanned = (ListView)findViewById(R.id.lvBanned);
		lvLimit1 = (ListView)findViewById(R.id.lvLimit1);
		lvLimit2 = (ListView)findViewById(R.id.lvLimit2);
		lvBanned.setOnItemClickListener(this);
		lvLimit1.setOnItemClickListener(this);
		lvLimit2.setOnItemClickListener(this);
		(new Thread(new Runnable() {

			//final MainActivity this$0;
			private final Handler h=new Handler();
			public void run()
			{
				cBanned = teacherUtils.getBannedCards(MainActivity.this);
				cLimit1 = teacherUtils.getLimit1Cards(MainActivity.this);
				cLimit2 = teacherUtils.getLimit2Cards(MainActivity.this);
				//h.sendEmptyMessage(1);
				h.sendEmptyMessage(1);
			}

			
			/*{
				this$0 = MainActivity.this;
				h = handler;
				super();
			}*/
		})).start();
	}

	private void setLimitView(ListView listview, Cursor cursor, int i)
	{
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		float f = displaymetrics.density;
		if (cursor != null)
		{
			String as[] = new String[2];
			as[0] = FieldDefine.DataFields[5];
			as[1] = FieldDefine.DataFields[10];
			listview.setAdapter(new SimpleCursorAdapter(this,R.layout.carditem, cursor, as, new int[] {
				R.id.tvCardName,R.id.tvCardType
			}));
			android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)listview.getLayoutParams();
			layoutparams.height = dipToPx(f, 49 * cursor.getCount()) + dipToPx(f, 24);
			listview.setLayoutParams(layoutparams);
		}
	}

	private void setSearchPage()
	{
		etCardName = (EditText)findViewById(R.id.etCardName);
		etCardAttack = (EditText)findViewById(R.id.etCardAttack);
		etCardDefense = (EditText)findViewById(R.id.etCardDefense);
		btnSearchCard = (Button)findViewById(R.id.btnSearchCard);
		btnSearchReset = (Button)findViewById(R.id.btnSearchReset);
		spCardEffect = (Spinner)findViewById(R.id.spCardEffect);
		setSpinner(spCardEffect, 0);
		spCardRace = (Spinner)findViewById(R.id.spCardRace);
		setSpinner(spCardRace, 1);
		spCardBelongs = (Spinner)findViewById(R.id.spCardBelongs);
		setSpinner(spCardBelongs, 2);
		spCardType = (Spinner)findViewById(R.id.spCardType);
		setSpinner(spCardType, 3);
		spCardAttribute = (Spinner)findViewById(R.id.spCardAttribute);
		setSpinner(spCardAttribute, 4);
		spCardLevel = (Spinner)findViewById(R.id.spCardLevel);
		setSpinner(spCardLevel, 5);
		spCardRare = (Spinner)findViewById(R.id.spCardRare);
		setSpinner(spCardRare, 6);
		spCardLimit = (Spinner)findViewById(R.id.spCardLimit);
		setSpinner(spCardLimit, 7);
		spCardTunner = (Spinner)findViewById(R.id.spCardTunner);
		setSpinner(spCardTunner, 8);
		btnSearchCard.setOnClickListener(this);
		btnSearchReset.setOnClickListener(this);
	}

	private void setSpinner(Spinner spinner, int i)
	{
		
		/*List list = null;
		i;
		JVM INSTR tableswitch 0 8: default 52*/
	//	               0 100
	//	               1 108
	//	               2 115
	//	               3 122
	//	               4 129
	//	               5 136
	//	               6 143
	//	               7 150
	//	               8 157;
		/*List list=null;
		if(list!=null){
			list.add(0, getResources().getString(0x7f07002f));
			spinner.setAdapter(new ArrayAdapter(this, 0x7f030012, list));
			spinner.setSelection(0);
			spinner.setOnItemSelectedListener(this);*/
		//可能有问题
		List list=null;
		switch(i)
		{
			default:
				if (list != null)
				{
					list.add(0, getResources().getString(R.string.search_na));
					spinner.setAdapter(new ArrayAdapter(this, R.layout.spinitem, list));
					spinner.setSelection(0);
					spinner.setOnItemSelectedListener(this);
				}
				return;
			case 0:
				list = teacherUtils.getEffectList(this);
				break;
			case 1:
				list = CardConstDefine.getCardRace();
				break;
			case 2:
				list = CardConstDefine.getCardBelongs();
				break;
			case 3:
				list = CardConstDefine.getCardType();
				break;
			case 4:
				list = CardConstDefine.getCardAttribute();
				break;
			case 5:
				list = CardConstDefine.getCardLevel();
				break;
			case 6:
				list = CardConstDefine.getCardCare();
				break;
			case 7:
				list = CardConstDefine.getCardLimit();
				break;
			case 8:
				list = CardConstDefine.getCardTunner();
				break;
		}
		//}
		
		   /*goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
		if (list != null)
		{
			list.add(0, getResources().getString(0x7f07002f));
			spinner.setAdapter(new ArrayAdapter(this, 0x7f030012, list));
			spinner.setSelection(0);
			spinner.setOnItemSelectedListener(this);
		}
		return;
_L2:
		list = teacherUtils.getEffectList(this);
		continue;
_L3:
		list = CardConstDefine.getCardRace();
		continue;
_L4:
		list = CardConstDefine.getCardBelongs();
		continue;
_L5:
		list = CardConstDefine.getCardType();
		continue; 
_L6:
		list = CardConstDefine.getCardAttribute();
		continue;
_L7:
		list = CardConstDefine.getCardLevel();
		continue;
_L8:
		list = CardConstDefine.getCardCare();
		continue; 
_L9:
		list = CardConstDefine.getCardLimit();
		continue; 
_L10:
		list = CardConstDefine.getCardTunner();
		if (true) goto _L1; else goto _L11
_L11:*/
	}

	private void setToolButtonLayout()
	{
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		float f = displaymetrics.density;
		int i = (getWindowManager().getDefaultDisplay().getWidth() - dipToPx(f, 16)) / 4;
		android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)btnP1Half.getLayoutParams();
		layoutparams.width = i;
		btnP1Half.setLayoutParams(layoutparams);
		android.widget.RelativeLayout.LayoutParams layoutparams1 = (android.widget.RelativeLayout.LayoutParams)btnP2Half.getLayoutParams();
		layoutparams1.width = i;
		btnP2Half.setLayoutParams(layoutparams1);
		android.widget.RelativeLayout.LayoutParams layoutparams2 = (android.widget.RelativeLayout.LayoutParams)btnP1Double.getLayoutParams();
		layoutparams2.width = i;
		btnP1Double.setLayoutParams(layoutparams2);
		android.widget.RelativeLayout.LayoutParams layoutparams3 = (android.widget.RelativeLayout.LayoutParams)btnP2Double.getLayoutParams();
		layoutparams3.width = i;
		btnP2Double.setLayoutParams(layoutparams3);
		android.widget.RelativeLayout.LayoutParams layoutparams4 = (android.widget.RelativeLayout.LayoutParams)btnP1Divide.getLayoutParams();
		layoutparams4.width = i;
		btnP1Divide.setLayoutParams(layoutparams4);
		android.widget.RelativeLayout.LayoutParams layoutparams5 = (android.widget.RelativeLayout.LayoutParams)btnP2Divide.getLayoutParams();
		layoutparams5.width = i;
		btnP2Divide.setLayoutParams(layoutparams5);
		android.widget.RelativeLayout.LayoutParams layoutparams6 = (android.widget.RelativeLayout.LayoutParams)btnP1Opt.getLayoutParams();
		layoutparams6.width = i;
		btnP1Opt.setLayoutParams(layoutparams6);
		android.widget.RelativeLayout.LayoutParams layoutparams7 = (android.widget.RelativeLayout.LayoutParams)btnP2Opt.getLayoutParams();
		layoutparams7.width = i;
		btnP2Opt.setLayoutParams(layoutparams7);
		int j = (getWindowManager().getDefaultDisplay().getWidth() - dipToPx(f, 16)) / 3;
		android.widget.RelativeLayout.LayoutParams layoutparams8 = (android.widget.RelativeLayout.LayoutParams)btnCoin.getLayoutParams();
		layoutparams8.width = j;
		btnCoin.setLayoutParams(layoutparams8);
		android.widget.RelativeLayout.LayoutParams layoutparams9 = (android.widget.RelativeLayout.LayoutParams)btnDice.getLayoutParams();
		layoutparams9.width = j;
		btnDice.setLayoutParams(layoutparams9);
		android.widget.RelativeLayout.LayoutParams layoutparams10 = (android.widget.RelativeLayout.LayoutParams)btnRestartDuel.getLayoutParams();
		layoutparams10.width = j;
		btnRestartDuel.setLayoutParams(layoutparams10);
	}

	private void setTop100Cards()
	{
		(new Thread(new Runnable() {

			//final MainActivity this$0;
			private final Handler h=new Handler();

			public void run()
			{
				c = teacherUtils.getLatest100(MainActivity.this);
				h.sendEmptyMessage(1);
			}

			
			/*{
				this$0 = MainActivity.this;
				h = handler;
				super();
			}*/
		})).start();
	}

	private void setWebView(WebView webview)
	{
		WebSettings websettings = webview.getSettings();
		websettings.setAllowFileAccess(true);
		websettings.setLoadWithOverviewMode(false);
		websettings.setUseWideViewPort(true);
		websettings.setSupportZoom(false);
		webview.setVerticalScrollBarEnabled(true);
		webview.setHorizontalScrollBarEnabled(false);
		webview.setFocusable(true);
		webview.setFocusableInTouchMode(true);
	}

	public void onClick(View view)
	{
		switch (view.getId())
		{
		default:
			return;

		case R.id.btnHome: 
			navigateTo(1);
			return;

		case R.id.btnSearch: 
			navigateTo(2);
			return;

		case R.id.btnList: 
			navigateTo(3);
			return;

		case R.id.btnTool: 
			navigateTo(4);
			return;

		case R.id.btnSearchCard: 
			doSearchCard();
			return;

		case R.id.btnSearchReset: 
			doSearchReset();
			return;

		case R.id.btnLeft: 
			currentHomePage = currentHomePage - 1;
			if (currentHomePage < 0)
				currentHomePage = 2;
			navigateHomeTo(currentHomePage);
			return;

		case R.id.btnRight: 
			currentHomePage = 1 + currentHomePage;
			if (currentHomePage > 2)
				currentHomePage = 0;
			navigateHomeTo(currentHomePage);
			return;

		case R.id.btnRestartDuel: 
			Player1Life = 8000;
			Player2Life = 8000;
			setLifePoint(tvPlayer1Life, Player1Life);
			setLifePoint(tvPlayer2Life, Player2Life);
			return;

		case R.id.btnP1Add: 
			if (etP1Life.getText().toString().equals(""))
			{
				Toast.makeText(this, R.string.tool_number_reqired, 1).show();
				return;
			} else
			{
				Player1Life = Integer.parseInt(etP1Life.getText().toString()) + Player1Life;
				setLifePoint(tvPlayer1Life, Player1Life);
				etP1Life.setText("");
				return;
			}

		case R.id.btnP2Add: 
			if (etP2Life.getText().toString().equals(""))
			{
				Toast.makeText(this,R.id.btnP2Add, 1).show();
				return;
			} else
			{
				Player2Life = Integer.parseInt(etP2Life.getText().toString()) + Player2Life;
				setLifePoint(tvPlayer2Life, Player2Life);
				etP2Life.setText("");
				return;
			}

		case R.id.btnP1Minus: 
			if (etP1Life.getText().toString().equals(""))
			{
				Toast.makeText(this,R.string.tool_number_reqired, 1).show();
				return;
			}
			int j1 = Integer.parseInt(etP1Life.getText().toString());
			Player1Life -= j1;
			if (Player1Life < 0)
				Player1Life = 0;
			setLifePoint(tvPlayer1Life, Player1Life);
			etP1Life.setText("");
			return;

		case R.id.btnP2Minus: 
			if (etP2Life.getText().toString().equals(""))
			{
				Toast.makeText(this,R.string.tool_number_reqired, 1).show();
				return;
			}
			int i1 = Integer.parseInt(etP2Life.getText().toString());
			Player2Life -= i1;
			if (Player2Life < 0)
				Player2Life = 0;
			setLifePoint(tvPlayer2Life, Player2Life);
			etP2Life.setText("");
			return;

		case R.id.btnP1Set: 
			if (etP1Life.getText().toString().equals(""))
			{
				Toast.makeText(this,R.string.tool_number_reqired, 1).show();
				return;
			} else
			{
				Player1Life = Integer.parseInt(etP1Life.getText().toString());
				setLifePoint(tvPlayer1Life, Player1Life);
				etP1Life.setText("");
				return;
			}

		case R.id.btnP2Set: 
			if (etP2Life.getText().toString().equals(""))
			{
				Toast.makeText(this,R.string.tool_number_reqired, 1).show();
				return;
			} else
			{
				Player2Life = Integer.parseInt(etP2Life.getText().toString());
				setLifePoint(tvPlayer2Life, Player2Life);
				etP2Life.setText("");
				return;
			}

		case R.id.btnP1Half: 
			if (Player1Life % 2 != 0)
				Player1Life = 1 + Player1Life;
			Player1Life /= 2;
			setLifePoint(tvPlayer1Life, Player1Life);
			return;

		case R.id.btnP2Half: 
			if (Player2Life % 2 != 0)
				Player2Life = 1 + Player2Life;
			Player2Life /= 2;
			setLifePoint(tvPlayer2Life, Player2Life);
			return;

		case R.id.btnP1Double: 
			Player1Life = 2 * Player1Life;
			setLifePoint(tvPlayer1Life, Player1Life);
			return;

		case R.id.btnP2Double: 
			Player2Life = 2 * Player2Life;
			setLifePoint(tvPlayer2Life, Player2Life);
			return;

		case R.id.btnP1Divide: 
			int k = Player1Life + Player2Life;
			if (k % 2 != 0)
				k++;
			int l = k / 2;
			Player1Life = l;
			Player2Life = l;
			setLifePoint(tvPlayer1Life, Player1Life);
			setLifePoint(tvPlayer2Life, Player2Life);
			return;

		case R.id.btnP2Divide: 
			int i = Player1Life + Player2Life;
			if (i % 2 != 0)
				i++;
			int j = i / 2;
			Player1Life = j;
			Player2Life = j;
			setLifePoint(tvPlayer1Life, Player1Life);
			setLifePoint(tvPlayer2Life, Player2Life);
			return;

		case R.id.btnP1Opt: 
			Player1Life = Player2Life;
			setLifePoint(tvPlayer1Life, Player1Life);
			return;

		case R.id.btnP2Opt: 
			Player2Life = Player1Life;
			setLifePoint(tvPlayer2Life, Player2Life);
			return;

		/*case 2131361894: 
			Intent intent1 = new Intent(this,CoinDiceActivity.class);
			intent1.putExtra("type", 1);
			startActivity(intent1);
			return;

		case 2131361893: 
			Intent intent = new Intent(this,CoinDiceActivity.class);
			intent.putExtra("type", 2);
			startActivity(intent);
			return;*/
		}
	}

	protected void onCreate(Bundle bundle)
	{
		requestWindowFeature(1);
		super.onCreate(bundle);
		File file = new File("/sdcard/.teacher/");
		if (!file.exists())
			file.mkdir();
		File file1 = new File("/sdcard/.teacher/images/");
		if (!file1.exists())
			file1.mkdir();
		/*if (!(new File("/sdcard/teacher.db")).exists())
		{
			(new android.app.AlertDialog.Builder(this)).setTitle(R.string.hint).setMessage(R.string.msg_no_database).setPositiveButton(R.string.ok, new android.content.DialogInterface.OnClickListener() {

				//final MainActivity this$0;

				public void onClick(DialogInterface dialoginterface, int i)
				{
					finish();
				}

			
			}).show();
		} else
		{*/
			setContentView(R.layout.main);
			btnHome = (RelativeLayout)findViewById(R.id.btnHome);
			btnSearch = (RelativeLayout)findViewById(R.id.btnSearch);
			btnList = (RelativeLayout)findViewById(R.id.btnList);
			btnTool = (RelativeLayout)findViewById(R.id.btnTool);
			layHome = (RelativeLayout)findViewById(R.id.lay_home);
			laySearch = (RelativeLayout)findViewById(R.id.lay_search);
			layList = (RelativeLayout)findViewById(R.id.lay_list);
			layTool = (RelativeLayout)findViewById(R.id.lay_tool);
			layLimit = (RelativeLayout)findViewById(R.id.layLimit);
			layTop100 = (RelativeLayout)findViewById(R.id.layTop100);
			lvCard = (ListView)findViewById(R.id.lvCard);
			lvList = (ListView)findViewById(R.id.lvList);
			tvListNoCard = (TextView)findViewById(R.id.tvListNoCard);
			tvPageName = (TextView)findViewById(R.id.tvPageName);
			btnLeft = (Button)findViewById(R.id.btnLeft);
			btnRight = (Button)findViewById(R.id.btnRight);
			svLimit = (ScrollView)findViewById(R.id.svLimit);
			tvPlayer1Life = (TextView)findViewById(R.id.tvPlayer1Life);
			tvPlayer2Life = (TextView)findViewById(R.id.tvPlayer2Life);
			etP1Life = (EditText)findViewById(R.id.etP1Life);
			etP2Life = (EditText)findViewById(R.id.etP2Life);
			btnP1Add = (Button)findViewById(R.id.btnP1Add);
			btnP2Add = (Button)findViewById(R.id.btnP2Add);
			btnP1Minus = (Button)findViewById(R.id.btnP1Minus);
			btnP2Minus = (Button)findViewById(R.id.btnP2Minus);
			btnP1Set = (Button)findViewById(R.id.btnP1Set);
			btnP2Set = (Button)findViewById(R.id.btnP2Set);
			btnP1Half = (Button)findViewById(R.id.btnP1Half);
			btnP2Half = (Button)findViewById(R.id.btnP2Half);
			btnP1Double = (Button)findViewById(R.id.btnP1Double);
			btnP2Double = (Button)findViewById(R.id.btnP2Double);
			btnP1Divide = (Button)findViewById(R.id.btnP1Divide);
			btnP2Divide = (Button)findViewById(R.id.btnP2Divide);
			btnP1Opt = (Button)findViewById(R.id.btnP1Opt);
			btnP2Opt = (Button)findViewById(R.id.btnP2Opt);
			btnDice = (Button)findViewById(R.id.btnDice);
			btnCoin = (Button)findViewById(R.id.btnCoin);
			btnRestartDuel = (Button)findViewById(R.id.btnRestartDuel);
			setToolButtonLayout();
			btnRestartDuel.setOnClickListener(this);
			btnP1Add.setOnClickListener(this);
			btnP2Add.setOnClickListener(this);
			btnP1Minus.setOnClickListener(this);
			btnP2Minus.setOnClickListener(this);
			btnP1Set.setOnClickListener(this);
			btnP2Set.setOnClickListener(this);
			btnP1Half.setOnClickListener(this);
			btnP2Half.setOnClickListener(this);
			btnP1Double.setOnClickListener(this);
			btnP2Double.setOnClickListener(this);
			btnP1Divide.setOnClickListener(this);
			btnP2Divide.setOnClickListener(this);
			btnP1Opt.setOnClickListener(this);
			btnP2Opt.setOnClickListener(this);
			btnDice.setOnClickListener(this);
			btnCoin.setOnClickListener(this);
			onClick(btnRestartDuel);
			wvUpdateHistory = (WebView)findViewById(R.id.wvUpdateHistory);
			setWebView(wvUpdateHistory);
			wvUpdateHistory.loadUrl("file:///android_asset/changelog.html");
			setBottomBar();
			setSearchPage();
			lvList.setOnItemClickListener(this);
			lvCard.setOnItemClickListener(this);
			btnHome.setOnClickListener(this);
			btnSearch.setOnClickListener(this);
			btnList.setOnClickListener(this);
			btnTool.setOnClickListener(this);
			btnLeft.setOnClickListener(this);
			btnRight.setOnClickListener(this);
			navigateTo(1);
			navigateHomeTo(Integer.parseInt(ConfigUtils.getConfig(this, getResources().getString(R.string.id_home), "0")));
			setTop100Cards();
			setLimitCards();
			if (ConfigUtils.getConfig(this, getResources().getString(R.string.id_update), true))
			{
				checkUpdateT();
				return;
			}
		//}
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.clear();
		getMenuInflater().inflate(R.menu.mainmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void onItemClick(AdapterView adapterview, View view, int i, long l)
	{
		Intent intent;
		int j;
		int k;
		//intent = new Intent(this, com/teacher/android/CardInfoActivity);
		intent=new Intent();
		intent.setClass(this,CardInfoActivity.class);
		j = adapterview.getId();
		k = 0;
		switch(j)
		{
			default:
				intent.putExtra("CardId", k);
				startActivity(intent);
				return;
			case R.id.lvCard:
				c.moveToPosition(i);
				k = c.getInt(0);
				break; 
			case R.id.lvList:
				cSearchResult.moveToPosition(i);
				k = cSearchResult.getInt(0);
				break; 
			case R.id.lvBanned:
				cBanned.moveToPosition(i);
				k = cBanned.getInt(0);
				break; 
			case R.id.lvLimit1:
				cLimit1.moveToPosition(i);
				k = cLimit1.getInt(0);
				break; 
			case R.id.lvLimit2:
				cLimit2.moveToPosition(i);
				k = cLimit2.getInt(0);
				break;
		}
		/*j;
		JVM INSTR lookupswitch 5: default 76
	//	               2131361839: 146
	//	               2131361841: 172
	//	               2131361843: 198
	//	               2131361844: 120
	//	               2131361897: 94;
		   goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
		intent.putExtra("CardId", k);
		startActivity(intent);
		return;
_L6:
		c.moveToPosition(i);
		k = c.getInt(0);
		continue; 
_L5:
		cSearchResult.moveToPosition(i);
		k = cSearchResult.getInt(0);
		continue; 
_L2:
		cBanned.moveToPosition(i);
		k = cBanned.getInt(0);
		continue; 
_L3:
		cLimit1.moveToPosition(i);
		k = cLimit1.getInt(0);
		continue; 
_L4:
		cLimit2.moveToPosition(i);
		k = cLimit2.getInt(0);
		if (true) goto _L1; else goto _L7
_L7:*/
	}

	public void onItemSelected(AdapterView adapterview, View view, int i, long l)
	{
		Spinner spinner;
		switch (adapterview.getId())
		{
		default:
			return;

		case R.id.spCardType: 
			spinner = spCardTunner;
			break;
		}
		boolean flag;
		if (i <= 7)
			flag = true;
		else
			flag = false;
		spinner.setEnabled(flag);
	}

	public void onNothingSelected(AdapterView adapterview)
	{
	}

	public boolean onOptionsItemSelected(MenuItem menuitem)
	{
		/*menuitem.getItemId();
		JVM INSTR tableswitch 2131361918 2131361919: default 28
	//	               2131361918 34
	//	               2131361919 52;
		   goto _L1 _L2 _L3
_L1:
		return super.onOptionsItemSelected(menuitem);
_L2:
		startActivity(new Intent(this, com/teacher/android/SettingsActivity));
		continue; /* Loop/switch isn't completed 
_L3:
		startActivity(new Intent(this, com/teacher/android/AboutActivity));
		if (true) goto _L1; else goto _L4
_L4:*/
		switch(menuitem.getItemId()){
			default:
				return onOptionsItemSelected(menuitem);
			case R.id.mi_settings:
				Intent intent=new Intent();
				intent.setClass(this,SettingsActivity.class);
				startActivity(intent);
				break;
			case R.id.mi_about:
				Intent intent2=new Intent();
				intent2.setClass(this,AboutActivity.class);
				startActivity(intent2);
				break;
		}
		return false;
	}

	public String readAssetsFile(String s)
	{
		String s1;
		try
		{
			InputStream inputstream = getAssets().open(s);
			byte abyte0[] = new byte[inputstream.available()];
			inputstream.read(abyte0);
			inputstream.close();
			s1 = new String(abyte0, "utf-8");
		}
		catch (IOException ioexception)
		{
			Log.e("readAssetsFile", ioexception.getMessage());
			return "";
		}
		return s1;
	}



	// Unreferenced inner class com/teacher/android/MainActivity$2

	/*class b extends Handler
	{

		final MainActivity this$0;

		public void handleMessage(Message message)
		{
			if (message.what == 1)
			{
				MainActivity mainactivity = MainActivity.this;
				MainActivity mainactivity1 = MainActivity.this;
				Cursor cursor = c;
				String as[] = new String[2];
				as[0] = FieldDefine.DataFields[5];
				as[1] = FieldDefine.DataFields[10];
				mainactivity.adapterTop100 = new SimpleCursorAdapter(mainactivity1, 0x7f030007, cursor, as, new int[] {
					0xid/tvCardName, 0xid/tvCardType
				});
				lvCard.setAdapter(adapterTop100);
			}
			super.handleMessage(message);
		}

			
			{
				this$0 = MainActivity.this;
				super();
			}
	}*/


	// Unreferenced inner class com/teacher/android/MainActivity$4

/* anonymous class */
	/*class d extends Handler
	{

		final MainActivity this$0;

		public void handleMessage(Message message)
		{
			if (message.what == 1)
			{
				setLimitView(lvBanned, cBanned, 1);
				setLimitView(lvLimit1, cLimit1, 2);
				setLimitView(lvLimit2, cLimit2, 3);
				svLimit.post(new Runnable() {

					final d this$1;

					public void run()
					{
						svLimit.scrollTo(0, 0);
					}

			
			{
				this$1 = d.this;
				super();
			}*/
			//	});
			//}
			//super.handleMessage(message);
		//}


			
			/*{
				this$0 = MainActivity.this;
				super();
			}*/
	//}


	// Unreferenced inner class com/teacher/android/MainActivity$6

	/*class 6 extends Handler
	{

		final MainActivity this$0;

		public void handleMessage(Message message)
		{
			if (message.what == 1)
			{
				UpdateInfo updateinfo = (UpdateInfo)message.obj;
				if (updateinfo != null && (updateinfo.getUpdateApk() != 0 || updateinfo.getUpdateData() != 0))
				{
					//Intent intent = new Intent(MainActivity.this, com/teacher/android/UpdateActivity);
					Intent intent=new Intent();
					intent.setClass(MainActivity.this,UpdateActivity.class);
					intent.putExtra("apk", updateinfo.getUpdateApk());
					intent.putExtra("data", updateinfo.getUpdateData());
					startActivity(intent);
				}
			}
			super.handleMessage(message);
		}

			
			/*{
				this$0 = MainActivity.this;
				super();
			}*/
}
