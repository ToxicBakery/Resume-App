package com.IanThomas.resume.utils;

public class ResumeMath {

	public static final float clamp(float val, float min, float max) {
		return val < min ? min : val > max ? max : val;
	}

}
