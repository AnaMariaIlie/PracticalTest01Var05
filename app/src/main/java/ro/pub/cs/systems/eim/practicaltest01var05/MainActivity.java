package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01var05.service.BReceiver;
import ro.pub.cs.systems.eim.practicaltest01var05.service.PracticalTest01Var05Service;

public class MainActivity extends AppCompatActivity {

    Button play;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    int[] options = {0,1,2,3};
    int scor;

    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);

        editText1 = findViewById(R.id.edit_text1);
        editText2 = findViewById(R.id.edit_text2);
        editText3 = findViewById(R.id.edit_text3);

        play.setOnClickListener(buttonClickListener);
        intentFilter.addAction("action.victory");
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

       //String t1 = editText1.getText().toString();
       // String t2 = editText2.getText().toString();
       // String t3 = editText3.getText().toString();
       String t1 ="", t2="", t3="";
       Random rand = new Random();

        @Override
        public void onClick(View view) {


            switch(view.getId()) {
                case R.id.play:
                    int randomNum = rand.nextInt(3);


                    if(checkBox1.isChecked()) {
                        if (!editText1.getText().toString().equals("")) {
                            t1 = editText1.getText().toString();
                        }
                    } else {
                        t1 = String.valueOf(rand.nextInt(3));
                    }

                    if(checkBox2.isChecked()) {
                        if (!editText2.getText().toString().equals("")) {
                            t2 = editText2.getText().toString();
                        }
                    } else {
                        t2 = String.valueOf(rand.nextInt(3));
                    }

                    if(checkBox3.isChecked()) {
                        if (!editText3.getText().toString().equals("")) {
                            t3 = editText3.getText().toString();
                        }
                    } else {
                        t3 = String.valueOf(rand.nextInt(3));
                    }

                    Toast.makeText(getApplicationContext(), t1 + " " + t2 + " " +t3, Toast.LENGTH_LONG).show();


                    Intent i = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                    i.putExtra("text1", editText1.getText().toString());
                    i.putExtra("text2", editText2.getText().toString());
                    i.putExtra("text3", editText3.getText().toString());
                    i.putExtra("scor", String.valueOf(scor));
                    i.putExtra("bool1", checkBox1.isChecked());
                    i.putExtra("bool2", checkBox2.isChecked());
                    i.putExtra("bool3", checkBox3.isChecked());

                    startActivityForResult(i, 2019);
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 2019) {
            scor = scor + resultCode;
            Toast.makeText(this, "The activity returned with score cumulat " + String.valueOf(resultCode), Toast.LENGTH_LONG).show();
        }

        if (scor >= 200) {

            Intent ii = new Intent(this,PracticalTest01Var05Service.class);
            ii.putExtra("scor", scor);
            this.startService(ii);


        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }

    private BReceiver messageBroadcastReceiver = new BReceiver();

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
