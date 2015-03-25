package com.example.skywalker.example2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // toda tabla que se cree va a tener un primary key conformado por la palabra id mas el nombre de la tabla
    private final String table1 = "anuncios";

    //la columna 1 se crea junto con la tabla, por eso se comienza con la columna 2
    private final String table1Col2 = "fecha";
    private final String table1Col3 = "titulo";
    private final String table1Col4 = "categoria";
    private final String table1Col5 = "area";


    //En este constructor definimos el context, el nombre de la BBDD y la version de la misma
    public DatabaseHelper(Context context) {
        super(context, "Horario", null, 1);
    }

    //metodo heredado de SQLiteOpenHelper
    //Este se ejecuta cuando se crea la BBDD
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creando tabla de anuncios
        createTable(db,this.table1);

        //agregando las columnas (la col con el PK se genero junto con la tabla)
        addTableColumn(db,this.table1,this.table1Col2,"TEXT");
        addTableColumn(db,this.table1,this.table1Col3,"TEXT");
        addTableColumn(db,this.table1,this.table1Col4,"TEXT");
        addTableColumn(db,this.table1,this.table1Col5,"TEXT");

    }

    //metodo heredado de SQLiteOpenHelper
    //Este se ejecuta cuando se actualiza algun componente de la BBDD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTable(SQLiteDatabase db, String tableName){
        db.execSQL("CREATE TABLE " +tableName+"(id"+tableName+" Integer PRIMARY KEY);");
    }

    public void addTableColumn(SQLiteDatabase db, String tableName, String columnName, String typeColumn){
        db.execSQL("ALTER TABLE " +tableName+" ADD COLUMN "+columnName+" "+typeColumn+";");
    }

    public void insertTable(String tableName, String column1, String data1){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column1, data1);

        db.insert(tableName, null, values);
        db.close();
    }

    public void insertTable(String tableName, String column1, String data1,String column2, String data2,
                            String column3, String data3,String column4, String data4){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column1, data1);
        values.put(column2, data2);
        values.put(column3, data3);
        values.put(column4, data4);

        db.insert(tableName, null, values);
        db.close();
    }

    public Cursor getAll(String table){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db. rawQuery("SELECT id"+table+" as _id, *  FROM "+table+";", null);
        return c;
    }

}
