package com.example.joaofreitas.testeroomfinal.ui.pedido.formulario

import android.os.AsyncTask
import android.os.Bundle
import br.com.maximasistemas.arch.mvp.view.activities.MvpActivity
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.Pedido
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository
import kotlinx.android.synthetic.main.activity_formulario_pedido.*
import org.koin.android.ext.android.inject

class FormularioPedidoActivity : MvpActivity<FormularioPedidoView, FormularioPedidoPresenter>(), FormularioPedidoView {

	private val pedidoRepository: PedidoRepository by inject()

	override fun obterClassePresenter(): Class<FormularioPedidoPresenter> = FormularioPedidoPresenter::class.java

	override fun obterRepository(): PedidoRepository = pedidoRepository

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_formulario_pedido)

		configuraBotaoSalvar()
	}

	private fun configuraBotaoSalvar() {
		formulario_pedido_salvar.setOnClickListener {
			val pedidoCriado = criaPedido()
			presenter().salvaPedido(pedidoCriado)
		}
	}

	private fun criaPedido(): Pedido {
		val nome = formulario_pedido_nome.editText?.text.toString()
		return Pedido(nome = nome)
	}

	override fun finalizarTela() {
		finish()
	}
}


