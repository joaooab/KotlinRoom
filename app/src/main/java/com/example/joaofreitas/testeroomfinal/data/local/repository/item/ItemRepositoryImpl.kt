package com.example.joaofreitas.testeroomfinal.data.local.repository.item

import javax.inject.Singleton

//TODO testar @Singleton
//TODO criar interface

class ItemRepositoryImpl @Singleton constructor(private val itemDao: ItemDao) : ItemRepository {

	override fun obtemItensLiveData() = itemDao.all()

	override fun obtemItensPorPedidoId(id: Long) = itemDao.allByPedidoId(id)

	override fun obtemItensLiveDataPorPedidoId(id: Long) = itemDao.allByLiveDataPedidoId(id)

	override fun adicionaPedido(item: Item) = itemDao.add(item)

	override fun deletaPedido(item: Item) = itemDao.delete(item)

//	companion object {
//		@Volatile
//		private var instance: ItemRepositoryImpl? = null
//
//		fun getInstance(itemDao: ItemDao) =
//				instance
//						?: synchronized(this) {
//							instance
//									?: ItemRepositoryImpl(itemDao).also { instance = it }
//						}
//	}
}