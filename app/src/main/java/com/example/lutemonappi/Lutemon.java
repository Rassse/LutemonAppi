package com.example.lutemonappi;

import java.io.Serializable;


public class Lutemon implements Serializable {

    public Lutemon(String name, String color) {
        this.name = name;
        this.color = color;
        this.attack = 0;
        this.defense = 0;
        this.id = idCounter;
        this.experience = 0;
        this.maxHealth = 0;


        // DeepSeek helped me to ideate method to get the default values for colors //
        if (color == "Valkoinen") {
            this.attack = 5;
            this.defense = 4;
            this.maxHealth = 20;
        }
        else if (color == "Vihreä") {
            this.attack = 6;
            this.defense = 3;
            this.maxHealth = 19;
        }
        else if (color == "Pinkki") {
            this.attack = 7;
            this.defense = 2;
            this.maxHealth = 18;
        }
        else if (color == "Oranssi") {
            this.attack = 8;
            this.defense = 1;
            this.maxHealth = 17;
        }
        else if (color == "Musta") {
            this.attack = 9;
            this.defense = 0;
            this.maxHealth = 16;
        }

    }

    public int whereIsLutemon() {
        if (health == 0) {
            return 0;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean aliveOrDead() {
        if (health == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAttack() {
        return attack;
    }

    public void printSpecs() {
        System.out.println(name + " : " + color + ", " + health);
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }


    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int experience;
    protected int health;
    protected int maxHealth;
    protected int id;
    protected int idCounter;
}
