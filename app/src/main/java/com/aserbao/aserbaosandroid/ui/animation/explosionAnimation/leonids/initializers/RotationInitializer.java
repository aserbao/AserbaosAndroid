package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.initializers;


import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.Particle;

import java.util.Random;

public class RotationInitializer implements ParticleInitializer {

	private int mMinAngle;
	private int mMaxAngle;

	public RotationInitializer(int minAngle, int maxAngle) {
		mMinAngle = minAngle;
		mMaxAngle = maxAngle;
	}

	@Override
	public void initParticle(Particle p, Random r) {
		p.mInitialRotation = (mMinAngle == mMaxAngle) ? mMinAngle : r.nextInt(mMaxAngle - mMinAngle) + mMinAngle;
	}

}
