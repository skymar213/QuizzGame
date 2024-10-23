package com.sky.quizzgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class BossAct extends AppCompatActivity {
    TextView txtHome,txtContinue,txtQuestion,txtScreen;
    ImageView bigboss;
    Animation smalltobig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOneRegular.ttf");
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtScreen = (TextView) findViewById(R.id.txtScreen);
        txtHome= (TextView) findViewById(R.id.txtHome);
        txtContinue= (TextView) findViewById(R.id.txtContinue);
        smalltobig = AnimationUtils.loadAnimation(this,R.anim.smalltobig);

        bigboss =(ImageView) findViewById(R.id.bigboss);
        bigboss.startAnimation(smalltobig);
        smalltobig.setDuration(300);


        txtQuestion.setTypeface(typeface);
        txtScreen.setTypeface(typeface);
        txtHome.setTypeface(typeface);
        txtContinue.setTypeface(typeface);

        txtHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BossAct.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}