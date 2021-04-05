package com.dion.roommvp

import androidx.room.*
import com.dion.roommvp.model.Biodata

@Dao
interface BioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(bio: Biodata)

    @Query("SELECT * FROM biodata")
    suspend fun selectAll(): MutableList<Biodata>

    @Query("SELECT * FROM biodata where  nik = :id")
    suspend fun selectOne(id: String):Biodata

    @Update
    suspend fun updateData(bio: Biodata)

    @Delete
    suspend fun deleteData(bio: Biodata)

    @Query("SELECT * FROM biodata WHERE name LIKE :name")
    suspend fun searchName(name: String): MutableList<Biodata>
}