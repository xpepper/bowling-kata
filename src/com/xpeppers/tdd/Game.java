package com.xpeppers.tdd;

public class Game {

	private int[] _throws = new int[21];
	private int currentThrow = 0;
	
	public void rolls(int pins) {
		_throws[currentThrow] = pins;
		currentThrow++;
	}

	public int score() {
		int score = 0;
		int frameIndex = 0;
		
		for (int frame = 0; frame < 10; frame++) {
			if (isStrike(frameIndex)) {
				score += strikeBonus(frameIndex);
				frameIndex += 1;
			}
			else if (isSpare(frameIndex))
			{
				score += spareBonus(frameIndex);
				frameIndex += 2;	
			}
			else 
			{
				score += _throws[frameIndex] + _throws[frameIndex+1];
				frameIndex += 2;					
			}
			
									
		}
		
		return score;
		
	}

	private int spareBonus(int frameIndex) {
		return 10 + _throws[frameIndex+2];
	}

	private int strikeBonus(int frameIndex) {
		return 10 + _throws[frameIndex+1] + _throws[frameIndex+2];
	}

	private boolean isStrike(int frameIndex) {
		return _throws[frameIndex] == 10;
	}

	private boolean isSpare(int frameIndex) {
		return _throws[frameIndex] + _throws[frameIndex+1] == 10;
	}

}
