package com.dion.roommvp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize

@Entity(tableName = "biodata")
data class Biodata (
    @PrimaryKey @ColumnInfo(name = "nik") var nik: String = "",
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "sex") var sex: String? = null,
    @ColumnInfo(name = "job")var job: String? = null
): Parcelable