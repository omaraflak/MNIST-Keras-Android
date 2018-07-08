package com.aflak.mnistcnn;

import android.app.Application;

import com.aflak.mnistcnn.ml.DaggerMLComponent;
import com.aflak.mnistcnn.ml.MLComponent;
import com.aflak.mnistcnn.ml.MLModule;

/**
 * Created by omar on 7/5/18.
 */

public class MyApp extends Application {
    private static MyApp app;
    private MLComponent mlComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mlComponent = DaggerMLComponent.builder()
                .mLModule(new MLModule(this))
                .build();
    }

    public static MyApp app(){
        return app;
    }

    public MLComponent mlComponent(){
        return mlComponent;
    }
}
