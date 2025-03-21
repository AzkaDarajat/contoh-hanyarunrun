package com.example.hanyarunrun.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val nim: String,
    val nama: String,
    val kelas: String,
    val email: String,
    val profileImagePath: String
)
