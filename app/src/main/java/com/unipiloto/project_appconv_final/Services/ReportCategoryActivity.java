package com.unipiloto.project_appconv_final.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.unipiloto.project_appconv_final.FormReport.FormReport;
import com.unipiloto.project_appconv_final.R;

public class ReportCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<Report> adaptadorLista = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Report.reportes
        );

        ListView listaReportes = findViewById(R.id.lista_categ_reportes);
        listaReportes.setAdapter(adaptadorLista);

        AdapterView.OnItemClickListener itemClickListener= (parent, view, position, id) -> {
            Intent intent = new Intent(ReportCategoryActivity.this, ReportActivity.class);
            intent.putExtra(ReportActivity.EXTRA_REPORTID, (int)id);
            startActivity(intent);
        };
        listaReportes.setOnItemClickListener(itemClickListener);

    }

    public void AddReportRedirect(View view){
        Intent intent = new Intent(this, FormReport.class);
        startActivity(intent);
    }

}