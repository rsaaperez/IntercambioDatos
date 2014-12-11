package com.rsaaperez.ral.intercambiodatos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

//Vamos a empezar el examen.
public class MyActivity extends Activity {

    public ArrayList<Agenda> agenda = new ArrayList<Agenda>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final Intent intento = new Intent(MyActivity.this, ListaActivity.class);
        Button bAnhadir = (Button) findViewById(R.id.bAnhadir);
        Button bListar = (Button) findViewById(R.id.bListar);

        bAnhadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eNombre = (EditText) findViewById(R.id.eNombre);
                EditText eTelefono = (EditText) findViewById(R.id.eTelefono);
                // comprobar si existe nombre
                if ("".equals(eNombre.getText().toString()) || "".equals(eTelefono.getText().toString())) {
                    //mostrar toast
                    showToast();
                } else {
                    agenda.add(new Agenda(eNombre.getText().toString(), Integer.parseInt(eTelefono.getText().toString())));
                    eNombre.setText("");
                    eTelefono.setText("");
                }
            }
        });
        bListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Agenda> ag = agenda;
                intento.putExtra("id1", ag);
                startActivityForResult(intento, 1);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showToast() {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.noNameMsg);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // cogemos el valor devuelto por la otra actividad
            Agenda modificado = (Agenda) data.getSerializableExtra("id3");
            Agenda contacto = (Agenda) data.getSerializableExtra("id4");
            for (int i = 0; i < agenda.size(); i++) {
                if (agenda.get(i).getNombre().equalsIgnoreCase(contacto.getNombre().toString())) {

                    agenda.get(i).setNombre(modificado.getNombre());
                    agenda.get(i).setTelefono(modificado.getTelefono());
                }
            }
            // enseñamos al usuario el resultado
            Context context = getApplicationContext();
            CharSequence text = "Nombre: " + modificado.getNombre() + " Telefono: " + String.valueOf(modificado.getTelefono());
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }
}
