package Hka.prueba.recibodeventa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import Hka.prueba.recibodeventa.adapter.ItemAdapter;
import Hka.prueba.recibodeventa.adapter.ReciboAdapter;
import Hka.prueba.recibodeventa.db.Recibos;
import Hka.prueba.recibodeventa.entity.Items;

public class RecibodeVenta extends AppCompatActivity {

    RecyclerView reciboItem;
    ArrayList<Items> itemsArrayList;
    Recibos recibos = new Recibos(RecibodeVenta.this);
    TextView tvEmisor, tvNrifEmisor, tvNombreCliente, tvCedulaCliente, tvTotalVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibode_venta);
        setTitle(getString(R.string.activity_recibo));

        //Asignaciones de variables
        tvEmisor = findViewById(R.id.tvEmisor);
        tvNrifEmisor = findViewById(R.id.tvNrifEmisor);
        tvNombreCliente = findViewById(R.id.tvNombreCliente);
        tvCedulaCliente = findViewById(R.id.tvCedulaCliente);
        tvTotalVenta = findViewById(R.id.tvtotalReciboVenta);

        //Recibo los datos de la activity anterior *NuevoRecibo*
        String emisor = getIntent().getExtras().getString("emisor");
        String nrif = getIntent().getExtras().getString("nrif");
        String nombre = getIntent().getExtras().getString("nombre");
        String cedula = getIntent().getExtras().getString("cedula");
        String total = getIntent().getExtras().getString("total");

        //Inserto los datos en su respectivo TextView
        tvEmisor.setText(emisor);
        tvNrifEmisor.setText(nrif);
        tvNombreCliente.setText(nombre);
        tvCedulaCliente.setText(cedula);
        tvTotalVenta.setText(total);

        //RecycleView
        reciboItem = (RecyclerView) this.findViewById(R.id.rvRecibo_Venta);
        reciboItem.setLayoutManager(new LinearLayoutManager(this));

        Recibos recibos = new Recibos(RecibodeVenta.this);
        itemsArrayList = new ArrayList<>();

        //Se insertan los datos al RecycleView
        ItemAdapter itemAdapter = new ItemAdapter(recibos.getItems());
        reciboItem.setAdapter(itemAdapter);

        System.out.println("LISTA DE ITEMS DE RECIBO INSERTADOS");
    }

    //OnBackPressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Al salir de esta activity se eliminar los Items del último recibo
        recibos.deleteItems();
        System.out.println("ITEMS ELIMINADOS");
    }
}