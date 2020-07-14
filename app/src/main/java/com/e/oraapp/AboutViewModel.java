package com.e.oraapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {
    private MutableLiveData<String> mtext;

    public AboutViewModel(){
        mtext = new MutableLiveData<>();
        mtext.setValue("Ini adalah about fragment");
    }
    public LiveData<String> getText(){return mtext;}
}
