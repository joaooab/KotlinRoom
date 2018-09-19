package com.example.joaofreitas.testeroomfinal.data.local.repository.pedido

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PedidoDao {

	@Query("SELECT * FROM pedido ORDER BY id DESC")
	fun allLiveData(): LiveData<List<Pedido>>

	@Query("SELECT * FROM pedido ORDER BY id DESC")
	fun all(): List<Pedido>

	@Insert
	fun add(vararg pedido: Pedido)

	@Delete
	fun delete(vararg pedido: Pedido)
}