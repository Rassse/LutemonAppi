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

    public void printSpecs(int order) {
        System.out.println(order + name + " : " + color + ", " + health);
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
    public void defence(Lutemon lutemon) {
        int damage = this.defense - lutemon.getAttack();
        // Copilot helped me to check if damage is negative //
        if (damage < 0) {
            damage = 0;
        }
        int healthAfterAttack = this.health - damage;
        this.health = Math.max(healthAfterAttack, 0);

        System.out.println(this.name+" defended against "+ lutemon.getName());
        System.out.println("And took this amount of damage "+ damage);
    }
    public void attack(Lutemon lutemon) {
        int damage = this.attack - lutemon.getDefense();
        if (damage < 0) {
            damage = 0;
        }
        int healthAfterAttack = lutemon.getHealth() - damage;
        lutemon.setHealth(Math.max(healthAfterAttack, 0));
        this.experience += 1;
        System.out.println(this.name+" attackec"+" "+lutemon.getName()+" and damaged this amount "+ damage);
        System.out.println(lutemon.getName()+" health is "+lutemon.getHealth());

        if (lutemon.getHealth() == 0) {
            System.out.println(lutemon.getName() + " died! R.I.P.");
            Storage.getInstance().removeLutemon(lutemon.getId());
        }
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
    protected int order;
}
