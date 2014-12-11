package com.rsaaperez.ral.intercambiodatos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class BorrarActivity extends Activity {

    private ArrayList<Agenda> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
        Button bSi = (Button) findViewById(R.id.bSi);
        Button bNo = (Button) findViewById(R.id.bNo);
        bSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intento = new Intent(BorrarActivity.this, ListaActivity.class);
                    Agenda contacto = (Agenda) getIntent().getSerializableExtra("idb");
                    intento.putExtra("idb1", contacto);
                    setResult(2, intento);
                    finish();
                }
        });
        bNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
