package com.example.joaofreitas.testeroomfinal.ui.item.formulario

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.data.repository.item.ItemDao
import com.example.joaofreitas.testeroomfinal.data.Database
import com.example.joaofreitas.testeroomfinal.data.repository.item.Item
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.Pedido
import com.example.joaofreitas.testeroomfinal.ui.pedido.CHAVE_PEDIDO
import kotlinx.android.synthetic.main.activity_formulario_item.*

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
