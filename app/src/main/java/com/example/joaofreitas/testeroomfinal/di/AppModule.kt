package com.example.joaofreitas.testeroomfinal.di

import android.arch.persistence.room.Room
import com.example.joaofreitas.testeroomfinal.data.AppDatabase
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.PedidoRepository
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.PedidoRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {

	single { Room.databaseBuilder(
			androidContext(),
			AppDatabase::class.java,
			"teste_room").
			fallbackToDestructiveMigration().
			build()}

	factory<PedidoRepository> { PedidoRepositoryImpl(get()) }
	factory { get<AppDatabase>().pedidoDao() }
}