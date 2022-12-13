//package com.example.mastertask;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import java.util.List;
//
//@Dao
//public interface DAO {
//
//    @Insert
//    public void insertTask(Tasks tasks);
//
//    @Query("SELECT * FROM Tasks WHERE id = :id")
//    public Tasks findById(long id);
//
//    @Query("SELECT * FROM Tasks")
//    public List<Tasks> findAll();
//
//    @Delete
//    public void delete(Tasks tasks);
//
//    @Update
//    public void update(Tasks tasks);
//}
