package com.aflak.mnistcnn.ml;

import android.content.Context;
import android.content.res.AssetManager;

import com.aflak.mnistcnn.ml.CNN;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by omar on 7/5/18.
 */

@Module
public class MLModule {
    private Context context;

    public MLModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides @Singleton
    public AssetManager provideAssetManager(Context context){
        return context.getAssets();
    }

    @Provides @Singleton
    public TensorFlowInferenceInterface provideTensorflow(AssetManager assetManager){
        return new TensorFlowInferenceInterface(assetManager, "tensorflow_lite_convnet.pb");
    }

    @Provides @Singleton
    public CNN provideCNN(TensorFlowInferenceInterface inferenceInterface){
        return new CNN(inferenceInterface);
    }
}
