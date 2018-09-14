package com.example.joaofreitas.testeroomfinal.repository

import com.example.joaofreitas.testeroomfinal.dao.PedidoDao
import com.example.joaofreitas.testeroomfinal.model.Pedido

class PedidoRepository private constructor(private val pedidoDao: PedidoDao) {

	fun obtemPedidos() = pedidoDao.all()

	fun adicionaPedido(pedido: Pedido) = pedidoDao.add(pedido)

	fun deletaPedido(pedido: Pedido) = pedidoDao.delete(pedido)

	companion object {
		@Volatile
		private var instance: PedidoRepository? = null

		fun getInstance(pedidoDao: PedidoDao) =
				instance ?: synchronized(this) {
					instance ?: PedidoRepository(pedidoDao).also { instance = it }
				}
	}
}