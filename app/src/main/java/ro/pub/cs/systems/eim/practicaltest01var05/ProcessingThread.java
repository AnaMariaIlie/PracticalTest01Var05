package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();
    private int scor;

    public ProcessingThread(Context context, int scor) {
        this.context = context;

        this.scor = scor;
    }

    @Override
    public void run() {
        while (isRunning) {
            sleep();
            sendMessage();

        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("action.victory");
        intent.putExtra("data",
                new Date(System.currentTimeMillis()) + " " + scor + "Victory");
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}