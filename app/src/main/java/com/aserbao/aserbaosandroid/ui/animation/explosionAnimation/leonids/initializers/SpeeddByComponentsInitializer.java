package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.initializers;


import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.Particle;

import java.util.Random;

public class SpeeddByComponentsInitializer implements ParticleInitializer {

	private float mMinSpeedX;
	private float mMaxSpeedX;
	private float mMinSpeedY;
	private float mMaxSpeedY;

	public SpeeddByComponentsInitializer(float speedMinX, float speedMaxX, float speedMinY, float speedMaxY) {
		mMinSpeedX = speedMinX;
		mMaxSpeedX = speedMaxX;
		mMinSpeedY = speedMinY;
		mMaxSpeedY = speedMaxY;
	}

	@Override
	public void initParticle(Particle p, Random r) {
		p.mSpeedX = r.nextFloat()*(mMaxSpeedX-mMinSpeedX)+mMinSpeedX;
		p.mSpeedY = r.nextFloat()*(mMaxSpeedY-mMinSpeedY)+mMinSpeedY;
	}

}
