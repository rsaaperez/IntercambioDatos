package com.rsaaperez.ral.intercambiodatos;

import android.app.Activity;
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


public class ListaActivity extends Activity {

    ArrayList<Agenda> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        final Intent intento = new Intent(ListaActivity.this, MyActivity2.class);
        contactos = (ArrayList<Agenda>) getIntent().getSerializableExtra("id1");

        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new ArrayAdapter<Agenda>(this, android.R.layout.simple_list_item_1, contactos));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intento.putExtra("id2", new Agenda (contactos.get(i).getNombre(),contactos.get(i).getTelefono()));
                intento.putExtra("id3", contactos);
                startActivityForResult(intento, 1);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
