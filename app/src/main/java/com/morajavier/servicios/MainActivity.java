package com.morajavier.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button jBtn1;
    private  Button jBtn2;
    private Button jbtnPausar;
    private Button jbtnContinuar;
    private TextView jTV1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jBtn1 = findViewById(R.id.xbtn1);
        jBtn2 = findViewById(R.id.xbtn2);
        jbtnContinuar = findViewById(R.id.xbtncontinuar);
        jbtnPausar = findViewById(R.id.xbtnpausar);
        jTV1 = findViewById(R.id.tiempo);

        jBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCrono();
            }
        });

        jBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * ¿Se preguntan por que el MiCrono.n = 0?
                 * lo agregue aqui ya que si agrega al servicio esta declaracion al momento de pausar se le asigna el valor de 0 y no hace
                 * lo adecuado que hacer una pausa
                 * entonces cuando se quiera parar el cronometro primero se hace 0 n y despues se paran los servicios
                 * */

                stopCrono();
                MiCrono.n = 0.0000;
            }
        });
        //MiCrono.setUpdateListener(this);

        jbtnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //continuar el cronometro

                initCrono();
            }
        });

        jbtnPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pausar el crono
                pausCrono();
            }
        });
        MiCrono.setUpdateListener(this);

    }
    @Override
    protected void onDestroy() {
        stopCrono();
        super.onDestroy();
    }
    private void initCrono() {
        Intent in = new Intent(this, MiCrono.class);
        startService(in);
    }
    private void stopCrono() {

        Intent in = new Intent(this, MiCrono.class);
        stopService(in);
    }
    /*public void continuaCrono(){

        double guardaTiempo = MiCrono.n;
        stopCrono();
        MiCrono.n = guardaTiempo;
        initCrono();


    }*/
    public void pausCrono(){
        double guardaTiemoi = MiCrono.n;
        stopCrono();
        MiCrono.n = guardaTiemoi;


    }
    public void refreshCrono(double t) {
        jTV1.setText(String.format("%.2f", t) + " segs");
    }

}
