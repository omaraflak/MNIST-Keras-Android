package com.aflak.mnistcnn.ml;

import com.aflak.mnistcnn.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by omar on 7/5/18.
 */

@Singleton
@Component(modules = {MLModule.class})
public interface MLComponent {
    void inject(MainActivity activity);
}
