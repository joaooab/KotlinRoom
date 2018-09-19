package com.example.joaofreitas.testeroomfinal.di

import android.app.Application
import org.koin.android.ext.android.startKoin

class MainApplication: Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin(this, listOf(appModule))
	}
}