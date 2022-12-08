package com.example.mastertask.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class TaskmasterDataConverter {
    @TypeConverter
    public static Date timeStamp(Long dateAdded){
        return dateAdded == null ? null : new Date(dateAdded);
    }

    @TypeConverter
    public static Long dateToTime(Date date){
        return date == null ? null : date.getTime();
    }
}
