package com.testrakuten;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by kuljeetsingh on 10/21/18.
 */

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<String> counterLabel = new MutableLiveData<>();
}
