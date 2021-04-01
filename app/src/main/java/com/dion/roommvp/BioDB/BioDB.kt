package com.dion.roommvp.BioDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dion.roommvp.BioDao
import com.dion.roommvp.model.Biodata

@Database(entities = [Biodata::class], version = 1,exportSchema = false)
abstract class BioDB : RoomDatabase(){

    abstract fun bioDao(): BioDao

    companion object{
        private var INSTANCE: BioDB? = null
        private val sLock = Any()
        private const val DATABASE_NAME = "biodata"

        fun getInstance(context: Context): BioDB{
            synchronized(sLock){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        BioDB::class.java,
                        DATABASE_NAME
                    ).build()
                }

            }
            return INSTANCE as BioDB
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}