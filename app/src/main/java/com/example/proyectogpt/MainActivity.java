package com.example.proyectogpt;

import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText entradaNombre;
    Button botonSaludo;
    Button botonGuardar;
    TextView textoSaludo;

    Integer contador;

    private static final String TAG = "CicloDeVida";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MI APP: onCreate()");
//        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        entradaNombre = findViewById(R.id.entradaNombre);
        botonSaludo = findViewById(R.id.botonSaludo);
        botonGuardar = findViewById(R.id.botonGuardar);
        textoSaludo = findViewById(R.id.textoSaludo);

        // Configurar el RecyclerView
        RecyclerView miRecycler = findViewById(R.id.miRecycler);
        miRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Crear una lista de nombres (puedes reemplazar esto con tus datos)
        List<String> nombres = List.of("Ismael", "Juan", "María", "Pedro", "Ana", "Luis");

        // Crear el adaptador y asignarlo al RecyclerView
        NombreAdapter adaptador = new NombreAdapter(nombres);
        miRecycler.setAdapter(adaptador);


        // Recuperar el nombre guardado de SharedPreferences si existe
        getSharedPreferencesName();

        botonSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = entradaNombre.getText().toString();
                if (nombre.isEmpty()) {
                    textoSaludo.setTextColor(Color.RED);
                    textoSaludo.setText("Debes escribir tu nombre!");
                } else {
                    nombre = nombre.trim();
                    nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
                    nombre = nombre.replace("\n", " ");
                    textoSaludo.setTextColor(Color.BLUE);
                    textoSaludo.setTextSize(30);
                    textoSaludo.setText("Hola, " + nombre + "!");
                    entradaNombre.setText(nombre);
                }

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("nombreUsuario", nombre);
                startActivity(intent);
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = entradaNombre.getText().toString();
                if (!nombre.isEmpty()) {
                    // Guardar en SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("misDatos", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombreGuardado", nombre);
                    if(contador == null) {
                        contador = 0;
                    }
                    contador++;
                    editor.putInt("contador", contador);
                    editor.apply(); // o commit() para guardar inmediatamente

                    Toast.makeText(MainActivity.this, "Nombre guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getSharedPreferencesName() {
        SharedPreferences sharedPreferences = getSharedPreferences("misDatos", MODE_PRIVATE);
        String nombreGuardado = sharedPreferences.getString("nombreGuardado", null);
        contador = sharedPreferences.getInt("contador", 0);

        if (nombreGuardado != null) {
            entradaNombre.setText(nombreGuardado);
            textoSaludo.setTextColor(Color.BLUE);
            textoSaludo.setTextSize(30);
            textoSaludo.setText("Hola de nuevo " + nombreGuardado + "! -> " + contador);
        } else {
            textoSaludo.setTextColor(Color.RED);
            textoSaludo.setTextSize(20);
            textoSaludo.setText("No se encontró un nombre guardado. -> " + contador);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MI APP: onStart()");
//        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MI APP: onResume()");
//        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MI APP: onPause()");
//        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MI APP: onStop()");
//        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MI APP: onRestart()");
//        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MI APP: onDestroy()");
//        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }

}