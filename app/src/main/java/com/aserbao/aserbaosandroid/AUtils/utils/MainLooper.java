package com.aserbao.aserbaosandroid.AUtils.utils;

import android.os.Handler;
import android.os.Looper;

public class MainLooper extends Handler
{
	private static MainLooper instance = null;

	public static MainLooper getInstance ()
	{
		if(instance == null)
		{
			synchronized (MainLooper.class)
			{
				if(instance == null)
				{
					instance = new MainLooper();
				}
			}
		}
		return instance;
	}

	private MainLooper()
	{
		super(Looper.getMainLooper());
	}

	public void runOnUiThread (Runnable runnable)
	{
		if (Looper.myLooper() == Looper.getMainLooper())
		{
			runnable.run();
		}
		else
		{
			post(runnable);
		}
	}
}
