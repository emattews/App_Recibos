package Hka.prueba.recibodeventa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Hka.prueba.recibodeventa.R;
import Hka.prueba.recibodeventa.entity.Recibos;

public class ReciboAdapter extends RecyclerView.Adapter<ReciboAdapter.reciboViewHolder>{

    ArrayList<Recibos> recibosArrayList;
    public ReciboAdapter(ArrayList<Recibos> recibosArrayList){
        this.recibosArrayList = recibosArrayList;
    }

    @NonNull
    @Override
    public reciboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_recibos, null, false);
        return new reciboViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull reciboViewHolder holder, int position) {
        holder.tvNombre_cliente.setText(recibosArrayList.get(position).getNomnbre_cliente());
        holder.tvEmisor.setText(recibosArrayList.get(position).getRazon_emisor());
        holder.tvCantidad_items.setText(recibosArrayList.get(position).getTotal_items());
        holder.tvTotal.setText(recibosArrayList.get(position).getTotal_recibo());

    }

    @Override
    public int getItemCount() {
        return recibosArrayList.size();
    }

    public  class reciboViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre_cliente, tvEmisor, tvCantidad_items, tvTotal;

        public reciboViewHolder(@NonNull View reciboViewHolder) {
            super(reciboViewHolder);

            tvNombre_cliente = reciboViewHolder.findViewById(R.id.tvNombre_cliente);
            tvEmisor = reciboViewHolder.findViewById(R.id.tvRazon_emisor);
            tvCantidad_items = reciboViewHolder.findViewById(R.id.tvCantidad_items);
            tvTotal = reciboViewHolder.findViewById(R.id.tvMonto_total);
        }
    }
}
