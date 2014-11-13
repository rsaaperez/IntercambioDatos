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


public class MyActivity extends Activity {

    public ArrayList<Agenda> agenda = new ArrayList<Agenda>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final Intent intento = new Intent(MyActivity.this, MyActivity2.class);
        Button bAnhadir = (Button) findViewById(R.id.bAnhadir);
        Button bEditar = (Button) findViewById(R.id.bEditar);

        bAnhadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eNombre = (EditText) findViewById(R.id.eNombre);
                EditText eTelefono = (EditText) findViewById(R.id.eTelefono);
                // comprobar si existe nombre
                if ("".equals(eNombre.getText().toString()) || "".equals(eTelefono.getText().toString())) {
                    //mostrar toast
                    showToast();
                }
                agenda.add(new Agenda(eNombre.getText().toString(), Integer.parseInt(eTelefono.getText().toString())));
                eNombre.setText("");
                eTelefono.setText("");
            }
        });
        bEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eENombre = (EditText) findViewById(R.id.eENombre);
                // comprobar si existe nombre
                if ("".equals(eENombre.getText().toString())) {
                    //mostrar toast
                    showToast();
                }
                Agenda contacto = new Agenda();
                for (int i = 0; i < agenda.size(); i++) {
                    if (agenda.get(i).getNombre().equalsIgnoreCase(eENombre.getText().toString())) {
                        contacto = new Agenda(agenda.get(i).getNombre(), agenda.get(i).getTelefono());
                        intento.putExtra("id1", contacto);
                        startActivityForResult(intento, 1);
                    } else {
                        showMal();
                    }
                }

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

    protected void showMal() {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.mal);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // cogemos el valor devuelto por la otra actividad
            EditText eNombre = (EditText) findViewById(R.id.eNombre);
            EditText eTelefono = (EditText) findViewById(R.id.eTelefono);
            EditText eENombre = (EditText) findViewById(R.id.eENombre);
            eNombre.setText("");
            eTelefono.setText("");
            eENombre.setText("");
            Agenda contacto = (Agenda) data.getSerializableExtra("id2");
            agenda.add(contacto);
            // enseñamos al usuario el resultado
            Context context = getApplicationContext();
            CharSequence text = "Nombre: " + contacto.getNombre() + " Telefono: " + String.valueOf(contacto.getTelefono());
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }
}
