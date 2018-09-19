package com.example.joaofreitas.testeroomfinal.ui.pedido.list

import br.com.maximasistemas.arch.mvp.view.MvpListaView
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository

interface ListPedidoView : MvpListaView {
	fun obterRepository(): PedidoRepository
}
