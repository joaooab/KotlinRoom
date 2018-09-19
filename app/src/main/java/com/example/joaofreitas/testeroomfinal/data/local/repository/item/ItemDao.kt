package com.example.joaofreitas.testeroomfinal.data.local.repository.item

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ItemDao {

	@Query("SELECT * FROM item ORDER BY id DESC")
	fun all(): LiveData<List<Item>>

	@Query("SELECT * FROM item WHERE pedidoId =:id ")
	fun allByLiveDataPedidoId(id: Long): LiveData<List<Item>>

	@Query("SELECT * FROM item WHERE pedidoId =:id ")
	fun allByPedidoId(id: Long): List<Item>

	@Insert
	fun add(vararg pedido: Item)

	@Delete
	fun delete(vararg pedido: Item)
}