package Hka.prueba.recibodeventa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import Hka.prueba.recibodeventa.adapter.ItemAdapter;
import Hka.prueba.recibodeventa.adapter.ReciboAdapter;
import Hka.prueba.recibodeventa.db.Recibos;

public class ListaRecibo extends AppCompatActivity {

    RecyclerView listaRecibos;
    ArrayList<Recibos> recibosArrayList;
    Button deleteRecibos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recibo);
        setTitle(getString(R.string.activity_lista));

        listaRecibos = (RecyclerView) this.findViewById(R.id.rv_listaRecibos);
        listaRecibos.setLayoutManager(new LinearLayoutManager(this));

        Recibos recibos = new Recibos(ListaRecibo.this);
        recibosArrayList = new ArrayList<>();

        ReciboAdapter reciboAdapter = new ReciboAdapter(recibos.getRecibos());
        listaRecibos.setAdapter(reciboAdapter);

        //DeleteRecibos
        deleteRecibos = findViewById(R.id.deleteRecibos);

        deleteRecibos.setOnClickListener(v -> {
            //elimina todos los recibos de la DB al presionar el botón, es a elección del usuario
            recibos.deleteRecibos();
            finish();
            startActivity(getIntent());
        });

    }
}