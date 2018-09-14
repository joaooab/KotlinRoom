package com.example.joaofreitas.testeroomfinal.utilities

import android.content.Context
import com.example.joaofreitas.testeroomfinal.database.Database
import com.example.joaofreitas.testeroomfinal.repository.ItemRepository
import com.example.joaofreitas.testeroomfinal.repository.PedidoRepository

object InjectorUtil {

	private fun getPedidoRepository(context: Context) : PedidoRepository {
		return PedidoRepository.getInstance(Database.getInstance(context).pedidoDao())
	}

	private fun getItemRepository(context: Context) : ItemRepository {
		return ItemRepository.getInstance(Database.getInstance(context).itemDao())
	}
}