package com.example.joaofreitas.testeroomfinal.ui.pedido.formulario

import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository

interface FormularioPedidoView {
	fun obterRepository(): PedidoRepository
	fun finalizarTela()
}