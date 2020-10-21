package com.example.b2parcial;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainFemeninasMayores extends Activity {
    private ArrayList<String> lista = new ArrayList<String>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_femeninas_mayores);

        lv = (ListView) findViewById(R.id.lv);

        AdminBD admin = new AdminBD(this, "db_b2parcial", null, 1);
        SQLiteDatabase db_parcial2 = admin.getReadableDatabase();
        Cursor c = db_parcial2.rawQuery("SELECT * FROM usuarios WHERE UPPER(sexo)=UPPER('F') AND edad>=18", null);

        if (c.moveToFirst()) {
            do {

                lista.add("Numero Documento: | " + c.getString(0) + " |\n"
                        + "Nombres: | " + c.getString(1) + " |\n"
                        + "Apellidos: | " + c.getString(2) + " |\n"
                        + "Edad: | " + c.getString(3) + " |\n"
                        + "Sexo: | " + c.getString(4) + " |\n"
                        + "Correo: | " + c.getString(5) + " |");

            } while (c.moveToNext());

            db_parcial2.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
            lv.setAdapter(adapter);

        }
    }

    public void atras(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
