package com.nik.androidtest.sparkletext;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class MainActivity extends AppCompatActivity {

    // private HTextView mSparkleTextView;
    private CountDownTimer mCountDownTimer;
    private HTextView mTextCountdown;
    private boolean isCounterOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSparkleText();

        Button buttonStartCountdown = (Button) findViewById(R.id.button_start_countdown);
        buttonStartCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCoutDouwn(10000);
            }
        });

        Button buttonStopCountdown = (Button) findViewById(R.id.button_stop_countdown);
        buttonStopCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopCoutDouwn("Time Extended");
            }
        });
    }

    /*
    private void SparkleText() {
        final ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        mSparkleTextView = (HTextView) new HTextView(this);
        rootView.addView(mSparkleTextView);
        mSparkleTextView.setTextColor(Color.WHITE);
        mSparkleTextView.setBackgroundColor(Color.BLACK);
        mSparkleTextView.setTypeface(null);
        mSparkleTextView.setAnimateType(HTextViewType.SPARKLE);
        float px = 24 * MainActivity.this.getResources().getDisplayMetrics().scaledDensity;
        mSparkleTextView.setTextSize(px);
        mSparkleTextView.animateText("new simple string"); // animate
        mSparkleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Status", "OnClick Called");
                mSparkleTextView.animateText("");
                mSparkleTextView.animateText("new simple string");
            }
        });
    }
    */

    private void startCoutDouwn(int startTime) {
        if (!isCounterOn) {
            isCounterOn = true;
            mCountDownTimer = new CountDownTimer(startTime+999, 1000) {
                public void onTick(long millisUntilFinished) {
                    // if(millisUntilFinished / 1000<0)
                    mTextCountdown.animateText("" + millisUntilFinished / 1000);
                    Log.d("Time", "" + millisUntilFinished);
                    if (millisUntilFinished / 1000 == 1) {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (isCounterOn) {
                                    mTextCountdown.animateText("Time Over");
                                    isCounterOn = false;
                                }
                            }
                        }, 1000);
                    }
                }

                public void onFinish() {
                    // mTextCountdown.animateText("Time Over");
                }
            }.start();
        } else {
            mCountDownTimer.cancel();
            isCounterOn = false;
            startCoutDouwn(startTime);
        }
    }

    private void stopCoutDouwn(String stopMessage) {
        if (isCounterOn) {
            isCounterOn = false;
            mCountDownTimer.cancel();
            mTextCountdown.animateText(stopMessage);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mTextCountdown.animateText("");
                }
            }, 1000);
        }
    }

    private void addSparkleText() {
        final ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        mTextCountdown = new HTextView(this);
        mTextCountdown.setTextColor(Color.rgb(255, 165, 0));
        mTextCountdown.setBackgroundColor(Color.TRANSPARENT);
        mTextCountdown.setTypeface(null);
        mTextCountdown.setAnimateType(HTextViewType.SPARKLE);
        mTextCountdown.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mTextCountdown.setLayoutParams(layoutParams);
        mTextCountdown.setGravity(Gravity.CENTER);
        rootView.addView(mTextCountdown);
    }

    /*
        textCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Status", "OnClick Called");
                textCountdown.animateText("");
                textCountdown.animateText("new simple string");
            }
        });
        */
}
