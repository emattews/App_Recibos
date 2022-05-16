package Hka.prueba.recibodeventa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import Hka.prueba.recibodeventa.db.DbRecibo;
import Hka.prueba.recibodeventa.db.Recibos;
import Hka.prueba.recibodeventa.entity.Items;


public class MainActivity extends AppCompatActivity {

    private boolean canExitApp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.home));

        //Creación de DB
        DbRecibo dbRecibo = new DbRecibo(MainActivity.this);
        SQLiteDatabase db = dbRecibo.getReadableDatabase();
        if (db != null){
            System.out.println("Ya existe");

            Recibos recibos = new Recibos(MainActivity.this);
            recibos.deleteItems();
        }else {
            createdb();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.menu_Nuevo) {
            ViewNuevoRecibo();
        }else {
            ViewListaRecibo();
        }
        return true;
    }

    public void ViewNuevoRecibo(){
        Recibos recibos = new Recibos(MainActivity.this);
        recibos.deleteItems();
        Intent intent = new Intent(MainActivity.this, NuevoRecibo.class);
        startActivity(intent);
    }
    public void ViewListaRecibo(){
        Intent intent = new Intent(MainActivity.this, ListaRecibo.class);
        startActivity(intent);
    }

    public void createdb(){
        DbRecibo dbRecibo = new DbRecibo(MainActivity.this);
        SQLiteDatabase db = dbRecibo.getWritableDatabase();

        if (db != null){
            Toast.makeText(this, "Open", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "x", Toast.LENGTH_LONG).show();
        }
    }

    //OnBackPressed onClick
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (!canExitApp) {
            canExitApp = true;
            Toast.makeText(this, getString(R.string.back_pressed), Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    canExitApp = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }
}