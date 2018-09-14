package com.example.joaofreitas.testeroomfinal.repository

import com.example.joaofreitas.testeroomfinal.dao.ItemDao
import com.example.joaofreitas.testeroomfinal.model.Item

class ItemRepository private constructor(private val itemDao: ItemDao) {
	fun obtemItem() = itemDao.all()

	fun adicionaPedido(item: Item) = itemDao.add(item)

	fun deletaPedido(item: Item) = itemDao.delete(item)

	companion object {
		@Volatile
		private var instance: ItemRepository? = null

		fun getInstance(itemDao: ItemDao) =
				instance ?: synchronized(this) {
					instance ?: ItemRepository(itemDao).also { instance = it }
				}
	}
}