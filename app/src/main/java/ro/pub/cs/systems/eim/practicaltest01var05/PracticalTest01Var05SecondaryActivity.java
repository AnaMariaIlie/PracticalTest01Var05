package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    TextView score;
    Button ok;
    int castig = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        score = findViewById(R.id.score);
        ok = findViewById(R.id.ok);

        Intent i = getIntent();
        if (i != null) {
            int t1 = Integer.valueOf(i.getStringExtra("text1"));
            int t2 = Integer.valueOf(i.getStringExtra("text2"));
            int t3 = Integer.valueOf(i.getStringExtra("text3"));
            int  scor = Integer.valueOf(i.getStringExtra("scor"));
            boolean b1 = i.getBooleanExtra("bool1", true);
            boolean b2 = i.getBooleanExtra("bool2", true);
            boolean b3 = i.getBooleanExtra("bool3", true);
            String text = "";


            if (t1 == t3 && t2 == t3 ) {
                if(b1 == true && b2 == true && b3 == true) {

                    text = "Gained" + "0";
                    castig = 0 +scor ;
                } else if (b1 == true || b2 == true || b3 == true){
                    text = "Gained" + "50";
                    castig = 50 + scor;
                } else {
                    text = "Gained" + "100";
                    castig = 100 +scor;
                }

            } else{
                text = "Gained" + "0";
                castig = 0 +scor;
            }
            System.out.println("set text");
            score.setText(text);
        }

        ok.setOnClickListener(buttonClickListener);

    }

    private Button1ClickListener buttonClickListener = new Button1ClickListener();
    private class Button1ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok:
                    setResult(castig, new Intent());
                    finish();
                    break;

            }
        }
    }

}
