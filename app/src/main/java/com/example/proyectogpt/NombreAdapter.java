package com.example.proyectogpt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NombreAdapter extends RecyclerView.Adapter<NombreAdapter.ViewHolder> {

    private List<String> listaNombres;

    public NombreAdapter(List<String> listaNombres) {
        if (listaNombres != null) {
            this.listaNombres = listaNombres;
        }else {
            this.listaNombres = new ArrayList<>();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView texto;

        public ViewHolder(View itemView) {
            super(itemView);
            texto = itemView.findViewById(R.id.textoItem);
        }
    }

    @NonNull
    @Override
    public NombreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nombre, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull NombreAdapter.ViewHolder holder, int position) {
        holder.texto.setText(listaNombres.get(position));
    }

    @Override
    public int getItemCount() {
        return listaNombres.size();
    }
}
