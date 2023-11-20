package com.movil1.ejercicio2_4.Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConexion extends SQLiteOpenHelper {
    Context context;
    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, dbname, factory, version);
    }

    public SQLiteConexion(Context context) {
        super(context, Operaciones.NameDatabase, null, Operaciones.versionDatabase);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Operaciones.CreateTableSignature);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(Operaciones.DROPTableSignature);
        onCreate(db);
    }
}
