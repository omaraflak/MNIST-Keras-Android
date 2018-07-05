package com.aflak.mnistcnn.ml;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

/**
 * Created by omar on 7/5/18.
 */

public class CNN {
    private TensorFlowInferenceInterface inferenceInterface;
    public final static int IMAGE_SIZE = 28;
    public final static int OUTPUT_SIZE = 10;

    public CNN(TensorFlowInferenceInterface inferenceInterface) {
        this.inferenceInterface = inferenceInterface;
    }

    public float[] predict(float[] input){
        float output[] = new float[OUTPUT_SIZE];
        inferenceInterface.feed("conv2d_1_input", input, 1, IMAGE_SIZE, IMAGE_SIZE, 1);
        inferenceInterface.run(new String[]{"dense_3/Softmax"});
        inferenceInterface.fetch("dense_3/Softmax", output);
        return output;
    }

    public Result bestResult(float[] array){
        Result result = new Result(0, 0f);
        for(int i=0 ; i<array.length ; i++){
            if(array[i]>result.score){
                result.score = array[i];
                result.number = i;
            }
        }
        return result;
    }

    public class Result {
        public int number;
        public float score;

        public Result(int number, float score) {
            this.number = number;
            this.score = score;
        }
    }
}
