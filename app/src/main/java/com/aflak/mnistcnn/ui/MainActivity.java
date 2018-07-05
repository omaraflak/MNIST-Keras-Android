package com.aflak.mnistcnn.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aflak.mnistcnn.MyApp;
import com.aflak.mnistcnn.R;
import com.aflak.mnistcnn.ml.CNN;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_drawView) DrawView drawView;
    @BindView(R.id.activity_main_status) TextView status;
    @Inject CNN cnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        MyApp.app().mlComponent().inject(this);
    }

    @OnClick(R.id.activity_main_done)
    public void onPredict(){
        float[] input = drawView.getImage(CNN.IMAGE_SIZE, CNN.IMAGE_SIZE);
        float[] output = cnn.predict(input);
        CNN.Result best = cnn.bestResult(output);

        String text = best.number + " - " + best.score*100 + "%";
        status.setText(text);

        new Handler().postDelayed(() -> {
            String statusText = "Draw number";
            status.setText(statusText);
            drawView.clear();
        }, 1500);
    }
}
