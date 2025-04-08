package com.example.proyectogpt;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText entradaNombre;
    Button botonSaludo;
    TextView textoSaludo;

    private static final String TAG = "CicloDeVida";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MI APP: onCreate()");
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        entradaNombre = findViewById(R.id.entradaNombre);
        botonSaludo = findViewById(R.id.botonSaludo);
        textoSaludo = findViewById(R.id.textoSaludo);

        botonSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = entradaNombre.getText().toString();
                if (nombre.isEmpty()) {
                    textoSaludo.setTextColor(Color.RED);
                    textoSaludo.setText("Debes escribir tu nombre!");
                    entradaNombre.requestFocus();
                    return;
                }
                nombre = nombre.trim();
                nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
                nombre = nombre.replace("\n", " ");
                entradaNombre.setText(nombre);
                textoSaludo.setText("Hola, " + nombre + "!");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MI APP: onStart()");
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MI APP: onResume()");
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MI APP: onPause()");
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MI APP: onStop()");
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MI APP: onRestart()");
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MI APP: onDestroy()");
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }

}