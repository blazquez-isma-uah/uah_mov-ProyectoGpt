package com.example.proyectogpt;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    Button botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        TextView textoSaludo = findViewById(R.id.textoRecibido);

        // Recoger el Intent y el dato
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombreUsuario");
            if(nombre != null && !nombre.isEmpty()) {
                textoSaludo.setTextColor(Color.BLUE);
                textoSaludo.setTextSize(30);
                textoSaludo.setText("Hola, " + nombre + "!");
            } else {
                textoSaludo.setTextColor(Color.RED);
                textoSaludo.setTextSize(20);
                textoSaludo.setText("Error: No se recibió el nombre.");
            }
        }

        botonVolver = findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(v -> {
            finish();
        });

    }
}