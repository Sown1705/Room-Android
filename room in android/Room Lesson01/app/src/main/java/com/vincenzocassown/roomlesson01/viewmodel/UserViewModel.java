package com.vincenzocassown.roomlesson01.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vincenzocassown.roomlesson01.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {
    public MutableLiveData<List<User>> userModel = new MutableLiveData<>();
    public List<User> list = new ArrayList<>();
    public MutableLiveData<List<User>> getUserModel(){return userModel;}

    public void add(User u){
        list.add(u);
        userModel.postValue(list);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}