package com.swiggy.assignment;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Player firstPlayer = Player.builder().health(50).attack(10).strength(5).build();
		Player secondPlayer = Player.builder().health(100).attack(7).strength(7).build();

		List<Player> players = List.of(firstPlayer,secondPlayer);
		Game game = Game.builder().players(players).build();
		Player winner = game.play();
		System.out.println("Winner : "+ winner);
	}
}