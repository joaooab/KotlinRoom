package com.example.joaofreitas.testeroom.activity.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.joaofreitas.testeroom.R
import com.example.joaofreitas.testeroom.model.Item
import kotlinx.android.synthetic.main.activity_formulario_item.*

class FormularioItemActivity : AppCompatActivity() {

	companion object {
		const val CHAVE_ITEM = "ITEM"
		const val TITLE_APPBAR = "Formulario Item"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_formulario_item)

		title = TITLE_APPBAR

		configuraBotaoSalvar()
	}

	private fun configuraBotaoSalvar() {
		formulario_item_salvar.setOnClickListener {
			val itemCriado: Item = criaItem()
			vaiParaListaItens(itemCriado)
		}
	}

	private fun vaiParaListaItens(itemCriado: Item) {
		val vaiParaListaItens = Intent(this, FormularioPedidoActivity::class.java)
		vaiParaListaItens.putExtra(CHAVE_ITEM, itemCriado)
		startActivity(vaiParaListaItens)
		finish()
	}

	private fun criaItem(): Item {
		val nome: String = formulario_item_title.editText?.text.toString()
		val valor: Float = formulario_item_valor.editText?.text.toString().toFloat()

		return Item(nome = nome, valor = valor)
	}
}
