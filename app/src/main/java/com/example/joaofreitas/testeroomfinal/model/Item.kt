package com.example.joaofreitas.testeroomfinal.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class Item(
		@PrimaryKey(autoGenerate = true)
		val id: Long = 0,
		val nome: String,
		val valor: Float,
		@ForeignKey(
				entity = Pedido::class,
				parentColumns = arrayOf("id"),
				childColumns = arrayOf("pedido_id"),
				onDelete = ForeignKey.CASCADE
		)
		val pedidoId: Long
) : Parcelable