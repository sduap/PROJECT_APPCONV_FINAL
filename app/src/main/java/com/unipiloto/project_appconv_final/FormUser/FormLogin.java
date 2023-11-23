package com.unipiloto.project_appconv_final.FormUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unipiloto.project_appconv_final.DataBase.DataBaseHelper;
import com.unipiloto.project_appconv_final.DataBase.Utilidades;
import com.unipiloto.project_appconv_final.Home.Home_Funcionario;
import com.unipiloto.project_appconv_final.Home.Home_Peaton;
import com.unipiloto.project_appconv_final.R;

public class FormLogin extends AppCompatActivity {
    EditText editEmail, editPassword;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        textView = findViewById(R.id.registerNow);
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FormRegister.class);
            startActivity(intent);
            finish();
        });

        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);

    }

    public void login(View view) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (validar() == true) {
            boolean checkuser = checkUser(email);
            if (checkuser == false) {
                boolean checkpass = checkPass(password);
                if (checkpass == false) {
                    String chekrol = checkRol(email);
                    if (chekrol.contentEquals("Peaton")) {
                        Toast.makeText(this, "Bienvenido Peatón", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(this, Home_Peaton.class);
                        startActivity(i);
                    } else if (chekrol.contentEquals("Funcionario")) {
                        Toast.makeText(this, "Bienvenido Funcionario", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, Home_Funcionario.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(this, "La contraseña es INCORRECTA", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(this, "El usuario NO existe", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }
    }

    public void register (View view){
        Intent i = new Intent(this, FormRegister.class);
        startActivity(i);
    }

    public boolean validar () {
        String email = editEmail.getText().toString();
        String pass = editPassword.getText().toString();

        if (email.isEmpty() || pass.isEmpty())
            return false;
        else
            return true;
    }

//    public void show (String title, String message){
//        AlertDialog.Builder build = new AlertDialog.Builder(this);
//        build.setCancelable(true);
//        build.setTitle(title);
//        build.setMessage(message);
//        build.show();
//    }

    // CHEQUEOS
    public boolean checkUser (String email){
        DataBaseHelper con = new DataBaseHelper(this, "GUT_DB.db", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_USER + " WHERE " + Utilidades.EMAIL + " = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public boolean checkPass (String password){
        DataBaseHelper con = new DataBaseHelper(this, "GUT_DB.db", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_USER + " WHERE " + Utilidades.PASSWORD + " = ?", new String[]{password});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    private String checkRol (String email){
        DataBaseHelper con = new DataBaseHelper(getApplicationContext(), "GUT_DB.db", null, 1);
        SQLiteDatabase db = con.getReadableDatabase();

        String[] param = {email};
        String[] field = {Utilidades.ROLE};

        Cursor cursor = db.query(Utilidades.TABLE_USER, field, Utilidades.EMAIL + " = ?", param, null, null, null);
        cursor.moveToFirst();

        String res = cursor.getString(0);
        cursor.close();

        return res;
    }

}