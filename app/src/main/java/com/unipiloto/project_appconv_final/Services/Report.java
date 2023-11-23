package com.unipiloto.project_appconv_final.Services;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.unipiloto.project_appconv_final.FormReport.FormReport;
import com.unipiloto.project_appconv_final.R;

public class Report extends AppCompatActivity {

    //Atributos
    private String name;
    private String description;
    private int imageResourceId;

    // Declarar un arrreglo de los reportes disponibles
    public static final Report[] reportes = {
            new Report("Colisión", "Es un tipo de accidente donde dos o más vehículos chocan entre sí." +
                    "Puede ser frontal, lateral o desde atrás, y generalmente ocurre en cruces o intersecciones.", R.drawable.colision),
            new Report("Atropello a peatón", "Se refiere a cuando un vehículo impacta a una persona que va caminando." +
                    "Estos accidentes suelen ser graves debido a la falta de protección del peatón y " +
                    "pueden ocurrir en cruces peatonales, aceras o cualquier parte de la vía.",R.drawable.peaton_atropellado),
            new Report("Salida de vía", "Ocurre cuando un vehículo se sale del camino " +
                    "o carretera y termina en una cuneta, barranco, o colisionando contra algún objeto fijo " +
                    "como un árbol o poste. Generalmente, es causado por exceso de velocidad, " +
                    "condiciones adversas del camino o distracciones del conductor.",R.drawable.salida_de_via)
    };

    //Constructor con los atributos
    public Report(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    //Metodos get y set

    public String getName() {
        return name;}
    public void setName(String name) {
        this.name = name;}
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public int getImageResourceId() {
        return imageResourceId;
    }
    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
    @Override
    public String toString() {
        return name;
    }

}
