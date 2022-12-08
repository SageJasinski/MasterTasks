package com.example.mastertask.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mastertask.DAO;
import com.example.mastertask.Tasks;

@TypeConverters({TaskmasterDataConverter.class})
@Database(entities = {Tasks.class}, version = 1)

public abstract class TaskmasterData extends RoomDatabase {
    public abstract DAO taskDAO();
}
