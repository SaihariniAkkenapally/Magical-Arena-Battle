package com.swiggy.assignment.player;

import java.util.Objects;

public class Player {

	public static class PlayerBuilder {
		private int health;
		private int strength;
		private int attack;

		public PlayerBuilder health(int health) {
			this.health = health;
			return this;
		}

		public PlayerBuilder strength(int strength) {
			this.strength = strength;
			return this;
		}

		public PlayerBuilder attack(int attack) {
			this.attack = attack;
			return this;
		}

		public Player build() {
			Player player = new Player();
			player.health = this.health;
			player.strength = this.strength;
			player.attack = this.attack;
			player.id = Objects.hash(attack, health, strength);
			return player;
		}
	}

	private int id;
	private int health;
	private int strength;
	private int attack;

	public int getStrength() {
		return strength;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public boolean isAlive() {
		return health > 0;
	}

	public static PlayerBuilder builder() {
		return new PlayerBuilder();
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", health=" + health + ", strength=" + strength + ", attack=" + attack + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attack, health, strength);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return attack == other.attack && health == other.health && strength == other.strength && id == other.id;
	}
}
