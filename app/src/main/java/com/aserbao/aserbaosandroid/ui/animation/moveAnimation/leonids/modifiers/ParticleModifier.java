package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.leonids.modifiers;


import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.leonids.Particle;

public interface ParticleModifier {

	/**
	 * modifies the specific value of a particle given the current miliseconds
	 * @param particle
	 * @param miliseconds
	 */
	void apply(Particle particle, long miliseconds);

}
