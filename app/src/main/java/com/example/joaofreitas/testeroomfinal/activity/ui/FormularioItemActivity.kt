package com.example.joaofreitas.testeroomfinal.activity.ui

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.dao.ItemDao
import com.example.joaofreitas.testeroomfinal.database.Database
import com.example.joaofreitas.testeroomfinal.model.Item
import com.example.joaofreitas.testeroomfinal.model.Pedido
import kotlinx.android.synthetic.main.activity_formulario_item.*
import kotlinx.android.synthetic.main.activity_pedido_detalhes.*

class FormularioItemActivity : AppCompatActivity() {

	private lateinit var itemDao: ItemDao
	private lateinit var pedido: Pedido

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_formulario_item)
		val dabase = Database.getInstance(this)
		itemDao = dabase.itemDao()

		if (!pedidoFoiCarregado()) finish()
		configuraBoraAddItem()
	}


	private fun pedidoFoiCarregado(): Boolean {
		intent.getParcelableExtra<Pedido>(CHAVE_PEDIDO)?.let {
			this.pedido = it
			return true
		}
		return false
	}

	private fun configuraBoraAddItem() {
		formulario_item_salvar.setOnClickListener {
			SalvaItem().execute()
		}
	}

	inner class SalvaItem() : AsyncTask<Void, Void, Void>() {
		override fun doInBackground(vararg params: Void?): Void? {
			val itemCriado = criaItem()
			itemDao.add(itemCriado)
			finish()
			return null
		}

		private fun criaItem(): Item {
			val nome = formulario_item_nome.editText?.text.toString()
			val valor = formulario_item_valor.editText?.text.toString().toFloat()
			return Item(nome = nome, valor = valor, pedidoId = pedido.id)
		}
	}
}
