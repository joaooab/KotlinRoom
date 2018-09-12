package com.example.joaofreitas.testeroom.model

import android.arch.persistence.room.*
import java.io.Serializable

@Entity(foreignKeys =
[
	ForeignKey(
			entity = Pedido::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("pedido_id"),
			onDelete = ForeignKey.CASCADE
	)
], indices = [Index(value = ["pedido_id"])]
)
class Item(
		@ColumnInfo(name = "nome")
		var nome: String,
		@ColumnInfo(name = "valor")
		var valor: Float
) : Serializable {
	@PrimaryKey(autoGenerate = true)
	var id: Long = 0
	@ColumnInfo(name = "pedido_id")
	var pedidoId: Long = 0
}