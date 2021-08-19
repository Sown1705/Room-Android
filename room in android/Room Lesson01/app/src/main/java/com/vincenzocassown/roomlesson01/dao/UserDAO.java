package com.vincenzocassown.roomlesson01.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vincenzocassown.roomlesson01.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE name LIKE :name AND " +
            "name LIKE :address LIMIT 1")
    User findByName(String name, String address);

    @Insert
    void insertAll(User... users);

    @Update
    void updateUser(User... user);

    @Delete
    void delete(User... user);

    @Query("SELECT * FROM user WHERE name=:name")
    List<User> checkUser(String name);

}