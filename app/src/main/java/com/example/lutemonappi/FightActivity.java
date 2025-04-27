package com.example.lutemonappi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FightActivity extends AppCompatActivity {

    private TextView textView;
    private Button buttonFightToNavigator;
    ArrayList<Lutemon> lutemonsInFight = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        Storage storage = Storage.getInstance();
        for (Lutemon lutemon : storage.getLutemons()) {
            if (lutemon.getId() == 5) {
                lutemonsInFight.add(lutemon);
            }
        }
        if (lutemonsInFight.size() < 2) {
            textView = findViewById(R.id.textViewFight);
            textView.setText("Valitse kaksi Lutemonia taistelemaan!");
        }

        if (lutemonsInFight.size() == 2) {
            textView = findViewById(R.id.textViewFight);
            textView.setText("Lutemonit taistelevat ja ottavat mittaa toisistaan!!!" + "\n");
            Lutemon lutemon1 = lutemonsInFight.get(0);
            Lutemon lutemon2 = lutemonsInFight.get(1);

            // I learned here to create and run on a thread, because the app kept crashing in fightactivity //
            // https://www.youtube.com/watch?v=kpFwxJFYnOo&list=PLfuE3hOAeWhYspjqABfkf97AzW1XNXgjZ //
            // https://medium.com/@yossisegev/understanding-activity-runonuithread-e102d388fe93 //
            // https://www.tutorialspoint.com/how-do-we-use-runonuithread-in-android //
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Lutemon lutemon : lutemonsInFight) {
                        lutemon.setHealth(lutemon.getMaxHealth());
                        runOnUiThread(() -> textView.append("Lutemoni: " + lutemon.getColor() + "(" + lutemon.getName() + ")" + " hyök: " + lutemon.getAttack() + "; puol: " + lutemon.getDefense() + "; kok: " + lutemon.getExperience() + "; elämät: " + lutemon.getHealth()+"/"+lutemon.getMaxHealth() + "\n"));
                    }
                    // ChatGPT helped me fix a bug where the whole fight was printed at once //
                    // The health's were not printed correctly //
                    // Using Thread.sleep solved the problem by creating 2 second delay between attacks//
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    while (lutemon1.getHealth() > 0 && lutemon2.getHealth() > 0) {
                        // ChatGPT suggested me to check if Lutemons are alive before they attack //
                        // In my code they were attacking when they were already dead //
                        if (!lutemon1.aliveOrDead() || !lutemon2.aliveOrDead()) {
                            break;
                        }
                        String attack1 = lutemon1.attack(lutemon2);
                        // Copilot helped me to debug the logic, I had to append to textView in all of the attacks and defences //
                        // Before it was only after defences //
                        runOnUiThread(() -> {
                            textView.append(attack1 + "\n");
                            /*textView.append("Lutemoni: " + lutemon1.getColor() + "(" + lutemon1.getName() + ")" + " hyök: " + lutemon1.getAttack() + "; puol: " + lutemon1.getDefense() + "; kok: " + lutemon1.getExperience() + "; elämät: " + lutemon1.getHealth()+"/"+lutemon1.getMaxHealth() + "\n");
                            textView.append("Lutemoni: " + lutemon2.getColor() + "(" + lutemon2.getName() + ")" + " hyök: " + lutemon2.getAttack() + "; puol: " + lutemon2.getDefense() + "; kok: " + lutemon2.getExperience() + "; elämät: " + lutemon2.getHealth()+"/"+lutemon2.getMaxHealth() + "\n");
                            */
                        });
                        if (lutemon1.getHealth() <= 0) break;
                        if (lutemon2.getHealth() <= 0) break;

                        String defence1 = lutemon2.defence(lutemon1);
                        runOnUiThread(() -> {
                            textView.append(defence1 + "\n");
                            textView.append("Lutemoni: " + lutemon1.getColor() + "(" + lutemon1.getName() + ")" + " hyök: " + lutemon1.getAttack() + "; puol: " + lutemon1.getDefense() + "; kok: " + lutemon1.getExperience() + "; elämät: " + lutemon1.getHealth()+"/"+lutemon1.getMaxHealth() + "\n");
                            textView.append("Lutemoni: " + lutemon2.getColor() + "(" + lutemon2.getName() + ")" + " hyök: " + lutemon2.getAttack() + "; puol: " + lutemon2.getDefense() + "; kok: " + lutemon2.getExperience() + "; elämät: " + lutemon2.getHealth()+"/"+lutemon2.getMaxHealth() + "\n");
                        });

                        try {
                            // I learnt about thread.sleep() here //
                            // https://www.digitalocean.com/community/tutorials/thread-sleep-java //
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        if (!lutemon1.aliveOrDead() || !lutemon2.aliveOrDead()) {
                            break;
                        }
                        String attack2 = lutemon2.attack(lutemon1);
                        runOnUiThread(() -> {
                            textView.append(attack2 + "\n");
                            /*textView.append("Lutemoni: " + lutemon1.getColor() + "(" + lutemon1.getName() + ")" + " hyök: " + lutemon1.getAttack() + "; puol: " + lutemon1.getDefense() + "; kok: " + lutemon1.getExperience() + "; elämät: " + lutemon1.getHealth()+"/"+lutemon1.getMaxHealth() + "\n");
                            textView.append("Lutemoni: " + lutemon2.getColor() + "(" + lutemon2.getName() + ")" + " hyök: " + lutemon2.getAttack() + "; puol: " + lutemon2.getDefense() + "; kok: " + lutemon2.getExperience() + "; elämät: " + lutemon2.getHealth()+"/"+lutemon2.getMaxHealth() + "\n");

                            */
                        });

                        if (lutemon1.getHealth() <= 0) break;
                        if (lutemon2.getHealth()<= 0) break;

                        String defence2 = lutemon1.defence(lutemon2);
                        runOnUiThread(() -> {
                            textView.append(defence2 + "\n");
                            textView.append("Lutemoni: " + lutemon1.getColor() + "(" + lutemon1.getName() + ")" + " hyök: " + lutemon1.getAttack() + "; puol: " + lutemon1.getDefense() + "; kok: " + lutemon1.getExperience() + "; elämät: " + lutemon1.getHealth()+"/"+lutemon1.getMaxHealth() + "\n");
                            textView.append("Lutemoni: " + lutemon2.getColor() + "(" + lutemon2.getName() + ")" + " hyök: " + lutemon2.getAttack() + "; puol: " + lutemon2.getDefense() + "; kok: " + lutemon2.getExperience() + "; elämät: " + lutemon2.getHealth()+"/"+lutemon2.getMaxHealth() + "\n");
                        });
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    runOnUiThread(() -> {
                        if (lutemon1.getHealth() <= 0) {
                            lutemon1.setId(4);
                            lutemon2.setId(1);
                            lutemon1.setExperience(lutemon1.getExperience()+1);
                            lutemon2.setExperience(lutemon2.getExperience()+1);
                        } else if (lutemon2.getHealth() <= 0) {
                            lutemon2.setId(4);
                            lutemon1.setId(1);
                            lutemon1.setExperience(lutemon1.getExperience()+1);
                            lutemon2.setExperience(lutemon2.getExperience()+1);
                        }
                        storage.saveLutemons(FightActivity.this);
                    });
                }
            }).start();

        }
    }
        public void switchToNavigator (View view) {
            Storage storage = Storage.getInstance();
            // Copilot suggested to me to clear the list after fight //
            for (Lutemon lutemon : lutemonsInFight) {
                if (lutemon.getHealth() <= 0) {
                    lutemon.setId(4);
                } else {
                    lutemon.setId(1);
                    lutemon.setHealth(lutemon.getMaxHealth());
                }
            }
            storage.saveLutemons(this);
            lutemonsInFight.clear();

            if (textView != null) {
                textView.setText("");
            }

            Intent intent = new Intent(this, ActivityNavigator.class);
            startActivity(intent);
        }

        @Override
        public void onResume () {
            super.onResume();
        }

    }