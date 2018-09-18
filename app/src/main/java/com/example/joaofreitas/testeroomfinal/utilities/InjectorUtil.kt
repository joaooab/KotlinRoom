package com.example.joaofreitas.testeroomfinal.utilities

import android.content.Context
import com.example.joaofreitas.testeroomfinal.data.Database
import com.example.joaofreitas.testeroomfinal.data.repository.item.ItemRepository
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.PedidoRepositoryImpl

object InjectorUtil {

	fun getPedidoRepository(context: Context) : PedidoRepositoryImpl {
		return PedidoRepositoryImpl.getInstance(Database.getInstance(context).pedidoDao())
	}

	fun getItemRepository(context: Context) : ItemRepository {
		return ItemRepository.getInstance(Database.getInstance(context).itemDao())
	}
}