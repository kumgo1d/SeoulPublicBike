package com.goldcompany.apps.koreabike

import android.app.Application
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import timber.log.Timber

class KBikeApplication: Application() {
    companion object {
        lateinit var instance: KBikeApplication
            private set
    }

    val database by lazy { KBikeDatabase.getInstance(this)}

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}