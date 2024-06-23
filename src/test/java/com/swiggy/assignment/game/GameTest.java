package com.swiggy.assignment.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.swiggy.assignment.player.Player;

class GameTest {
	@Test
	void testPlay() {
		Game game = startGame();
		Player winner = game.play();
		assertEquals(winner, game.getPlayers().get(0));
	}

	@Test
	void testGetOtherPlayers() {
		Game game = startGame();
		List<Player> players = game.getPlayers();
		Player attacker = game.getPlayers().get(0);
		List<Player> otherPlayers = new ArrayList<>(players);
		otherPlayers.remove(attacker);
		assertEquals(otherPlayers, game.getOtherPlayers(attacker, players));
	}

	@Test
	void testInsertAttackerAtTheEnd() {
		Game game = startGame();
		List<Player> players = game.getPlayers();
		Player attacker = game.getPlayerWithMinimumHealth(players);
		List<Player> otherPlayersList = game.getOtherPlayers(attacker, players);
		otherPlayersList.add(attacker);

		game.insertAttackerAtTheEnd(attacker);
		List<Player> playersAfterInsertion = game.getPlayers();
		assertEquals(otherPlayersList, playersAfterInsertion);
		for (int index = 0; index < otherPlayersList.size(); index++)
			assertEquals(otherPlayersList.get(index), playersAfterInsertion.get(index));
	}

	@Test
	void testGetPlayers() {
		Player firstPlayer = playerCreation(50, 10, 5);
		Player secondPlayer = playerCreation(80, 5, 6);
		List<Player> players = List.of(firstPlayer, secondPlayer);
		Game game = gameCreation(players);
		assertEquals(players, game.getPlayers());
	}

	@Test
	void testGetPlayerWithMinimumHealth() {
		Game game = startGame();
		List<Player> players = game.getPlayers();
		assertEquals(50, game.getPlayerWithMinimumHealth(players).getHealth());
	}

	@Test
	void testRollDice() {
		Game game = startGame();
		int diceNumber = game.rollDice();
		assertTrue(diceNumber >= 1 && diceNumber <= 6);
	}

	Player playerCreation(int health, int attack, int strength) {
		Player player = Player.builder().health(health).attack(attack).strength(strength).build();
		return player;
	}

	Game gameCreation(List<Player> playersList) {
		List<Player> players = playersList;
		Game game = Game.builder().players(players).build();
		return game;
	}

	Game startGame() {
		List<Player> players = List.of(playerCreation(100, 12, 9), playerCreation(50, 10, 4), playerCreation(90, 5, 6));
		Game game = gameCreation(players);
		return game;
	}

}
