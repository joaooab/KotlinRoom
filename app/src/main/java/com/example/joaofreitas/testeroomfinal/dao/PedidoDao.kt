package com.example.joaofreitas.testeroomfinal.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.joaofreitas.testeroomfinal.model.Pedido

@Dao
interface PedidoDao {

	@Query("SELECT * FROM pedido ORDER BY id DESC")
	fun all(): LiveData<List<Pedido>>

	@Insert
	fun add(vararg pedido: Pedido)

	@Delete
	fun delete(vararg pedido: Pedido)
}