package com.rsaaperez.ral.intercambiodatos;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListaActivity extends ListActivity {

    ArrayList<Agenda> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        contactos = (ArrayList<Agenda>) getIntent().getSerializableExtra("id1");
        setListAdapter(new ArrayAdapter<Agenda>(this,android.R.layout.simple_list_item_1, contactos));
    }
    public void onListItemClick(ListView parent,View v,int posicion,long id){
        Intent intento = new Intent(ListaActivity.this, MyActivity2.class);
        intento.putExtra("id2", new Agenda (contactos.get(posicion).getNombre(),contactos.get(posicion).getTelefono()));
        intento.putExtra("id3", contactos);
        startActivityForResult(intento, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Agenda modificado = (Agenda) data.getSerializableExtra("id4");
            Agenda contacto = (Agenda) data.getSerializableExtra("id5");
            Intent intento = new Intent(ListaActivity.this, MyActivity.class);
            intento.putExtra("id7", contacto);
            intento.putExtra("id6",modificado);
            setResult(RESULT_OK, intento);
            finish();
        }
    }
}
