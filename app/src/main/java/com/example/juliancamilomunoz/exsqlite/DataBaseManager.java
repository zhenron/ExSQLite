package com.example.juliancamilomunoz.exsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Xingke on 29/05/2015.
 */
public class DataBaseManager {

    public static final String TABLE_NAME = "contactos";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";
    public static final String CREATE_TABLE = " create table "
            + TABLE_NAME + " (" + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_PHONE + " text);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        //Generar el constructor de DBM
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertar(String nombre, String telefono) {
        db.insert(TABLE_NAME, null, generarContentValues(nombre, telefono));
    }

    public void modificar_telefono(String nombre, String nuevoTelefono) {
        db.update(TABLE_NAME, generarContentValues(nombre, nuevoTelefono), CN_NAME + "=?", new String[]{nombre});
    }

    public ContentValues generarContentValues(String nombre, String Telefono) {
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME, nombre);
        valores.put(CN_PHONE, Telefono);
        return valores;
    }

    public void eliminar_id(String _id){
        db.delete(TABLE_NAME,CN_ID+"=?", new String[] {_id});
    }

    public void eliminar_nombre(String nombre){
        db.delete(TABLE_NAME,CN_NAME+"=?", new String[] {nombre});
    }

    public Cursor cargarCursor(){
        //query(Table,columns,String selection, String selection args,String Group BY,String Having,String OrderBy)
        String [] columnas= new String[] {CN_ID, CN_NAME, CN_PHONE};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

    public Cursor buscarContacto(String nombre){
        String[] columnas = new String[] {CN_ID,CN_NAME,CN_PHONE};
        return db.query(TABLE_NAME,columnas,CN_NAME + "=?", new String[]
                {nombre},null,null,null);
    }



}

