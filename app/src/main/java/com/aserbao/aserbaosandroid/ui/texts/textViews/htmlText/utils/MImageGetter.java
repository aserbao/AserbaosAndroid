package com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MImageGetter implements ImageGetter {
		Context c;
		
	    public MImageGetter(TextView text, Context c) {
	        this.c = c;
	    }
	
	 public Drawable getDrawable(String source) {
		    Drawable drawable = null;
		    InputStream is = null;
			try {
				is = c.getResources().getAssets().open(source);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            try {
                TypedValue typedValue = new TypedValue();
                typedValue.density = TypedValue.DENSITY_DEFAULT;
                drawable = Drawable.createFromResourceStream(null, typedValue, is, "src");
                DisplayMetrics dm = c.getResources().getDisplayMetrics();
        		int dwidth = dm.widthPixels-10;//padding left + padding right 
        		float dheight = (float)drawable.getIntrinsicHeight()*(float)dwidth/(float)drawable.getIntrinsicWidth();
        		int dh = (int)(dheight+0.5);
        		int wid = dwidth;
                int hei = dh;
                /*int wid = drawable.getIntrinsicWidth() > dwidth? dwidth: drawable.getIntrinsicWidth();
                int hei = drawable.getIntrinsicHeight() > dh ? dh: drawable.getIntrinsicHeight();*/
                drawable.setBounds(0, 0, wid, hei);
                return drawable;
            } catch (Exception e) {
            	System.out.println(e);
                return null;
            }
	       
	    }
	 


}
