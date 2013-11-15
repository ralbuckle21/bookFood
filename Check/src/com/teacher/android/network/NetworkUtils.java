// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android.network;

import android.os.Handler;
import android.os.Message;
import com.teacher.android.pojo.UpdateInfo;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class NetworkUtils
{

	public static final int MSG_DOWNLOAD_ERROR = 19;
	public static final int MSG_DOWNLOAD_FINISH = 12;
	public static final int MSG_DOWNLOAD_PROGRESS = 11;
	public static final int MSG_DOWNLOAD_START = 10;

	public NetworkUtils()
	{
	}

	public static String CallGet(String s, String s1)
		throws Exception
	{
		HttpGet httpget = new HttpGet(s);
		DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
		defaulthttpclient.getParams().setParameter("http.connection.timeout", Integer.valueOf(10000));
		HttpResponse httpresponse = defaulthttpclient.execute(httpget);
		String s2 = "";
		if (httpresponse.getStatusLine().getStatusCode() == 200)
			s2 = EntityUtils.toString(httpresponse.getEntity(), s1);
		return s2;
	}

	public static void downloadFile(String s, String s1, Handler handler)
	{
		/*int i = 0;
		URL url = new URL(s);
		InputStream inputstream;
		int j;
		HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
		inputstream = httpurlconnection.getInputStream();
		j = httpurlconnection.getContentLength();
		if (handler == null)
			break MISSING_BLOCK_LABEL_76;
		Message message = new Message();
		message.what = 10;
		message.arg1 = 0;
		message.arg2 = j;
		handler.sendMessage(message);
		FileOutputStream fileoutputstream;
		byte abyte0[];
		fileoutputstream = new FileOutputStream(new File(s1));
		abyte0 = new byte[1024];
//_L2:
		int k = inputstream.read(abyte0);
		if (k != -1)
			break MISSING_BLOCK_LABEL_166;
		inputstream.close();
		fileoutputstream.close();
		if (handler == null)
			break MISSING_BLOCK_LABEL_239;
		Message message2;
		try
		{
			Message message1 = new Message();
			message1.what = 12;
			message1.arg1 = 0;
			message1.arg2 = j;
			handler.sendMessage(message1);
			return;
		}
		catch (Exception exception) { }
		break MISSING_BLOCK_LABEL_226;
		fileoutputstream.write(abyte0, 0, k);
		i += k;
		if (handler == null) goto _L2; else goto _L1
//_L1:
		message2 = new Message();
		message2.what = 11;
		message2.arg1 = i;
		message2.arg2 = j;
		handler.sendMessage(message2);
		  goto _L2
_L4:
		handler.sendEmptyMessage(19);
		return;
		Exception exception1;
		exception1;
		if (true) goto _L4; else goto _L3
_L3:*/
		int i = 0;
		Handler paramHandler=new Handler();
	    try
	    {
	      URL localURL = new URL(s);
	      try
	      {
	        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
	        InputStream localInputStream = localHttpURLConnection.getInputStream();
	        int j = localHttpURLConnection.getContentLength();
	        if (paramHandler != null)
	        {
	          Message localMessage1 = new Message();
	          localMessage1.what = 10;
	          localMessage1.arg1 = 0;
	          localMessage1.arg2 = j;
	          paramHandler.sendMessage(localMessage1);
	        }
	        FileOutputStream localFileOutputStream = new FileOutputStream(new File(s1));
	        byte[] arrayOfByte = new byte[1024];
	        while (true)
	        {
	          int k = localInputStream.read(arrayOfByte);
	          if (k == -1)
	          {
	            localInputStream.close();
	            localFileOutputStream.close();
	            if (paramHandler == null)
	              break;
	            Message localMessage2 = new Message();
	            localMessage2.what = 12;
	            localMessage2.arg1 = 0;
	            localMessage2.arg2 = j;
	            paramHandler.sendMessage(localMessage2);
	            return;
	          }
	          localFileOutputStream.write(arrayOfByte, 0, k);
	          i += k;
	          if (paramHandler == null)
	            continue;
	          Message localMessage3 = new Message();
	          localMessage3.what = 11;
	          localMessage3.arg1 = i;
	          localMessage3.arg2 = j;
	          paramHandler.sendMessage(localMessage3);
	        }
	      }
	      catch (Exception localException1)
	      {
	        //label226: paramHandler.sendEmptyMessage(19);
	    	paramHandler.sendEmptyMessage(19);
	        return;
	      }
	    }
	    catch (Exception localException2)
	    {
	    	paramHandler.sendEmptyMessage(19);
	        return;
	    }
	}

	public static UpdateInfo findUpdate(int i, int j)
	{
		Object aobj[] = new Object[2];
		aobj[0] = Integer.valueOf(i);
		aobj[1] = Integer.valueOf(j);
		String s = String.format("http://rarnu.7thgen.info/teacher/update.php?ver=%d&cardid=%d", aobj);
		JSONObject jsonobject;
		UpdateInfo updateinfo;
		try
		{
			jsonobject = new JSONObject(CallGet(s, "utf-8"));
			updateinfo = new UpdateInfo();
		}
		catch (Exception exception1)
		{
			return null;
		}
		try
		{
			updateinfo.setUpdateApk(jsonobject.getInt("apk"));
			updateinfo.setUpdateData(jsonobject.getInt("data"));
		}
		catch (Exception exception)
		{
			return updateinfo;
		}
		return updateinfo;
	}
}
