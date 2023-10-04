package com.example.android_gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button[] botones = new Button[9];
    Button ReiniciarPartida;
    TextView anuncio, MarcadorPlayer1, MarcadorPlayer2;

    int puntosjugador1, puntosjugador2, rountcount;

    boolean JugadorActivo;

    int[] tablero = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] ParaGanar = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ReiniciarPartida = findViewById(R.id.reiniciarpartida);
        MarcadorPlayer1 = findViewById(R.id.contadorp1);
        MarcadorPlayer2 = findViewById(R.id.contadorp2);
        anuncio = findViewById(R.id.IndicadorTurno);

        botones[0] = findViewById(R.id.btn0);
        botones[1] = findViewById(R.id.btn1);
        botones[2] = findViewById(R.id.btn2);
        botones[3] = findViewById(R.id.btn3);
        botones[4] = findViewById(R.id.btn4);
        botones[5] = findViewById(R.id.btn5);
        botones[6] = findViewById(R.id.btn6);
        botones[7] = findViewById(R.id.btn7);
        botones[8] = findViewById(R.id.btn8);

        for(int i=0; i<botones.length; i++)
        {
            botones[i].setOnClickListener(this);
        }

        rountcount = 0;
        puntosjugador1 = 0;
        puntosjugador2 = 0;
        JugadorActivo = true;
        anuncio.setText("Turno del Jugador 1");

        ReiniciarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReiniciarPartida();
            }
        });
    }

    @Override
    public void onClick(View v){
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        else if (VerificarGanador())
        {
            return;
        }

        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));

        if(JugadorActivo)
        {
            ((Button)v).setText("X");

            tablero[gameStatePointer] = 0;
            anuncio.setText("Turno del jugador 2");
        }
        else
        {
            ((Button)v).setText("O");

            tablero[gameStatePointer] = 1;
            anuncio.setText("Turno del jugador 1");
        }
        rountcount++;

        if(VerificarGanador())
        {
            if(JugadorActivo)
            {
                puntosjugador1++;
                ActualizarMarcadores();
                Toast.makeText(this, "Jugador 1 ha ganado!!!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                puntosjugador2++;
                ActualizarMarcadores();
                Toast.makeText(this, "Jugador 2 ha ganado!!!", Toast.LENGTH_SHORT).show();
            }
            ActualizarMarcadores();
            JugardeNuevo();
        }
        else if (rountcount == 9)
        {
            ReiniciarPartida();
            Toast.makeText(this, "Nadie ha ganado :(", Toast.LENGTH_SHORT).show();
        }
        else
        {
            JugadorActivo = !JugadorActivo;
        }
    }

    private boolean VerificarGanador()
    {
        boolean ResultadosGanador = false;

        for (int[] Paraganar:ParaGanar)
        {
            if (tablero[Paraganar[0]] == tablero[Paraganar[1]]
                    && tablero[Paraganar[1]] == tablero[Paraganar[2]]
                    && tablero[Paraganar[0]] != 2)
            {
                ResultadosGanador = true;
            }
        }

        return ResultadosGanador;
    }

    public void ActualizarMarcadores()
    {
        MarcadorPlayer1.setText(Integer.toString(puntosjugador1));
        MarcadorPlayer2.setText(Integer.toString(puntosjugador2));
    }

    public void ReiniciarPartida()
    {
        rountcount = 0;
        puntosjugador1 = 0;
        puntosjugador2 = 0;
        JugadorActivo = true;

        for (int i = 0; i < botones.length; i++)
        {
            tablero[i] = 2;
            botones[i].setText("");
        }

        ActualizarMarcadores();
        anuncio.setText("");
    }

    public void JugardeNuevo()
    {
        rountcount = 0;
        JugadorActivo = true;

        for (int i = 0; i < botones.length; i++)
        {
            tablero[i] = 2;
            botones[i].setText("");
        }

        ActualizarMarcadores();
        anuncio.setText("");
    }
}