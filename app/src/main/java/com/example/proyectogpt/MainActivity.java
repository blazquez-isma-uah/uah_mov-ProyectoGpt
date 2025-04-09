package com.example.proyectogpt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CicloDeVida";

    EditText entradaNombre;
    Button botonSaludo;
    Button botonGuardar;
    Button botonReiniciarNombres;
    TextView textoSaludo;

    String nombreGuardado;
    Integer contador;
    List<String> listaNombres;
    SharedPreferences sharedPreferences;
    NombreAdapter adaptador;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MI APP: onCreate()");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initWidgets();

        // Recuperar el nombre guardado de SharedPreferences si existe
        getSharedPreferences();

        // Configurar el RecyclerView
        getRecyclerView();

        botonSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSaludo();
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGuardar();
            }
        });

        botonReiniciarNombres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReiniciarNombres();
            }
        });

        botonSaludo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchSaludo(event);
            }
        });

    }

    private boolean onTouchSaludo(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                botonSaludo.setBackgroundColor(Color.YELLOW); // Al presionar
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                botonSaludo.setBackgroundColor(Color.MAGENTA); // Al soltar
                break;
        }
        return false;
    }

    private void onClickReiniciarNombres() {
        // Reiniciar la lista de nombres
        int previoSize = listaNombres.size();
        listaNombres.clear();
        adaptador.notifyItemRangeRemoved(0, previoSize);

        // Guardar en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("listaNombres", null);
        editor.apply();

        Toast.makeText(MainActivity.this, "Lista de nombres reiniciada", Toast.LENGTH_SHORT).show();
    }

    private void getRecyclerView() {
        RecyclerView miRecycler = findViewById(R.id.miRecycler);
        miRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Crear el adaptador y asignarlo al RecyclerView
        adaptador = new NombreAdapter(listaNombres);
        miRecycler.setAdapter(adaptador);
    }

    private void initWidgets() {
        entradaNombre = findViewById(R.id.entradaNombre);
        botonSaludo = findViewById(R.id.botonSaludo);
        botonGuardar = findViewById(R.id.botonGuardar);
        botonReiniciarNombres = findViewById(R.id.botonReiniciarNombres);
        textoSaludo = findViewById(R.id.textoSaludo);
        sharedPreferences = getSharedPreferences("misDatos", MODE_PRIVATE);
    }

    private void onClickGuardar() {
        String nombre = entradaNombre.getText().toString();
        if (!nombre.isEmpty()) {
            // Añadir el nombre a la lista
            if (!listaNombres.contains(nombre)) {
                listaNombres.add(nombre);
            }
            // Transformar la lista a JSON
            Gson gson = new Gson();
            String listaJson = gson.toJson(listaNombres);
            
            // Guardar en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nombreGuardado", nombre);
            if(contador == null) {
                contador = 0;
            }
            contador++;
            editor.putInt("contador", contador);
            editor.putString("listaNombres", listaJson);
            editor.apply(); // o commit() para guardar inmediatamente

            adaptador.notifyItemInserted(listaNombres.size() - 1);

            // Mostrar mensaje utilizando snackbar
            Snackbar.make(findViewById(android.R.id.content), "Nombre guardado con éxito", Snackbar.LENGTH_LONG).show();
//            Toast.makeText(MainActivity.this, "🚀 Nombre guardado con éxito", Toast.LENGTH_LONG).show();
        }
    }

    private void onClickSaludo() {
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

    private void getSharedPreferences() {
        nombreGuardado = sharedPreferences.getString("nombreGuardado", null);
        contador = sharedPreferences.getInt("contador", 0);

        String json = sharedPreferences.getString("listaNombres", null);
        Gson gson = new Gson();
        if (json != null) {
            Type type = new TypeToken<List<String>>() {}.getType();
            listaNombres = gson.fromJson(json, type);
        } else {
            listaNombres = new ArrayList<>();
        }

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