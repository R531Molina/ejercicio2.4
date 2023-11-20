package com.movil1.ejercicio2_4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.movil1.ejercicio2_4.Config.Operaciones;
import com.movil1.ejercicio2_4.Config.SQLiteConexion;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ActivityListarFirma extends AppCompatActivity {

    SQLiteConexion conexion = new SQLiteConexion(this, Operaciones.NameDatabase, null, 1);
    Button btnAtras;
    ArrayList<Firma> listaFirmas = new ArrayList<Firma>();
    ImageView imageView;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_firma);

        SQLiteDatabase db = conexion.getWritableDatabase();
        String sql = "SELECT * FROM Signature";
        Cursor cursor = db.rawQuery(sql, new String[] {});

        while (cursor.moveToNext()){
            listaFirmas.add(new Firma(cursor.getString(0) , cursor.getBlob(1)));
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        AdaptadorFirmas adaptador = new AdaptadorFirmas(this);
        list = findViewById(R.id.lista);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                obtenerFoto(i);
            }
        });

        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void obtenerFoto( int id) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Firma lista_Firmas = null;
        listaFirmas = new ArrayList<Firma>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Operaciones.TablaSignature,null);

        while (cursor.moveToNext())
        {
            lista_Firmas = new Firma();
            lista_Firmas.setDescripcion(cursor.getString(0));
            listaFirmas.add(lista_Firmas);
        }
        cursor.close();
        Firma signature = listaFirmas.get(id);
    }


    class AdaptadorFirmas extends ArrayAdapter<Firma> {

        AppCompatActivity appCompatActivity;

        AdaptadorFirmas(AppCompatActivity context) {
            super(context, R.layout.sigle_row, listaFirmas);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.sigle_row, null);

            imageView = item.findViewById(R.id.imgFirma);

            SQLiteDatabase db = conexion.getWritableDatabase();

            String sql = "SELECT * FROM Signature";

            Cursor cursor = db.rawQuery(sql, new String[] {});
            Bitmap bitmap = null;
            TextView textView1 = item.findViewById(R.id.descripcionImagen);

            if (cursor.moveToNext()){
                textView1.setText(listaFirmas.get(position).getDescripcion());
                byte[] blob = listaFirmas.get(position).getImage();
                ByteArrayInputStream bais = new ByteArrayInputStream(blob);
                bitmap = BitmapFactory.decodeStream(bais);
                imageView.setImageBitmap(bitmap);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            db.close();

            return(item);
        }
    }
}