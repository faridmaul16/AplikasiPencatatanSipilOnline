package com.e.oraapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<String> mtext;

    public ProfileViewModel(){
        mtext = new MutableLiveData<>();
        mtext.setValue("Ini adalah profile fragment");
    }
    public LiveData<String> getText(){return mtext;}
}
