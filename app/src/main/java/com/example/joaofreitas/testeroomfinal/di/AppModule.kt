package com.example.joaofreitas.testeroomfinal.di

import android.arch.persistence.room.Room
import com.example.joaofreitas.testeroomfinal.data.local.AppDatabase
import com.example.joaofreitas.testeroomfinal.data.local.repository.item.ItemRepository
import com.example.joaofreitas.testeroomfinal.data.local.repository.item.ItemRepositoryImpl
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepositoryImpl
import com.example.joaofreitas.testeroomfinal.data.remoto.conexao.ConexaoClient
import com.example.joaofreitas.testeroomfinal.data.remoto.conexao.ConexaoClientRepository
import com.example.joaofreitas.testeroomfinal.data.remoto.datamanager.DataManagerClient
import com.example.joaofreitas.testeroomfinal.data.remoto.datamanager.DataMangerClientRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {

	single {
		Room.databaseBuilder(
				androidContext(),
				AppDatabase::class.java,
				"teste_room")
				.allowMainThreadQueries()
				.fallbackToDestructiveMigration()
				.build()
	}

	single<PedidoRepository> { PedidoRepositoryImpl(get()) }
	single { get<AppDatabase>().pedidoDao() }

	single<ItemRepository> { ItemRepositoryImpl(get()) }
	single { get<AppDatabase>().itemDao() }

	single<ConexaoClientRepository> { ConexaoClient() }

	single<DataMangerClientRepository> { DataManagerClient() }

}