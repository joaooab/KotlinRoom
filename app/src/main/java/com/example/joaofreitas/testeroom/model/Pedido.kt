package com.example.joaofreitas.testeroom.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Pedido(
		@PrimaryKey(autoGenerate = true)
		val id: Long = 0,
		val nome: String) : Serializable {

	var total: Float = 0.0F

	fun calculaTotal(items: List<Item>): Float {
		for (item: Item in items) {
			this.total += item.valor
		}
		return total
	}
}

