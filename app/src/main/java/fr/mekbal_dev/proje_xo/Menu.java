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

public class Menu extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_activity);

        findViewById(R.id.button_version_3x3).setOnClickListener(view -> {
            // Ouvrir l'activité pour la version 3x3
            Intent intent = new Intent(Menu.this, MainActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.button_version_4x4).setOnClickListener(view -> {
            // Ouvrir l'activité pour la version 4x4
            Intent intent = new Intent(Menu.this, MainActivity_4x4.class);
            startActivity(intent);
        });


    }
}
