package com.rsaaperez.ral.intercambiodatos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity2 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_my);

        final Intent intento = new Intent(MyActivity2.this, MyActivity.class);

        final Agenda contacto = (Agenda) getIntent().getSerializableExtra("id2");
        final EditText eNombre = (EditText) findViewById(R.id.eNombre);
        final EditText eTelefono = (EditText) findViewById(R.id.eTelefono);
        Button bEditar = (Button) findViewById(R.id.bEEditar);

        eNombre.setText(contacto.getNombre());
        eTelefono.setText(String.valueOf(contacto.getTelefono()));

        bEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //comprobar si existe nombre
                if ("".equalsIgnoreCase(eNombre.getText().toString().trim()) || "".equalsIgnoreCase(eTelefono.getText().toString().trim())) {
                    //mostrar toast
                    showToast();
                    return;
                } else {
                    Agenda modificado = new Agenda(eNombre.getText().toString(),Integer.parseInt(eTelefono.getText().toString()));
                    intento.putExtra("id3", modificado);
                    intento.putExtra("id4",contacto);
                    setResult(RESULT_OK, intento);
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);
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
}
