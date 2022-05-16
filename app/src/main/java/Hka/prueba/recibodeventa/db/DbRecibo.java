package Hka.prueba.recibodeventa.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbRecibo extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "recibo.db";
    public static final String TABLE_RECIBO = "t_recibo";
    public static final String TABLE_ITEM = "t_item";

    public DbRecibo(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + " "+ TABLE_RECIBO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_cliente TEXT," +
                "cedula_cliente TEXT," +
                "razon_emisor TEXT," +
                "nrif_emisor TEXT," +
                "cantidad_items INT," +
                "total TEXT)");

        db.execSQL("CREATE TABLE " + " "+ TABLE_ITEM + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "codigo_producto TEXT," +
                "descripcion_producto TEXT," +
                "precio_producto DOUBLE," +
                "cantidad_producto INT," +
                "subtotal TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE" + " " + TABLE_RECIBO);
        db.execSQL("DROP TABLE" + " " + TABLE_ITEM);
        onCreate(db);
    }
}
