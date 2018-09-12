package com.example.joaofreitas.testeroom.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.joaofreitas.testeroom.model.Pedido

@Dao
interface PedidoDao {

	@get:Query("SELECT * FROM pedido")
	val all: List<Pedido>

	@Query("SELECT * FROM pedido WHERE nome LIKE :nome")
	fun findByName(nome: String): Pedido

	@Query("SELECT * FROM pedido WHERE id LIKE :id")
	fun findById(id: Long): Pedido

	@Insert
	fun add(pedidos: List<Pedido>)

	@Insert
	fun add(vararg pedido: Pedido)

	@Delete
	fun delete(pedido: Pedido)
}