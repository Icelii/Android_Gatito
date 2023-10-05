package com.example.android_gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 1;
    Button[] botones = new Button[9];
    Button ReiniciarPartida;
    TextView MarcadorPlayer1, MarcadorPlayer2;
    int puntosjugador1, puntosjugador2, rountcount;
    boolean JugadorActivo, winpl2 = false;
    String Turno;
    int[] tablero = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] ParaGanar = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ReiniciarPartida = findViewById(R.id.reiniciarpartida);
        MarcadorPlayer1 = findViewById(R.id.contadorp1);
        MarcadorPlayer2 = findViewById(R.id.contadorp2);

        botones[0] = findViewById(R.id.btn0);
        botones[1] = findViewById(R.id.btn1);
        botones[2] = findViewById(R.id.btn2);
        botones[3] = findViewById(R.id.btn3);
        botones[4] = findViewById(R.id.btn4);
        botones[5] = findViewById(R.id.btn5);
        botones[6] = findViewById(R.id.btn6);
        botones[7] = findViewById(R.id.btn7);
        botones[8] = findViewById(R.id.btn8);

        for (int i = 0; i < botones.length; i++) {
            botones[i].setOnClickListener(this);
        }

        rountcount = 0;
        puntosjugador1 = 0;
        puntosjugador2 = 0;
        JugadorActivo = true;
        MarcadorPlayer1.setText(Integer.toString(puntosjugador1));
        MarcadorPlayer2.setText(Integer.toString(puntosjugador2));

        ReiniciarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReiniciarPartida();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String marcador1 = data.getStringExtra("Regresodatos");
            String marcador2 = data.getStringExtra("Regresodatos2");
            String IndicaTurno = data.getStringExtra("RegresarTurno");

            puntosjugador1 = Integer.parseInt(marcador1);
            puntosjugador2 = Integer.parseInt(marcador2);

            MarcadorPlayer1.setText(Integer.toString(puntosjugador1));
            MarcadorPlayer2.setText(Integer.toString(puntosjugador2));
        }
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        } else if (VerificarGanador()) {
            return;
        }

        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length()));

        if (JugadorActivo) {
            ((Button) v).setText("X");
            ReiniciarPartida.setVisibility(View.VISIBLE);
            tablero[gameStatePointer] = 0;
        } else
        {
            ((Button) v).setText("O");
            tablero[gameStatePointer] = 1;
        }
        rountcount++;

        if (VerificarGanador()) {
            String resultado = "Game Over :(";
            if (!JugadorActivo)
            {
                resultado = "El jugador 2 ha ganado!!!";
                winpl2 = true;
                puntosjugador2++;
            }
            else {
                resultado = "El jugador 1 ha ganado!!!";
                puntosjugador1++;
            }

            ReiniciarPartida.setVisibility(View.INVISIBLE);

            EnviarLosDatos(resultado);
            ActualizarMarcadores();
            JugardeNuevo();
        }
        else if (rountcount == 9) {
            EnviarLosDatos("Game Over :(");
            ReiniciarPartida.setVisibility(View.INVISIBLE);
            ReiniciarPartida();
        }
        else {
            JugadorActivo = !JugadorActivo;
        }
    }

    private boolean VerificarGanador() {
        boolean ResultadosGanador = false;

        for (int[] Paraganar : ParaGanar) {
            if (tablero[Paraganar[0]] == tablero[Paraganar[1]]
                    && tablero[Paraganar[1]] == tablero[Paraganar[2]]
                    && tablero[Paraganar[0]] != 2) {
                ResultadosGanador = true;
            }
        }

        return ResultadosGanador;
    }

    public void ActualizarMarcadores() {
        MarcadorPlayer1.setText(Integer.toString(puntosjugador1));
        MarcadorPlayer2.setText(Integer.toString(puntosjugador2));
    }

    public void ReiniciarPartida() {
        rountcount = 0;
        puntosjugador1 = 0;
        puntosjugador2 = 0;
        JugadorActivo = true;
        winpl2 = false;

        for (int i = 0; i < botones.length; i++) {
            tablero[i] = 2;
            botones[i].setText("");
        }

        ActualizarMarcadores();
        ReiniciarPartida.setVisibility(View.INVISIBLE);
    }

    public void JugardeNuevo() {
        rountcount = 0;
        JugadorActivo = true;
        winpl2 = false;

        for (int i = 0; i < botones.length; i++) {
            tablero[i] = 2;
            botones[i].setText("");
        }

        ActualizarMarcadores();
    }

    public void EnviarLosDatos(String resultado) {
        Intent ganar = new Intent(MainActivity2.this, MainActivity3.class);

        ganar.putExtra("keyResultado", resultado);
        ganar.putExtra("keyDatos1", Integer.toString(puntosjugador1));
        ganar.putExtra("keyDatos2", Integer.toString(puntosjugador2));
        ganar.putExtra("keyGanarp2", winpl2);

        startActivityForResult(ganar, REQUEST_CODE);
    }
}

