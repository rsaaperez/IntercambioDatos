package com.rsaaperez.ral.intercambiodatos;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    public void onListItemClick(ListView parent,View v, final int posicion,long id){
        final Dialog customDialog = new Dialog(this);
        customDialog.setTitle("Que desea?");
        //establecemos el contenido de nuestro dialog
        customDialog.setContentView(R.layout.dialogo_borrar);
        final Button bEdit = (Button) customDialog.findViewById(R.id.bEdit);
        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(ListaActivity.this, MyActivity2.class);
                intento.putExtra("id2", new Agenda (contactos.get(posicion).getNombre(),contactos.get(posicion).getTelefono()));
                startActivityForResult(intento, 1);
                customDialog.dismiss();

            }
        });
        final Button bBorr = (Button) customDialog.findViewById(R.id.bBorr);
        bBorr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(ListaActivity.this, BorrarActivity.class);
                intento.putExtra("idB", new Agenda (contactos.get(posicion).getNombre(),contactos.get(posicion).getTelefono()));
                startActivityForResult(intento, 1);
                customDialog.dismiss();

            }
        });
        customDialog.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Agenda modificado = (Agenda) data.getSerializableExtra("id3");
            Agenda contacto = (Agenda) data.getSerializableExtra("id4");
            Intent intento = new Intent(ListaActivity.this, MyActivity.class);
            intento.putExtra("id4", contacto);
            intento.putExtra("id3",modificado);
            setResult(RESULT_OK, intento);
            finish();
        } else if (resultCode == 2){
            Agenda contacto = (Agenda) data.getSerializableExtra("idb1");
            Intent intento = new Intent(ListaActivity.this, MyActivity.class);
            intento.putExtra("idb2", contacto);
            setResult(2, intento);
            finish();
        }
    }
}
