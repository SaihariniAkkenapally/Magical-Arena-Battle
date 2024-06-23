package com.swiggy.assignment.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.swiggy.assignment.player.Player;

public class Game {

	public static class GameBuilder {

		private List<Player> players;

		public GameBuilder players(List<Player> players) {
			this.players = players;
			return this;
		}

		public Game build() {
			Game game = new Game();
			game.players = this.players;
			return game;
		}
	}

	private Random random = new Random();
	private List<Player> players;

	public List<Player> getPlayers() {
		return players;
	}

	public static GameBuilder builder() {
		return new GameBuilder();
	}

	public Player play() {
		Player playerWithMinimumHealth = getPlayerWithMinimumHealth(players);
		List<Player> newPlayers = new ArrayList<>();
		newPlayers.add(playerWithMinimumHealth);
		newPlayers.addAll(getOtherPlayers(playerWithMinimumHealth, players));
		players = newPlayers;
		return playNextTurn();
	}

	Player getPlayerWithMinimumHealth(List<Player> players) {
		Player playerWithMinimumHealth = players.get(0);
		int minHealth = playerWithMinimumHealth.getHealth();
		for (int index = 1; index < players.size(); index++) {
			if (players.get(index).getHealth() <= minHealth) {
				playerWithMinimumHealth = players.get(index);
				minHealth = players.get(index).getHealth();
			}
		}
		return playerWithMinimumHealth;
	}

	List<Player> getOtherPlayers(Player attacker, List<Player> players) {
		List<Player> otherPlayers = new ArrayList<>();
		for (Player player : players) {
			if (!player.equals(attacker)) {
				otherPlayers.add(player);
			}
		}
		return otherPlayers;
	}

	private void attackDefenders(Player attacker, List<Player> defenders) {
		Player defender = getPlayerWithMinimumHealth(defenders);
		int attackerPower = attacker.getAttack() * rollDice();
		int defenderPower = defender.getStrength() * rollDice();
		int damage = Math.max(attackerPower - defenderPower, 0);
		int defenderHealth = defender.getHealth() - damage;
		defender.setHealth(defenderHealth);
		if (defender.getHealth() <= 0) {
			defenders.remove(defender);
			players.remove(defender);
		}
	}

	int rollDice() {
		return random.nextInt(1, 7);
	}

	private Player playNextTurn() {
		if (players.size() == 1) {
			return players.get(0);
		} else {
			Player attacker = players.get(0);
			List<Player> defenders = getOtherPlayers(attacker, players);
			attackDefenders(attacker, defenders);
			insertAttackerAtTheEnd(attacker);
			return playNextTurn();
		}
	}

	void insertAttackerAtTheEnd(Player attacker) {
		List<Player> otherPlayers = getOtherPlayers(attacker, players);
		otherPlayers.add(attacker);
		players = otherPlayers;
	}

}
