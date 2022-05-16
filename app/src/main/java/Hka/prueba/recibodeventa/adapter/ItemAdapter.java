package Hka.prueba.recibodeventa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Hka.prueba.recibodeventa.R;
import Hka.prueba.recibodeventa.entity.Items;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.itemViewHolder> {

    ArrayList<Items> itemsArrayList;
    public ItemAdapter(ArrayList<Items> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_recibo, null, false);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

        holder.tvCodigo.setText(itemsArrayList.get(position).getCodigo());
        holder.tvPrecio.setText(itemsArrayList.get(position).getPrecio());
        holder.tvCantidad.setText(itemsArrayList.get(position).getCantidad());
        holder.tvTotal.setText(itemsArrayList.get(position).getSubtotal());
        holder.tvDescripcion.setText(itemsArrayList.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {

        TextView tvCodigo, tvDescripcion, tvPrecio, tvCantidad, tvTotal;

        public itemViewHolder(@NonNull View itemViewHolder) {
            super(itemViewHolder);

            tvCodigo = itemViewHolder.findViewById(R.id.tvCodigo_Producto);
            tvPrecio = itemViewHolder.findViewById(R.id.tvPrecio);
            tvCantidad = itemViewHolder.findViewById(R.id.tvCantidad);
            tvTotal = itemViewHolder.findViewById(R.id.tvPrecioTotal);
            tvDescripcion = itemViewHolder.findViewById(R.id.tvDescripcion);
        }

    }
}
