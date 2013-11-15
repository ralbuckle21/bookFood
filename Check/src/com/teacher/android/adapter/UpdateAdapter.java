// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.teacher.android.adapter;

import android.view.*;
import android.widget.*;
import java.util.List;

import com.teacher.android.R;

// Referenced classes of package com.teacher.android.adapter:
//			UpdateHolder

public class UpdateAdapter extends BaseAdapter
{

	private LayoutInflater inflater;
	private List list;

	public UpdateAdapter(LayoutInflater layoutinflater, List list1)
	{
		inflater = layoutinflater;
		list = list1;
	}

	public int getCount()
	{
		return list.size();
	}

	public Object getItem(int i)
	{
		return list.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		String s = (String)list.get(i);
		View view1;
		UpdateHolder updateholder;
		if (view == null)
			view1 = inflater.inflate(R.layout.updateitem, viewgroup, false);
		else
			view1 = view;
		updateholder = (UpdateHolder)view1.getTag();
		if (updateholder == null)
		{
			updateholder = new UpdateHolder();
			updateholder.tvUpdateName = (TextView)view1.findViewById(R.id.tvUpdateName);
			updateholder.pbDownloading = (ProgressBar)view1.findViewById(R.id.pbDownloading);
			view1.setTag(updateholder);
		}
		if (s != null)
			updateholder.tvUpdateName.setText(s);
		return view1;
	}
}
