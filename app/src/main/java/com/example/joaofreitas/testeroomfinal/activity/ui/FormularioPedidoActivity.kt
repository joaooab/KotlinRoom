package com.example.joaofreitas.testeroomfinal.activity.ui

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.dao.PedidoDao
import com.example.joaofreitas.testeroomfinal.database.Database
import com.example.joaofreitas.testeroomfinal.model.Pedido
import kotlinx.android.synthetic.main.activity_formulario_pedido.*

class FormularioPedidoActivity : AppCompatActivity() {

	private lateinit var pedidoDao: PedidoDao

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_formulario_pedido)
		val database = Database.getInstance(this)
		pedidoDao = database.pedidoDao()
		configuraBotaoSalvar()
	}

	private fun configuraBotaoSalvar() {
		formulario_pedido_salvar.setOnClickListener {
			SalvaPedido().execute()
		}
	}

	inner class SalvaPedido : AsyncTask<Void, Void, Void>(){
		override fun doInBackground(vararg params: Void?): Void? {
			val pedidoCriado = criaPedido()
			pedidoDao.add(pedidoCriado)
			finish()
			return null
		}

		private fun criaPedido(): Pedido {
			val nome = formulario_pedido_nome.editText?.text.toString()
			return Pedido(nome = nome)
		}
	}
}
