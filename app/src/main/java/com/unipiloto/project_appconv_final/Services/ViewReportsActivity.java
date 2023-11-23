package com.unipiloto.project_appconv_final.Services;

import static com.unipiloto.project_appconv_final.DataBase.Utilidades.TABLE_ACCIDENTS;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.unipiloto.project_appconv_final.Adapter.ListReportsAdapter;
import com.unipiloto.project_appconv_final.DataBase.DataBaseHelper;
import com.unipiloto.project_appconv_final.Home.Home_Funcionario;
import com.unipiloto.project_appconv_final.R;

import java.util.ArrayList;

public class ViewReportsActivity extends AppCompatActivity {

    RecyclerView listReports;
    ArrayList<ReportsView> reportsViewArrayList;
    public ArrayList<ReportsView> showReports(){

        DataBaseHelper dbHelper = new DataBaseHelper(this, "GUT_DB.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<ReportsView> listReports = new ArrayList<>();
        ReportsView repview = null;
        Cursor cursorReport = null;

        cursorReport = db.rawQuery("SELECT * FROM " + TABLE_ACCIDENTS,null);

        if (cursorReport.moveToFirst()){
            do {
                repview = new ReportsView();
                repview.setId(cursorReport.getInt(0));
                repview.setReportAddress(cursorReport.getString(1));
                repview.setReportDate(cursorReport.getString(2));
                repview.setReportTime(cursorReport.getString(3));
                repview.setReportCategory(cursorReport.getString(4));
                repview.setReportDescription(cursorReport.getString(5));
                repview.setReportStatus(cursorReport.getString(6));
                listReports.add(repview);
            } while (cursorReport.moveToNext());
        }

        cursorReport.close();

        return listReports;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        // BackButton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Lista de reportes
        listReports = findViewById(R.id.listReports);
        listReports.setLayoutManager(new LinearLayoutManager(this));

        reportsViewArrayList = new ArrayList<>();

        ListReportsAdapter adapter = new ListReportsAdapter(showReports());

        listReports.setAdapter(adapter);

    }

    private ShareActionProvider shareActionProvider;

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_aplicar,menu);

        MenuItem item = menu.findItem(R.id.btn_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        setShareActionIntent("¿Quieres aprobar este reporte de incidente?");

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.btn_aplicar) {
            Toast.makeText(this, "SELECCIONE UN REPORTE PARA EDITARLO", Toast.LENGTH_LONG).show();
            return true;
        } else if (itemId == android.R.id.home) {
            startActivity(new Intent(this, Home_Funcionario.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionIntent(String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

}