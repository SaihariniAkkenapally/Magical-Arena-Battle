package com.swiggy.assignment.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testPlayerCreation() {
		Player player = playerCreation(100, 10, 9);
		assertEquals(100, player.getHealth());
		assertEquals(10, player.getAttack());
		assertEquals(9, player.getStrength());
	}

	@Test
	void testGetHealth() {
		Player player = playerCreation(80, 10, 9);
		assertEquals(80, player.getHealth());
	}

	@Test
	void testGetStrength() {
		Player player = playerCreation(80, 10, 9);
		assertEquals(9, player.getStrength());
	}

	@Test
	void testGetAttack() {
		Player player = playerCreation(80, 10, 9);
		assertEquals(10, player.getAttack());
	}

	@Test
	void testIsAlive() {
		Player player = playerCreation(80, 3, 4);
		assertEquals(true, player.isAlive());
	}

	@Test
	void testSetHealth() {
		Player player = playerCreation(100, 10, 10);
		player.setHealth(90);
		assertEquals(90, player.getHealth());
	}

	@Test
	void testToString() {
		Player player = playerCreation(100, 20, 10);
		String expected = "Player [id=" + player.hashCode() + ", health=100, strength=10, attack=20]";
		assertEquals(expected, player.toString());
	}

	@Test
	void testHashCode() {
		Player player1 = playerCreation(100, 10, 20);
		Player player2 = playerCreation(100, 10, 20);
		assertEquals(player1.hashCode(), player2.hashCode());
	}

	@Test
	void testEquals() {
		Player player1 = playerCreation(100, 10, 20);
		Player player2 = playerCreation(100, 10, 20);

		assertTrue(player1.equals(player2));
		assertFalse(player1.equals(null));
		assertFalse(player1.equals(new Object()));
		assertTrue(player1.equals(player1));
	}

	@Test
	void testNotEqualByAttributes() {
		Player player1 = playerCreation(100, 10, 20);
		Player player2 = playerCreation(90, 10, 20);

		assertFalse(player1.equals(player2));
	}

	Player playerCreation(int health, int attack, int strength) {
		Player player = Player.builder().health(health).attack(attack).strength(strength).build();
		return player;
	}

}
