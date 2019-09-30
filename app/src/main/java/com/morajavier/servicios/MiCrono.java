package com.morajavier.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;


import java.util.Timer;
import java.util.TimerTask;

public class MiCrono extends Service {
    private Timer t = new Timer();
    private static final long INTERVALO_ACTUALIZACION = 10; // En milisegundos
    public static MainActivity UPDATE_LISTENER;
    public static double n=0;
    private Handler h;
    public static void setUpdateListener(MainActivity sta) {
        UPDATE_LISTENER = sta;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        iniciarCrono();
        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                UPDATE_LISTENER.refreshCrono(n);
            }
        };
    }
    @Override
    public void onDestroy() {
        pararCrono();
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
    private void iniciarCrono() {
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                n += 0.01;
                h.sendEmptyMessage(0);
            }
        }, 0, INTERVALO_ACTUALIZACION);
    }
    private void pararCrono() {
        if (t != null)
            t.cancel();
    }
    /*
    public void continuaCrono(){
        double guardaTiempo = n;

        iniciarCrono();
        t.cancel();
        n = guardaTiempo;

    }
    public void pausCrono(){
        double guardaTiemoi = n;
        t.cancel();
        n = guardaTiemoi;
        h.sendEmptyMessage(0);

    }*/
}
