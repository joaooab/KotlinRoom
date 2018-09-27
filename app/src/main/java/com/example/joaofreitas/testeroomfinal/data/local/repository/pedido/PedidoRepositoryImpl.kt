package com.example.joaofreitas.testeroomfinal.data.local.repository.pedido

import javax.inject.Singleton

class PedidoRepositoryImpl @Singleton constructor(private val pedidoDao: PedidoDao) : PedidoRepository {

	override fun obtemPedidosLiveData() = pedidoDao.allLiveData()

	override fun obtemPedidos() = pedidoDao.all()

	override fun adicionaPedido(pedido: Pedido) = pedidoDao.add(pedido)

	override fun deletaPedido(pedido: Pedido) = pedidoDao.delete(pedido)

//	companion object {
//		@Volatile
//		private var instance: PedidoRepositoryImpl? = null
//
//		fun getInstance(pedidoDao: PedidoDao) =
//				instance
//						?: synchronized(this) {
//					instance
//							?: PedidoRepositoryImpl(pedidoDao).also { instance = it }
//				}
//	}
}