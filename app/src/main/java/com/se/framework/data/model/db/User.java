package com.se.framework.data.model.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @ColumnInfo(name = "created_at")
    public String createdAt;

    @PrimaryKey
    @NonNull
    public String id;


    @ColumnInfo(name = "updated_at")
    public String updatedAt;
}
