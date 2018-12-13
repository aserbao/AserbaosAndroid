package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

class ParticleField extends View {

	private ArrayList<Particle> mParticles;

	public ParticleField(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public ParticleField(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ParticleField(Context context) {
		super(context);
	}

	public void setParticles(ArrayList<Particle> particles) {
		mParticles = particles;
	}

	private static final String TAG = "ParticleField";
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.e(TAG, "onDraw: " );
		// Draw all the particles
		synchronized (mParticles) {
			for (int i = 0; i < mParticles.size(); i++) {
				mParticles.get(i).draw(canvas);
			}
		}
	}
}
