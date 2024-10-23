package com.sky.quizzgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int pressCounter =0;
    private int maxPressCounter =4;
    private String[] keys = {"R","I","B","D","X"};
    private String textAnswer = "BIRD";
    TextView txtScreen,txtQuestion,txtTitle;
    Animation smallbigforth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smallbigforth = AnimationUtils.loadAnimation(this,R.anim.smallbigforth);
        smallbigforth.setDuration(300);

        keys = shuffleArray(keys);

        for (String key : keys){
            LinearLayoutCompat LayoutPanel;
            EditText edt;
            LayoutPanel = findViewById(R.id.LayoutPanel);
            edt = findViewById(R.id.edt);
            addView((LayoutPanel), key , (edt));
        }
        maxPressCounter =4;
    }

    private String[] shuffleArray(String[] ar){
        Random rnd = new Random();
        for (int i=ar.length-1 ;i>0;i--){
            int index =rnd.nextInt(i+1);
            String a =ar[index];
            ar[index] = ar[i];
            ar[i] = a;

        }
return ar;
    }
    private void addView(LinearLayoutCompat viewParent, final String text, final EditText edt ){
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.rightMargin = 20;
        final TextView textView = new TextView(this);
        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.purple_500));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOneRegular.ttf");
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtScreen = (TextView) findViewById(R.id.txtScreen);
        txtTitle = (TextView) findViewById(R.id.txtTitle);

        txtQuestion.setTypeface(typeface);
        txtScreen.setTypeface(typeface);
        txtTitle.setTypeface(typeface);
        edt.setTypeface(typeface);
        textView.setTypeface(typeface);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pressCounter < maxPressCounter){
                    if (pressCounter == 0)
                        edt.setText("");
                        edt.setText(edt.getText().toString() + text);
                       textView.startAnimation(smallbigforth);
                        textView.animate().alpha(0).setDuration(300);
                        pressCounter++;

                        if(pressCounter == maxPressCounter)
                          doValidate();



                }
            }
        });
        viewParent.addView(textView);


    }

    private void doValidate() {
        pressCounter =0;
        EditText edt = findViewById(R.id.edt);
        LinearLayoutCompat layoutCompat = findViewById(R.id.LayoutPanel);

        if(edt.getText().toString().equals(textAnswer)){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,BossAct.class);
            startActivity(intent);
            edt.setText("");
        }else{
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            edt.setText("");
        }

        keys = shuffleArray(keys);
        layoutCompat.removeAllViews();
        for (String key : keys){
            addView(layoutCompat,key,edt);
        }
    }

}