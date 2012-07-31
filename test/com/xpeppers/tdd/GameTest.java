package com.xpeppers.tdd;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class GameTest {

	
	private Game game;

	@Before
	public void setup() throws Exception {
		game = new Game();
	}
	
	@Test
	public void disasterGame() {
		rollsMany(20, 0);
		
		assertEquals(0, game.score());
	}

	
	@Test
	public void allOnes() throws Exception {
		rollsMany(20, 1);
		assertEquals(20, game.score());
		
	}
	
	@Test
	public void oneSpare() throws Exception {
		rollSpare();
		game.rolls(3);
		
		rollsMany(17, 0);
		
		assertEquals(16, game.score());
	}

	@Test
	public void oneStrike() throws Exception {
		rollsStrike();
		game.rolls(3);
		game.rolls(4);
		
		rollsMany(16, 0);
		
		assertEquals(24, game.score());
	}

	@Test
	public void gameWithNoMarks() throws Exception {
		for (int i = 0; i < 10; i++) {
			game.rolls(1);
			game.rolls(4);	
		}
		assertThat(game.score(), is(50));		
	}

	@Test
	public void gameWithNoMarksButTheLast() throws Exception {
		rollsMany(18, 4);
		
		game.rolls(2);
		game.rolls(8);
		game.rolls(3);
		
		assertThat(game.score(), is(8*9 + 2 + 8 + 3));
		
	}
	
	@Test
	public void sampleGame() throws Exception {
		game.rolls(1);
		game.rolls(4);

		game.rolls(4);
		game.rolls(5);
		
		game.rolls(6);
		game.rolls(4);
		
		game.rolls(5);
		game.rolls(5);

		game.rolls(10);
		
		game.rolls(0);
		game.rolls(1);

		game.rolls(7);
		game.rolls(3);

		game.rolls(6);
		game.rolls(4);

		game.rolls(10);

		game.rolls(2);
		game.rolls(8);

		game.rolls(6);
		
		game.rolls(2);
		game.rolls(1);

		assertThat(game.score(), is(133));
	}
	
	@Test
	public void perfectGame() throws Exception {
		for (int i = 0; i < 12; i++) {
			game.rolls(10);	
		}
		assertThat(game.score(), is(300));	
	}



	private void rollsStrike() {
		game.rolls(10);
	}

	private void rollSpare() {
		game.rolls(4);
		game.rolls(6);
	}
	
	private void rollsMany(int howManyRolls, int pins) {
		for (int i = 0; i < howManyRolls; i++) {
			game.rolls(pins);	
		}
	}
}
