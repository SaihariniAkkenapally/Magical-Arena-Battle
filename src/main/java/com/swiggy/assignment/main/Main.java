package com.swiggy.assignment.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.swiggy.assignment.game.Game;
import com.swiggy.assignment.player.Player;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Player> players = new ArrayList<>();

		System.out.println("Welcome to the Magical Arena!\n\nEnter the number of players: ");
		int numberOfPlayers = scanner.nextInt();
		for (int index = 0; index < numberOfPlayers; index++) {
			System.out.println("Enter details for Player" + (index + 1) + ": ");

			System.out.print("Health: ");
			int health = scanner.nextInt();

			System.out.print("Strength: ");
			int strength = scanner.nextInt();

			System.out.print("Attack: ");
			int attack = scanner.nextInt();

			Player player = Player.builder().health(health).attack(attack).strength(strength).build();
			players.add(player);
		}

		Game game = Game.builder().players(players).build();
		Player winner = game.play();
		System.out.println("Winner : " + winner);
		scanner.close();
	}
}