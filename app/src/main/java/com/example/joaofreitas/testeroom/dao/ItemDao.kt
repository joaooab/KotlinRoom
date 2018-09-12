package com.example.joaofreitas.testeroom.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.joaofreitas.testeroom.model.Item

@Dao
interface ItemDao{

	@get:Query("SELECT * FROM item")
	val all: List<Item>

	@Query("SELECT * FROM item WHERE nome LIKE :nome")
	fun findByName(nome: String): Item

	@Insert
	fun add(items: List<Item>)

	@Insert
	fun add(vararg item: Item)

	@Delete
	fun delete(item: Item)
}