package com.example.joaofreitas.testeroomfinal.data.repository.pedido

import android.arch.lifecycle.LiveData

interface PedidoRepository {
	fun obtemPedidosLiveData(): LiveData<List<Pedido>>

	fun obtemPedidos(): List<Pedido>

	fun adicionaPedido(pedido: Pedido)

	fun deletaPedido(pedido: Pedido)
}
