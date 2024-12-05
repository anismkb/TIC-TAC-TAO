package fr.mekbal_dev.proje_xo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.format.TextStyle;

public class MainActivity extends AppCompatActivity {

    private int [] tv = {0,0,0,0,0,0,0,0,0} ;
    private Button [] tb = new Button[9] ;

    private boolean isPlayerOne = true;     // Vrai si c'est le tour du joueur 1
    private boolean isGameActive = true;    // Permet de stopper le jeu si terminé

    private TextView tvPlayer, tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tb[0] = findViewById(R.id.button1);
        tb[1] = findViewById(R.id.button2);
        tb[2] = findViewById(R.id.button3);
        tb[3] = findViewById(R.id.button4);
        tb[4] = findViewById(R.id.button5);
        tb[5] = findViewById(R.id.button6);
        tb[6] = findViewById(R.id.button7);
        tb[7] = findViewById(R.id.button8);
        tb[8] = findViewById(R.id.button9);

        tvPlayer=findViewById(R.id.textView);
        tvResult=findViewById(R.id.textView2);

        Button btnReplay = findViewById(R.id.button10);
        btnReplay.setOnClickListener(v -> resetGame());

        findViewById(R.id.back2).setOnClickListener(view -> {
            // Ouvrir l'activité pour la version 3x3
            //Intent intent = new Intent(MainActivity.this, Menu.class);
            //startActivity(intent);
            finish();
        });
    }

    /*
    public void clic(View view) {
        String key = (String) view.getTag() ;
    }
     */

    public void clic(View view) {
        if (!isGameActive) return; // Si le jeu est terminé, ignorer les clics

        Button clickedButton = (Button) view;
        int index = Integer.parseInt((String) clickedButton.getTag());

        // Vérifie si la case est déjà jouée
        if (tv[index] != 0) return;

        // Marquer la case et mettre à jour l'interface
        tv[index] = isPlayerOne ? 1 : 2;
        clickedButton.setBackgroundResource(isPlayerOne ? R.drawable.x_shape : R.drawable.o_shape);

        // Vérifie si un joueur a gagné ou si la partie est nulle
        if (checkWin()) {
            tvResult.setText(isPlayerOne ? "Joueur 1 gagne !" : "Joueur 2 gagne !");
            isGameActive = false;
        } else if (isDraw()) {
            tvResult.setText("Partie nulle !");
            isGameActive = false;
        } else {
            // Change de joueur
            isPlayerOne = !isPlayerOne;
            tvPlayer.setText(isPlayerOne ? "Joueur 1" : "Joueur 2");
        }
    }

    private boolean checkWin() {
        // Vérifie les lignes, colonnes et diagonales
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Lignes
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Colonnes
                {0, 4, 8}, {2, 4, 6}             // Diagonales
        };

        for (int[] condition : winConditions) {
            if (tv[condition[0]] == tv[condition[1]] &&
                    tv[condition[1]] == tv[condition[2]] &&
                    tv[condition[0]] != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (int value : tv) {
            if (value == 0) return false;
        }
        return true;
    }

    private void resetGame() {
        // Réinitialise les données
        for (int i = 0; i < tv.length; i++) {
            tv[i] = 0;
            tb[i].setBackgroundResource(android.R.color.transparent);
        }
        isGameActive = true;
        isPlayerOne = true;
        tvPlayer.setText("Joueur 1");
        tvResult.setText("");
    }

}