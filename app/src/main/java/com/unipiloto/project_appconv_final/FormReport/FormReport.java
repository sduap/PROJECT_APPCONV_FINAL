package com.unipiloto.project_appconv_final.FormReport;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unipiloto.project_appconv_final.DataBase.DataBaseHelper;
import com.unipiloto.project_appconv_final.DataBase.Utilidades;
import com.unipiloto.project_appconv_final.R;

public class FormReport extends AppCompatActivity {

    EditText addressEditText, dateEditText, timeEditText, descriptionEditText;
    int selectAccident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addressEditText = findViewById(R.id.addressEditText);
        dateEditText = findViewById(R.id.dateEditText);
        timeEditText = findViewById(R.id.timeEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);

    }

    public void addReport(View view) {
        DataBaseHelper con = new DataBaseHelper(this, "GUT_DB.db", null, 1);

        SQLiteDatabase db = con.getWritableDatabase();

        Integer valueAccident = new Integer(selectAccident);

        if (validarCampos() == true) {
            ContentValues values = new ContentValues();

            values.put(Utilidades.ACCIDENT_ADDRESS, addressEditText.getText().toString());
            values.put(Utilidades.ACCIDENT_DATE, dateEditText.getText().toString());
            values.put(Utilidades.ACCIDENT_TIME, timeEditText.getText().toString());
            values.put(Utilidades.ACCIDENT_DESCRIPTION, descriptionEditText.getText().toString());

            String accident = null;

            if (valueAccident == 0) {
                accident = "Colisión";
            } else if (valueAccident == 1) {
                accident = "Atropello a peatón";
            } else if (valueAccident == 2) {
                accident = "Salida de vía";
            }

            values.put(Utilidades.ACCIDENT_CATEGORY, accident);

            Long res = db.insert(Utilidades.TABLE_ACCIDENTS, null, values);
            if (res == -1)
                Toast.makeText(this, "Reporte NO agregado", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(this, "Reporte agregado satisfactoriamente", Toast.LENGTH_LONG).show();
                addressEditText.setText("");
                dateEditText.setText("");
                timeEditText.setText("");
                descriptionEditText.setText("");
            }
        }else
            Toast.makeText(this, "Falta diligenciar campos", Toast.LENGTH_LONG).show();
    }

    public void radioClickedAccident(View view) {
        boolean check = ((RadioButton) view).isChecked();

        if (view.getId() == R.id.frontalCollisionRadioButton && check) {
            selectAccident = 0;
        } else if (view.getId() == R.id.pedestrianHitRadioButton && check) {
            selectAccident = 1;
        } else if (view.getId() == R.id.roadExitRadioButton && check) {
            selectAccident = 2;
        }
    }

    private boolean validarCampos() {
        String address = addressEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (address.isEmpty() || description.isEmpty() || date.isEmpty() || time.isEmpty() || description.isEmpty())
            return false;
        else
            return true;
    }

}