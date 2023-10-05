package com.example.android_gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button SeguirJuego = findViewById(R.id.SeguirJugando);
        Button Reiniciar = findViewById(R.id.ReiniciarPartida2);
        TextView Anunciar = findViewById(R.id.textoo);
        TextView MarcadorFinal = findViewById(R.id.MarcadorFinal);

        Bundle DatosRecibidos = getIntent().getExtras();
        String resultado = DatosRecibidos.getString("keyResultado");
        String marcador1 = DatosRecibidos.getString("keyDatos1");
        String marcador2 = DatosRecibidos.getString("keyDatos2");
        boolean estadop2 = DatosRecibidos.getBoolean("keyGanarp2");

        Anunciar.setText(resultado);

        if (!resultado.equals("Game Over :(") && estadop2 == false)
        {
            MarcadorFinal.setText(marcador1);
        }
        else if (!resultado.equals("Game Over :(") && estadop2 == true)
        {
            MarcadorFinal.setText(marcador2);
        }
        else if (resultado.equals("Game Over :("))
        {
            MarcadorFinal.setVisibility(View.GONE);
        }

        SeguirJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Regresodatos", marcador1);
                resultIntent.putExtra("Regresodatos2", marcador2);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        Reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reincioo = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(reincioo);
            }
        });
    }
}
