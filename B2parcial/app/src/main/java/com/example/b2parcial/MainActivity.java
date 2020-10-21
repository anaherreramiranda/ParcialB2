package com.example.b2parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText documento, documento2, nombres, apellidos, edad, sexo, correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        documento = (EditText) findViewById(R.id.documento);
        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        sexo = (EditText) findViewById(R.id.sexo);
        correo = (EditText) findViewById(R.id.correo);
        documento2 = (EditText) findViewById(R.id.documento2);

    }

    public void registrarUsuario(View v) {

        AdminBD admin = new AdminBD(this, "db_b2parcial", null, 1);
        SQLiteDatabase db_parcial2 = admin.getWritableDatabase();

        /*Cursor c = db_parcial.rawQuery("SELECT documento FROM usuarios WHERE documento=" + documento.getText().toString(), null);
        if (c.moveToFirst()) {
            Toast.makeText(this, "NUMERO DE DOCUMENTO DUPLICADO", Toast.LENGTH_SHORT).show();
        } else {*/


        String document = documento.getText().toString();
        String nombre = nombres.getText().toString();
        String apellido = apellidos.getText().toString();
        String edadd = edad.getText().toString();
        String sex = sexo.getText().toString();
        String email = correo.getText().toString();

        if (!document.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !edadd.isEmpty() && !sex.isEmpty() && !email.isEmpty()) {

            ContentValues datos = new ContentValues();

            datos.put("documento", document);
            datos.put("nombres", nombre);
            datos.put("apellidos", apellido);
            datos.put("edad", edadd);
            datos.put("sexo", sex);
            datos.put("email", email);

            try {

                db_parcial2.insert("usuarios", null, datos);
                db_parcial2.close();

                documento.getText().clear();
                nombres.getText().clear();
                edad.getText().clear();
                sexo.getText().clear();
                correo.getText().clear();

            } catch (Exception e) {
                Toast.makeText(this, "ERROR BASE DE DATOS", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "INGRESE LOS DATOS CORRECTOS", Toast.LENGTH_SHORT).show();
        }


        Toast.makeText(this, "USUARIO AGREGADO", Toast.LENGTH_SHORT).show();


    }

    public void eliminarUsuario(View v) {

        AdminBD admin = new AdminBD(this, "db_b2parcial", null, 1);
        SQLiteDatabase db_parcial2 = admin.getWritableDatabase();

        int e = db_parcial2.delete("usuarios", "documento=" + documento2.getText().toString(), null);


        db_parcial2.close();


        if (e == 1) {
            nombres.getText().clear();
            edad.getText().clear();
            sexo.getText().clear();
            correo.getText().clear();
            documento.getText().clear();

            Toast.makeText(this, documento2.getText().toString() + "USUARIO ELIMINADO", Toast.LENGTH_SHORT).show();
            documento2.getText().clear();
        } else {
            Toast.makeText(this, "EL USUARIO INGRESADO NO EXISTE", Toast.LENGTH_SHORT).show();
        }
    }

    public void usuarioEspecifico(View v) {
        Intent i = new Intent(this, MainFemeninasMayores.class);
        startActivity(i);
    }

}