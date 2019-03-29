package ro.pub.cs.systems.eim.practicaltest01var05.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import ro.pub.cs.systems.eim.practicaltest01var05.ProcessingThread;

public class PracticalTest01Var05Service extends Service {


    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Service started");
        int scor = intent.getIntExtra("scor", -1);
        processingThread = new ProcessingThread(this, scor);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
