package com.example.manorroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.manorroom.Zodiac

@Database(entities = [Zodiac::class], version = 1)
abstract class ZodiacDatabase : RoomDatabase() {
    abstract fun zodiacDao(): ZodiacDao
}