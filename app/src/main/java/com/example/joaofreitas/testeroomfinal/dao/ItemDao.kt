package com.example.joaofreitas.testeroomfinal.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.joaofreitas.testeroomfinal.model.Item

@Dao
interface ItemDao {

	@Query("SELECT * FROM item ORDER BY id DESC")
	fun all(): LiveData<List<Item>>

	@Query("SELECT * FROM item WHERE pedidoId =:id ")
	fun allByPedidoId(id: Long): LiveData<List<Item>>

	@Insert
	fun add(vararg pedido: Item)

	@Delete
	fun delete(vararg pedido: Item)
}