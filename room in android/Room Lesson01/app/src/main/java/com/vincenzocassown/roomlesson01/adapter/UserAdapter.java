package com.vincenzocassown.roomlesson01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincenzocassown.roomlesson01.database.UserDatabase;
import com.vincenzocassown.roomlesson01.databinding.ItemBinding;
import com.vincenzocassown.roomlesson01.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    List<User> list;
    ItemClick click;
    public interface ItemClick{
        void update(User u);
        void delete(User u);

    }

    public UserAdapter(ItemClick click) {
        this.click=click;
    }

    public void setList(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBinding binding = ItemBinding.inflate(inflater,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position ) {
            User u = list.get(position);
            holder.binding.setUser(u);
            holder.binding.executePendingBindings();//update data

            holder.binding.tvUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.update(u);
                }
            });

            holder.binding.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.delete(u);
                }
            });
    }


    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemBinding binding;
        public ViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
