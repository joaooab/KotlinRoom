package com.example.joaofreitas.testeroomfinal.data.local.repository.item

import android.arch.lifecycle.LiveData

interface ItemRepository {
	fun obtemItensLiveData(): LiveData<List<Item>>

	fun obtemItensPorPedidoId(id: Long): List<Item>

	fun obtemItensLiveDataPorPedidoId(id: Long): LiveData<List<Item>>

	fun adicionaPedido(item: Item)

	fun deletaPedido(item: Item)
}