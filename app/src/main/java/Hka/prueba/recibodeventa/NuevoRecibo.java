package Hka.prueba.recibodeventa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Hka.prueba.recibodeventa.adapter.ItemAdapter;
import Hka.prueba.recibodeventa.db.Recibos;
import Hka.prueba.recibodeventa.entity.Items;

public class NuevoRecibo extends AppCompatActivity {

    EditText txtCantidad, txtNombre, txtApellido, txtCedula, txtRazon, txtRIf, txtCodigo, txtDescripcion, txtPrecio;
    EditText txtSubtotal;
    Button btn_realizar, btn_total;
    Spinner itemSpinner;
    RecyclerView listaItems;
    ArrayList<Items> itemsArrayList;
    String NRIF, nombre_cliente, razon, cedula, subtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_recibo);
        setTitle(getString(R.string.activity_nuevo));

        //options Spinner Adapter
        //Declaración del Spinner
        itemSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Options_Spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter);

        //declaracones de variables asignadas a los txt del form
        txtCantidad = findViewById(R.id.txtCantidad);
        txtNombre = findViewById(R.id.txtNombre_Cliente);
        txtApellido = findViewById(R.id.txtApellido_Cliente);
        txtCedula = findViewById(R.id.txtCedula_Cliente);
        txtRazon = findViewById(R.id.txtRazon_Emisor);
        txtRIf = findViewById(R.id.txtNRIF_Emisor);
        txtCodigo = findViewById(R.id.txtCodigo_Producto);
        txtDescripcion = findViewById(R.id.txtDescripcion_Producto);
        txtPrecio = findViewById(R.id.txtPrecio_Producto);
        txtSubtotal = findViewById(R.id.txtSubtotal);

        //RecycleView
        listaItems = (RecyclerView) this.findViewById(R.id.listaItems);
        listaItems.setLayoutManager(new LinearLayoutManager(this));

        Recibos recibos = new Recibos(NuevoRecibo.this);
        itemsArrayList = new ArrayList<>();

        recibos.deleteItems();

        Items item = new Items();

        //onClick buttons
        btn_realizar = findViewById(R.id.btnrealizar);
        btn_total = findViewById(R.id.btn_Totalizar);

        //btn guardar el producto
        btn_realizar.setOnClickListener(v -> {

            try {
                if (camposvaciosProducto()){
                    Double precio = Double.parseDouble(txtPrecio.getText().toString());
                    int cantidad = Integer.parseInt(txtCantidad.getText().toString());
                    Double totalPrecioItem = precio * cantidad;

                    Recibos Item = new Recibos(NuevoRecibo.this);

                    //Insertar Item en la DB
                    long id = Item.insertarItem(
                            txtCodigo.getText().toString(),
                            txtDescripcion.getText().toString(),
                            precio,
                            cantidad,
                            String.valueOf(totalPrecioItem)
                    );
                    if (id > 0){
                        //Inserta el Item guardado en el RecycleView
                        ItemAdapter itemAdapter = new ItemAdapter(recibos.getItems());
                        listaItems.setAdapter(itemAdapter);
                        System.out.println("ITEM INSERTADO");

                        //Totaliza el subtotal
                        Double sub = Double.valueOf(txtSubtotal.getText().toString());
                        Double total = sub + totalPrecioItem;
                        txtSubtotal.setText(String.valueOf(total));

                        //Limpia los txt
                        clearItem();
                    }
                }else{
                    Toast.makeText(NuevoRecibo.this, "LLene los datos faltantes del producto", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                System.out.println(e);
            }

        });

        //btn guardar el recibo completo
        btn_total.setOnClickListener(v -> {

            try {
                if (camposvaciosRecibo()){
                    if (limiteItems()) {
                        //define variables
                        NRIF = itemSpinner.getSelectedItem().toString().concat(txtRIf.getText().toString());
                        nombre_cliente = txtNombre.getText().toString().concat(txtApellido.getText().toString());
                        razon = txtRazon.getText().toString();
                        cedula = txtCedula.getText().toString();
                        subtotal = txtSubtotal.getText().toString();

                        Recibos recibos1 = new Recibos(NuevoRecibo.this);
                        Items items = new Items();

                        ItemAdapter itemAdapter = new ItemAdapter(recibos.getItems());
                        int totalItems = itemAdapter.getItemCount();
                        System.out.println(totalItems);

                        //guarda el recibo en la DB
                        long id = recibos1.insertarRecibo(
                                nombre_cliente,
                                txtCedula.getText().toString(),
                                txtRazon.getText().toString(),
                                NRIF,
                                totalItems,
                                txtSubtotal.getText().toString()
                        );
                        if (id > 0) {
                            Toast.makeText(NuevoRecibo.this, "Recibo Guardado", Toast.LENGTH_LONG).show();
                            //limpia los campos
                            clearTotal();
                            //abre la vista del recibo de venta
                            ViewRecibodeVenta();
                        } else {
                            Toast.makeText(NuevoRecibo.this, "Producto agregado", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(NuevoRecibo.this, "El recibo solo admite entre 1 a 99 Productos", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(NuevoRecibo.this, "Llene todos los campos restantes", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                System.out.println(e);
            }
        });
    }

    private boolean camposvaciosProducto() {
        if (txtCodigo.getText().toString().equals("") || txtDescripcion.getText().toString().equals("") || txtPrecio.getText().toString().equals("") || txtCantidad.getText().toString().equals("")){
            return false;
        }
        return true;
    }
    private boolean camposvaciosRecibo() {
        if (txtNombre.getText().toString().equals("") || txtApellido.getText().toString().equals("") || txtRazon.getText().toString().equals("") || txtRIf.getText().toString().equals("") || txtCedula.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    private boolean limiteItems(){
        Recibos recibos = new Recibos(NuevoRecibo.this);
        ItemAdapter itemAdapter = new ItemAdapter(recibos.getItems());
        int totalItems = itemAdapter.getItemCount();
        if (totalItems > 0 &&  totalItems < 100){
            return true;
        }
        return false;
    }

    //abre la vista de recibo de venta y envío los datos del recibo
    public void ViewRecibodeVenta(){
        Intent intent = new Intent(NuevoRecibo.this, RecibodeVenta.class);
        intent.putExtra("emisor", razon);
        intent.putExtra("nrif", NRIF);
        intent.putExtra("nombre", nombre_cliente);
        intent.putExtra("cedula", cedula);
        intent.putExtra("total", subtotal);
        startActivity(intent);
    }

    //Vacía todos los campos
    private void clearTotal(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtRazon.setText("");
        txtRIf.setText("");
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");
    }

    //Vacía solo los de producto
    private void clearItem(){
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
    }

}