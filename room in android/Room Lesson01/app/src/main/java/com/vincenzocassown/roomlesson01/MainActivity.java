package com.vincenzocassown.roomlesson01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vincenzocassown.roomlesson01.adapter.UserAdapter;
import com.vincenzocassown.roomlesson01.database.UserDatabase;
import com.vincenzocassown.roomlesson01.databinding.ActivityMainBinding;
import com.vincenzocassown.roomlesson01.model.User;
import com.vincenzocassown.roomlesson01.viewmodel.UserViewModel;

import java.time.Year;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    UserAdapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
         binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        adapter= new UserAdapter(new UserAdapter.ItemClick() {
            @Override
            public void update(User u) {
                updateUser(u);
            }

            @Override
            public void delete(User u) {
                deleteUser(u);
            }
        });

        loadData();
        UserViewModel model = new ViewModelProvider(this).get(UserViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setHasFixedSize(false);
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setName(binding.edtName.getText().toString());
                u.setAddress(binding.edtAddress.getText().toString());
                if (!checkUser(u.getName())){
                    Toast.makeText(getApplicationContext(),"User exits !",Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.setUser(u);
                model.add(u);
                UserDatabase.getInstance(getApplicationContext()).userDao().insertAll(u);
            }
        });

        model.getUserModel().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setList(UserDatabase.getInstance(getApplicationContext()).userDao().getAll());
                binding.recyclerView.setAdapter(adapter);
            }
        });

        binding.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void updateUser(User u) {
    }

    private void deleteUser(User u) {
        new AlertDialog.Builder(this)
                .setTitle("Comfirm delete user ?")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserDatabase.getInstance(getApplicationContext()).userDao().delete(u);
                        Toast.makeText(getApplicationContext(),"Delete success !",Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    public boolean checkUser(String u){
        List<User> list = UserDatabase.getInstance(this).userDao().checkUser(u);
        return list!=null && list.isEmpty();
    }

    public void loadData(){
        adapter.setList(UserDatabase.getInstance(getApplicationContext()).userDao().getAll());
        binding.recyclerView.setAdapter(adapter);
    }

}