// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.teacher.android.adapter.UpdateAdapter;
import com.teacher.android.database.teacherUtils;
import com.teacher.android.network.NetworkUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends Activity
	implements android.view.View.OnClickListener
{

	int TaskCount;
	UpdateAdapter adapter;
	int apk;
	Button btnStartUpdate;
	Button btnUpdateLeft;
	int data;
	boolean inProgress;
	ListView lvUpdate;

	public UpdateActivity()
	{
		TaskCount = 0;
		inProgress = false;
		adapter = null;
		apk = 0;
		data = 0;
	}

	private void checkTask()
	{
		if (TaskCount == 0)
		{
			btnStartUpdate.setEnabled(true);
			btnStartUpdate.setText(R.string.update_finish);
			return;
		} else
		{
			Button button = btnStartUpdate;
			String s = getResources().getString(R.string.update_task);
			Object aobj[] = new Object[1];
			aobj[0] = Integer.valueOf(TaskCount);
			button.setText(String.format(s, aobj));
			return;
		}
	}

	private void copyFile(String s, String s1)
	{
		int i = 0;
		FileInputStream fileinputstream;
		FileOutputStream fileoutputstream;
		byte abyte0[];
		try
	    {
	      if (new File(s).exists())
	      {
	        FileInputStream localFileInputStream = new FileInputStream(s);
	        FileOutputStream localFileOutputStream = new FileOutputStream(s1);
	        byte[] arrayOfByte = new byte[1444];
	        while (true)
	        {
	          int j = localFileInputStream.read(arrayOfByte);
	          if (j == -1)
	          {
	            localFileInputStream.close();
	            return;
	          }
	          i += j;
	          System.out.println(i);
	          localFileOutputStream.write(arrayOfByte, 0, j);
	        }
	      }
	    }
	    catch (Exception localException)
	    {
	      Log.e("teacher", localException.getMessage());
	    }
		/*if (!(new File(s)).exists())
			break MISSING_BLOCK_LABEL_102;
		fileinputstream = new FileInputStream(s);
		fileoutputstream = new FileOutputStream(s1);
		abyte0 = new byte[1444];
//_L1:
		int j = fileinputstream.read(abyte0);
		if (j == -1)
		{
			try
			{
				fileinputstream.close();
				return;
			}
			catch (Exception exception)
			{
				Log.e("teacher", exception.getMessage());
			}
			break MISSING_BLOCK_LABEL_102;
		}
		i += j;
		System.out.println(i);
		fileoutputstream.write(abyte0, 0, j);*/
		  //goto _L1
	}

	private void setTaskList()
	{
		apk = getIntent().getIntExtra("apk", 0);
		data = getIntent().getIntExtra("data", 0);
		TaskCount = apk + data;
		ArrayList arraylist = new ArrayList();
		if (apk != 0)
			arraylist.add(getResources().getString(R.string.update_apk));
		if (data != 0)
			arraylist.add(getResources().getString(R.string.update_data));
		adapter = new UpdateAdapter(getLayoutInflater(), arraylist);
		lvUpdate.setAdapter(adapter);
	}

	public void downloadT(final ProgressBar pbDownloading, final String url, final String filePath)
	{
		pbDownloading.setVisibility(0);
		pbDownloading.setProgress(0);
		(new Thread(new Runnable() {

			//final UpdateActivity this$0;
			//private final String val$filePath;
			private final Handler h=new Handler();
			//private final String val$url;

			public void run()
			{
				NetworkUtils.downloadFile(url, filePath, h);
				if (filePath.endsWith(".db"))
				{
					File file = new File(filePath);
					if (file.exists())
					{
						teacherUtils.closeDatabase(UpdateActivity.this);
						(new File("/sdcard/.teacher/teacher.db")).delete();
						copyFile(filePath, "/sdcard/.teacher/teacher.db");
						file.delete();
					}
				}
				h.sendEmptyMessage(1);
			}

			
			/*{
				this$0 = UpdateActivity.this;
				url = s;
				filePath = s1;
				h = handler;
				super();
			}*/
		})).start();
		/*pbDownloading.setVisibility(0);
		pbDownloading.setProgress(0);
	    new Thread(new Runnable(url, filePath, new Handler(pbDownloading)
	    {
	      public void handleMessage(Message paramMessage)
	      {
	        if (paramMessage.what == 10)
	        {
	          this.val$pbDownloading.setMax(paramMessage.arg2);
	          this.val$pbDownloading.setProgress(paramMessage.arg1);
	        }
	        while (true)
	        {
	          super.handleMessage(paramMessage);
	          return;
	          if (paramMessage.what == 11)
	          {
	            this.val$pbDownloading.setProgress(paramMessage.arg1);
	            continue;
	          }
	          if (paramMessage.what == 1)
	          {
	            UpdateActivity localUpdateActivity = UpdateActivity.this;
	            localUpdateActivity.TaskCount -= 1;
	            UpdateActivity.this.checkTask();
	            continue;
	          }
	          if (paramMessage.what != 19)
	            continue;
	          this.val$pbDownloading.setProgress(0);
	          this.val$pbDownloading.setVisibility(8);
	        }
	      }
	    })
	    {
	      public void run()
	      {
	        NetworkUtils.downloadFile(this.val$url, this.val$filePath, this.val$h);
	        if (this.val$filePath.endsWith(".db"))
	        {
	          File localFile = new File(this.val$filePath);
	          if (localFile.exists())
	          {
	            teacherUtils.closeDatabase(UpdateActivity.this);
	            new File("/sdcard/.teacher/teacher.db").delete();
	            UpdateActivity.this.copyFile(this.val$filePath, "/sdcard/.teacher/teacher.db");
	            localFile.delete();
	          }
	        }
	        this.val$h.sendEmptyMessage(1);
	      }
	    }).start();*/
	}

	public void onClick(View view)
	{
		switch(view.getId())
		{
			default:
				return;
			case R.id.btnUpdateLeft:
				if (!inProgress)
				{
					finish();
					return;
				}
			case R.id.btnStartUpdate:
				if (btnStartUpdate.getText().toString().equals(getResources().getString(R.string.update_finish)))
				{
					if (apk != 0)
					{
						File file = new File("/sdcard/.teacher/downloads/teacherCard.apk");
						if (file.exists())
						{
							Uri uri = Uri.fromFile(file);
							Intent intent = new Intent("android.intent.action.VIEW");
							intent.setDataAndType(uri, "application/vnd.android.package-archive");
							startActivity(intent);
							return;
						}
					}
				} else
				{
					btnStartUpdate.setEnabled(false);
					btnStartUpdate.setText(R.string.update_downloading);
					inProgress = true;
					checkTask();
					int i = 0;
					while (i < lvUpdate.getCount()) 
					{
						RelativeLayout relativelayout = (RelativeLayout)lvUpdate.getChildAt(i);
						if (relativelayout != null)
							if (((TextView)relativelayout.findViewById(R.id.tvUpdateName)).getText().toString().equals(getResources().getString(R.string.update_apk)))
								downloadT((ProgressBar)relativelayout.findViewById(R.id.pbDownloading), "http://rarnu.7thgen.info/teacher/teacherCard.apk", "/sdcard/.teacher/downloads/teacherCard.apk");
							else
								downloadT((ProgressBar)relativelayout.findViewById(R.id.pbDownloading), "http://rarnu.7thgen.info/teacher/teacher.db", "/sdcard/.teacher/downloads/teacher.db");
						i++;
					}
				}
		}
		/*view.getId();
		JVM INSTR lookupswitch 2: default 32
	//	               2131361900: 45
	//	               2131361914: 33;
		   goto _L1 _L2 _L3
_L1:
		return;
_L3:
		if (!inProgress)
		{
			finish();
			return;
		}
		continue; 
_L2:
		if (btnStartUpdate.getText().toString().equals(getResources().getString(0x7f070086)))
		{
			if (apk != 0)
			{
				File file = new File("/sdcard/.teacher/downloads/teacherCard.apk");
				if (file.exists())
				{
					Uri uri = Uri.fromFile(file);
					Intent intent = new Intent("android.intent.action.VIEW");
					intent.setDataAndType(uri, "application/vnd.android.package-archive");
					startActivity(intent);
					return;
				}
			}
		} else
		{
			btnStartUpdate.setEnabled(false);
			btnStartUpdate.setText(0x7f070085);
			inProgress = true;
			checkTask();
			int i = 0;
			while (i < lvUpdate.getCount()) 
			{
				RelativeLayout relativelayout = (RelativeLayout)lvUpdate.getChildAt(i);
				if (relativelayout != null)
					if (((TextView)relativelayout.findViewById(0x7f0a007c)).getText().toString().equals(getResources().getString(0x7f070087)))
						downloadT((ProgressBar)relativelayout.findViewById(0x7f0a007d), "http://rarnu.7thgen.info/teacher/teacherCard.apk", "/sdcard/.teacher/downloads/teacherCard.apk");
					else
						downloadT((ProgressBar)relativelayout.findViewById(0x7f0a007d), "http://rarnu.7thgen.info/teacher/teacher.db", "/sdcard/.teacher/downloads/teacher.db");
				i++;
			}
		}
		if (true) goto _L1; else goto _L4
_L4:*/
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		requestWindowFeature(1);
		setContentView(R.layout.update);
		btnUpdateLeft = (Button)findViewById(R.id.btnUpdateLeft);
		lvUpdate = (ListView)findViewById(R.id.lvUpdate);
		btnStartUpdate = (Button)findViewById(R.id.btnStartUpdate);
		btnUpdateLeft.setOnClickListener(this);
		btnStartUpdate.setOnClickListener(this);
		File file = new File("/sdcard/.teacher/downloads/");
		if (!file.exists())
			file.mkdirs();
		setTaskList();
	}

	public boolean onKeyDown(int i, KeyEvent keyevent)
	{
		if (i == 4 && inProgress)
			return true;
		else
			return super.onKeyDown(i, keyevent);
	}



	// Unreferenced inner class com/teacher/android/UpdateActivity$1

/* anonymous class */
	/*class 1 extends Handler
	{

		final UpdateActivity this$0;
		private final ProgressBar val$pbDownloading;

		public void handleMessage(Message message)
		{
			if (message.what != 10) goto _L2; else goto _L1
_L1:
			pbDownloading.setMax(message.arg2);
			pbDownloading.setProgress(message.arg1);
_L4:
			super.handleMessage(message);
			return;
_L2:
			if (message.what == 11)
				pbDownloading.setProgress(message.arg1);
			else
			if (message.what == 1)
			{
				UpdateActivity updateactivity = UpdateActivity.this;
				updateactivity.TaskCount = updateactivity.TaskCount - 1;
				checkTask();
			} else
			if (message.what == 19)
			{
				pbDownloading.setProgress(0);
				pbDownloading.setVisibility(8);
			}
			if (true) goto _L4; else goto _L3
_L3:
		}

			
			{
				this$0 = UpdateActivity.this;
				pbDownloading = progressbar;
				super();
			}
	}*/

}
