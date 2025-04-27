package com.example.lutemonappi;

import java.io.Serializable;


public class Lutemon implements Serializable {

    public Lutemon(String name, String color) {
        this.name = name;
        this.color = color;
        this.attack = 0;
        this.defense = 0;
        this.id = 1;
        this.experience = 0;
        this.maxHealth = 0;
        this.losses = 0;
        // I used this website to get drawables //
        // https://opengameart.org/content/spiky-monster-obstacle //
        this.image = R.drawable.black;


        // DeepSeek helped me to ideate method to get the default values for colors //
        switch (color) {
            case "Valkoinen":
                this.attack = 5;
                this.defense = 4;
                this.maxHealth = 20;
                this.image = R.drawable.white;
                break;
            case "Vihreä":
                this.attack = 6;
                this.defense = 3;
                this.maxHealth = 19;
                this.image = R.drawable.green;
                break;
            case "Pinkki":
                this.attack = 7;
                this.defense = 2;
                this.maxHealth = 18;
                this.image = R.drawable.pink;
                break;
            case "Oranssi":
                this.attack = 8;
                this.defense = 1;
                this.maxHealth = 17;
                this.image = R.drawable.orange;
                break;
            case "Musta":
                this.attack = 9;
                this.defense = 0;
                this.maxHealth = 16;
                this.image = R.drawable.black;
                break;

        }
        this.health = this.maxHealth;
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
        if (health <= 0) {
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

    public String printSpecs() {
        String specs = name + " : " + color + ", " + health;
        return specs;
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

    public int getOrder() {
        return order;
    }

    public int getIdCounter() {
        return idCounter;
    }
    public String defence(Lutemon lutemon) {
        // ChatGPT helped me to understand that I have to avoid negative numbers //
        // which came up when I ran the code //
        // I avoided them by using MAth.max //
        // COpilot helped me to debug this logic, I had the lutemon.get... and this.defen.. in incorrect order //
        int damage = Math.max(lutemon.getAttack()- this.defense, 0);
        this.setHealth(Math.max(0, this.getHealth()-damage));
        String fight = this.color+"("+this.name+")"+" puolustautuu!";
        if (!this.aliveOrDead()) {
            fight += "\n"+this.name+" kuoli! Lepää Rauhassa.";
            // Copilot helped me fix a bug where a Lutemon was removed from the storage after a fight //
            // I only setId(4), not remove it //
            this.setId(4);
        }
        return fight;
    }
    public String attack(Lutemon lutemon) {
        if (this.health <= 0) {
            return "Taistelu päättyi!";
        }
        int damage = Math.max(this.attack - lutemon.getDefense(), 0);
        lutemon.setHealth(Math.max(0, lutemon.getHealth()-damage));
        String fight = this.color+"("+this.name+")"+" hyökkää Lutemonia "+ lutemon.getColor()+"("+lutemon.getName() + ")" +" kohti.";
        if (!lutemon.aliveOrDead()) {
            fight += "\n"+lutemon.getName() + " kuoli! Lepää Rauhassa.";
            lutemon.setId(4);
        }
        return fight;
    }


    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    protected int image;
    protected int losses;

    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int experience;
    protected int health;
    protected int maxHealth;
    protected int id;
    protected int idCounter;
    protected int order;
}
