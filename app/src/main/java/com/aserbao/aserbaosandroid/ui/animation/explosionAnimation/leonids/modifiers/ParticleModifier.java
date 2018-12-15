package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.modifiers;


import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.leonids.Particle;

public interface ParticleModifier {

	/**
	 * modifies the specific value of a particle given the current miliseconds
	 * @param particle
	 * @param miliseconds
	 */
	void apply(Particle particle, long miliseconds);

}
