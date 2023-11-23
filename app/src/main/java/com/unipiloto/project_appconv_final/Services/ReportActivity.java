package com.unipiloto.project_appconv_final.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.unipiloto.project_appconv_final.R;

public class ReportActivity extends AppCompatActivity {

    public static final String EXTRA_REPORTID="reportId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int reportId=(Integer) getIntent().getExtras().get(EXTRA_REPORTID);
        Report report=Report.reportes[reportId];

        TextView reporte_name=findViewById(R.id.reporte_name);
        reporte_name.setText(report.getName());

        TextView reporte_description=findViewById(R.id.reporte_description);
        reporte_description.setText(report.getDescription());

        ImageView photo=findViewById(R.id.photo);
        photo.setImageResource(report.getImageResourceId());
        photo.setContentDescription(report.getName());

    }
}