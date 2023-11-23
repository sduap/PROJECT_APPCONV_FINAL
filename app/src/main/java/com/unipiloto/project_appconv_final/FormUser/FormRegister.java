package com.unipiloto.project_appconv_final.FormUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.unipiloto.project_appconv_final.DataBase.DataBaseHelper;
import com.unipiloto.project_appconv_final.DataBase.Utilidades;
import com.unipiloto.project_appconv_final.Home.Home_Peaton;
import com.unipiloto.project_appconv_final.R;

public class FormRegister extends AppCompatActivity {

    EditText editName, editUserName, editAge, editEmail, editPassword, editRepass;
    int selectGender, selectRol;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);

        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FormLogin.class);
            startActivity(intent);
            finish();
        });

        DataBaseHelper con = new DataBaseHelper(this, "GUT_DB.db",null,1);

        editName = findViewById(R.id.name);
        editUserName = findViewById(R.id.username);
        editAge = findViewById(R.id.age);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        editRepass = findViewById(R.id.conf_password);

    }

    public void register(View view) {
        DataBaseHelper con = new DataBaseHelper(this, "GUT_DB.db", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();

        Integer valueGender = new Integer(selectGender);
        Integer valueRol = new Integer(selectRol);

        if (validarCampos() == true) {
            if (validarPass() == true) {

                ContentValues values = new ContentValues();
                values.put(Utilidades.FULL_NAME, editName.getText().toString());
                values.put(Utilidades.BIRTH_DATE, editAge.getText().toString());
                values.put(Utilidades.EMAIL, editEmail.getText().toString());
                values.put(Utilidades.PASSWORD, editPassword.getText().toString());
                values.put(Utilidades.USERNAME, editPassword.getText().toString());

                String gender = null;
                String rol = null;

                if (valueGender == 0) {
                    gender = "Hombre";
                } else if (valueGender == 1) {
                    gender = "Mujer";
                } else if (valueGender == 2) {
                    gender = "No-binario";
                }

                if (valueRol == 0) {
                    rol = "Peaton";
                } else if (valueRol == 1) {
                    rol = "Funcionario";
                }

                values.put(Utilidades.GENDER, gender);
                values.put(Utilidades.ROLE, rol);

                if (validarEdad() == true) {
                    if (checkUser(editEmail.getText().toString()) == true) {
                        long res = db.insert(Utilidades.TABLE_USER, null, values);
                        if (res == -1)
                            Toast.makeText(this, "Datos NO registrados", Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(this, "Datos Registrados con EXITO", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(this, FormLogin.class);
                            startActivity(i);
                        }
                    } else
                        Toast.makeText(this, "El correo ingresado YA está registrado", Toast.LENGTH_LONG).show();
                } else if (validarEdad() == false && rol == "Peaton" || rol == "Funcionario")
                    Toast.makeText(this, "NO eres mayor de edad", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "Los campos de la CONTRASEÑA NO COINCIDEN", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Falta completar alguno de los campos", Toast.LENGTH_LONG).show();
    }

    public boolean validarCampos(){
        String name = editName.getText().toString();
        String age = editAge.getText().toString();
        String email = editEmail.getText().toString();
        String pass = editPassword.getText().toString();
        String repass = editRepass.getText().toString();
        if (name.isEmpty() || age.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty())
            return false;
        else
            return true;
    }

    public boolean validarPass(){
        String pass = editPassword.getText().toString();
        String repass = editRepass.getText().toString();
        if (pass.equals(repass))
            return true;
        else
            return false;
    }

    public boolean validarEdad(){
        String age = editAge.getText().toString();
        int ageI = Integer.parseInt(age);
        if (ageI >= 18)
            return true;
        else
            return false;
    }


    public void radioClickedGender(View view) {
        boolean check = ((RadioButton) view).isChecked();

        if (view.getId() == R.id.radioMale && check) {
            selectGender = 0;
        } else if (view.getId() == R.id.radioFemale && check) {
            selectGender = 1;
        } else if (view.getId() == R.id.radioNonBinary && check) {
            selectGender = 2;
        }
    }

    public void radioClickedRol(View view) {
        boolean check = ((RadioButton) view).isChecked();

        if (view.getId() == R.id.radioPeaton && check) {
            selectRol = 0;
        } else if (view.getId() == R.id.radioFuncionario && check) {
            selectRol = 1;
        }
    }

//    public void radioClickedGender(View view) {
//        boolean check = ((RadioButton) view).isChecked();
//        switch (view.getId()){
//            case R.id.radioMale:
//                if (check)
//                    selectGender = 0;
//                    handleOption1();
//                break;
//            case R.id.radioFemale:
//                if (check)
//                    //selectGender = 1;
//                    handleOption2();
//                break;
//            case R.id.radioNonBinary:
//                if (check)
//                    //selectGender = 2;
//                    handleOption3();
//                break;
//        }
//    }

//    public void radioClickedRol(View view) {
//        boolean check = ((RadioButton) view).isChecked();
//        switch (view.getId()){
//            case R.id.radioPeaton:
//                if (check)
//                    selectRol = 0;
//                break;
//            case R.id.radioFuncionario:
//                if (check)
//                    selectRol = 1;
//                break;
//        }
//    }

    public boolean checkUser(String email){
        DataBaseHelper con = new DataBaseHelper(this, "GUT_DB.db", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_USER + " WHERE " + Utilidades.EMAIL + " = ?", new String[] {email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }
}