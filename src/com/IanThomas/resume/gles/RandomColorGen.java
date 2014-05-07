package com.IanThomas.resume.gles;

import java.util.Random;

public class RandomColorGen {

	private static final int[] COLOR_CHOICES = { 0xff3232, 0x32ff32, 0x3232ff,
			0xffff32, 0x32ffff, 0xff32ff, 0xff7f32, 0x7fff32, 0x32ff7f,
			0x327fff, 0x7f32ff, 0xff327f };

	public int red, green, blue, color;

	private double percentageCompleted;
	private int prevColor, randomColor, totalTimeLapse, startRed, startGreen,
			startBlue, endRed, endGreen, endBlue;
	private long stopTime;
	private static Random random = new Random();

	public RandomColorGen(int rateInSeconds) {
		totalTimeLapse = (rateInSeconds * 1000);
		final int randColor = COLOR_CHOICES[random
				.nextInt(COLOR_CHOICES.length)];
		red = (randColor >> 16) & 0xff;
		green = (randColor >> 8) & 0xff;
		blue = randColor & 0xff;
		newRandomColor();
	}

	/**
	 * Change the max steps (rate) at which the colors will fade
	 * 
	 * @param rateInSeconds
	 */
	public void changeRate(int rateInSeconds) {
		stopTime = 0;
		totalTimeLapse = (rateInSeconds * 1000);
	}

	/**
	 * Returns the next stepped color or an entirely new color totalTimeLapse =
	 * 10 stopTime = 10 system time = 2
	 * 
	 * @return
	 */
	public void updateColor() {
		if (System.currentTimeMillis() > stopTime) {
			newRandomColor();
		} else {
			percentageCompleted = (totalTimeLapse - (int) (stopTime - System
					.currentTimeMillis())) / (double) totalTimeLapse;
			red = (int) (startRed + ((endRed - startRed) * percentageCompleted));
			green = (int) (startGreen + ((endGreen - startGreen) * percentageCompleted));
			blue = (int) (startBlue + ((endBlue - startBlue) * percentageCompleted));

			// Mask 255 on alpha channel
			color = 0xFF000000 | (red << 16) | (green << 8) | blue;
		}
	}

	/**
	 * Generate a new random color and reset the current step to zero
	 */
	private void newRandomColor() {
		prevColor = randomColor;
		while (randomColor == prevColor) {
			randomColor = COLOR_CHOICES[random
					.nextInt(COLOR_CHOICES.length - 1)];
		}

		endRed = (randomColor >> 16) & 0xff;
		endGreen = (randomColor >> 8) & 0xff;
		endBlue = (randomColor >> 0) & 0xff;

		stopTime = System.currentTimeMillis() + totalTimeLapse;

		startRed = red;
		startGreen = green;
		startBlue = blue;

		color = red;
		color = (color << 8) + green;
		color = (color << 8) + blue;
	}

}
