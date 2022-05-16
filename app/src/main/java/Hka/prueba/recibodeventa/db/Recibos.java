package Hka.prueba.recibodeventa.db;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import Hka.prueba.recibodeventa.entity.Items;

public class Recibos extends DbRecibo{

    Context context;

    public Recibos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarRecibo(String nombre_cliente, String cedula_cliente, String razon_emisor, String rif_emisor, int cantidadItems, String total ){
        long id = 0;

        try{
            DbRecibo dbRecibo = new DbRecibo(context);
            SQLiteDatabase db = dbRecibo.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("nombre_cliente", nombre_cliente);
            contentValues.put("cedula_cliente", cedula_cliente);
            contentValues.put("razon_emisor", razon_emisor);
            contentValues.put("nrif_emisor", rif_emisor);
            contentValues.put("cantidad_items", cantidadItems);
            contentValues.put("total", total);

            id = db.insert(TABLE_RECIBO, null, contentValues);

        }catch (Exception e){
            e.toString();
        }
        return id;
    }
    public long insertarItem(String codigo, String descripcion, double precio, int cantidad, String subtotal ){
        long id = 0;

        try{
            DbRecibo dbRecibo = new DbRecibo(context);
            SQLiteDatabase db = dbRecibo.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("codigo_producto", codigo);
            contentValues.put("descripcion_producto", descripcion);
            contentValues.put("precio_producto", precio);
            contentValues.put("cantidad_producto", cantidad);
            contentValues.put("subtotal", subtotal);

            id = db.insert(TABLE_ITEM, null, contentValues);

        }catch (Exception e){
            e.toString();
        }
        return id;
    }

    public ArrayList<Items> getItems(){
        DbRecibo dbRecibo = new DbRecibo(context);
        SQLiteDatabase db = dbRecibo.getWritableDatabase();

        ArrayList<Items> items = new ArrayList<>();
        Items items1 = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_ITEM, null);

        if (cursor.moveToFirst()){
            do {
                items1 = new Items();
                items1.setId(cursor.getInt(0));
                items1.setCodigo(cursor.getString(1));
                items1.setDescripcion(cursor.getString(2));
                items1.setPrecio(cursor.getString(3));
                items1.setCantidad(cursor.getString(4));
                items1.setSubtotal(cursor.getString(5));
                items.add(items1);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  items;
    }

    public ArrayList<Hka.prueba.recibodeventa.entity.Recibos> getRecibos(){
        DbRecibo dbRecibo = new DbRecibo(context);
        SQLiteDatabase db = dbRecibo.getWritableDatabase();

        ArrayList<Hka.prueba.recibodeventa.entity.Recibos> recibosArrayList = new ArrayList<>();
        Hka.prueba.recibodeventa.entity.Recibos recibos = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT nombre_cliente, razon_emisor, cantidad_items, total FROM " + TABLE_RECIBO, null);

        if (cursor.moveToFirst()){
            do {
                recibos = new Hka.prueba.recibodeventa.entity.Recibos();
                recibos.setNomnbre_cliente(cursor.getString(0));
                recibos.setRazon_emisor(cursor.getString(1));
                recibos.setTotal_items(cursor.getString(2));
                recibos.setTotal_recibo(cursor.getString(3));
                recibosArrayList.add(recibos);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  recibosArrayList;
    }

    public long deleteItems(){
        long id = 0;

        try{
            DbRecibo dbRecibo = new DbRecibo(context);
            SQLiteDatabase db = dbRecibo.getWritableDatabase();

            db.execSQL("delete from "+ TABLE_ITEM);
            id = 1;

        }catch (Exception e){
            e.toString();
        }
        return id;
    }

    public long deleteRecibos(){
        long id = 0;

        try{
            DbRecibo dbRecibo = new DbRecibo(context);
            SQLiteDatabase db = dbRecibo.getWritableDatabase();

            db.execSQL("delete from "+ TABLE_RECIBO);
            id = 1;

        }catch (Exception e){
            e.toString();
        }
        return id;
    }

}
