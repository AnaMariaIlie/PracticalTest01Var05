package ro.pub.cs.systems.eim.practicaltest01var05.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "This is my Toast message!" + intent.getStringExtra("data"),
                Toast.LENGTH_LONG).show();
    }
}
