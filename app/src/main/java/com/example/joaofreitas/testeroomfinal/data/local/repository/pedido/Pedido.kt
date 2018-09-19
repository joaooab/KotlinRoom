package com.example.joaofreitas.testeroomfinal.data.local.repository.pedido

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class Pedido(
		@PrimaryKey(autoGenerate = true)
		val id: Long = 0,
		val nome: String,
		val total: Float = 0F) : Parcelable