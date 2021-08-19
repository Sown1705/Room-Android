package com.vincenzocassown.roomlesson01.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vincenzocassown.roomlesson01.dao.UserDAO;
import com.vincenzocassown.roomlesson01.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "user.db";
    public static UserDatabase instance;
    public static synchronized UserDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DATABASE_NAME)
            .allowMainThreadQueries().
                    build();
    }
        return instance;
    };
    public abstract UserDAO userDao();

}
