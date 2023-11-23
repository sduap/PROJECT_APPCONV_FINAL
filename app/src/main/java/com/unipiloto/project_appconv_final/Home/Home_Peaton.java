package com.unipiloto.project_appconv_final.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.unipiloto.project_appconv_final.FormUser.FormLogin;
import com.unipiloto.project_appconv_final.R;
import com.unipiloto.project_appconv_final.Services.MapsActivity;
import com.unipiloto.project_appconv_final.Services.Podometro;
import com.unipiloto.project_appconv_final.Services.ReportCategoryActivity;

public class Home_Peaton extends AppCompatActivity {

    Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_peaton);

        // LOGOUT
        buttonLogout = findViewById(R.id.logout);
        buttonLogout.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FormLogin.class);
            startActivity(intent);
            finish();
        });

        // LIST VIEW
        AdapterView.OnItemClickListener itemClickListener = (listView, view, position, id) -> {
            if (position == 0){
                Intent intent = new Intent(Home_Peaton.this, ReportCategoryActivity.class);
                startActivity(intent);
            } else if (position == 1) {
                Intent intent = new Intent(Home_Peaton.this, MapsActivity.class);
                startActivity(intent);
            } else if (position == 2) {
                Intent intent = new Intent(Home_Peaton.this, Podometro.class);
                startActivity(intent);
            }
        };

        ListView lista = findViewById(R.id.lista_opciones);
        lista.setOnItemClickListener(itemClickListener);

    }
}