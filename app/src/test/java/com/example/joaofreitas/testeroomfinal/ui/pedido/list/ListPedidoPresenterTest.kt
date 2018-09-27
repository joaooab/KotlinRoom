package com.example.joaofreitas.testeroomfinal.ui.pedido.list

import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository
import com.example.joaofreitas.testeroomfinal.di.appModule
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest


class ListPedidoPresenterTest : KoinTest {

	val repository: PedidoRepository by inject()

	@Test
	fun deve_fazer_injecao_corretamente() {
		startKoin(listOf(appModule))

		assertNotNull(repository.obtemPedidos())
	}
}