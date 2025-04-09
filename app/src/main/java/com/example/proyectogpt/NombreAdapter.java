package com.example.proyectogpt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView texto;

        public ViewHolder(View itemView) {
            super(itemView);
            texto = itemView.findViewById(R.id.textoItem);

            // Eliminar el nombre al hacer clic largo
            itemView.setOnLongClickListener( v -> {
                int position = getAdapterPosition();
                String nombreEliminado = listaNombres.get(position);
                if (position != RecyclerView.NO_POSITION) {
                    // Eliminar el nombre de la lista
                    listaNombres.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(v.getContext(), "Eliminado: " + nombreEliminado, Toast.LENGTH_SHORT).show();
                    return true; // Indica que el evento fue manejado
                }
                return false; // Indica que el evento no fue manejado
            });
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
