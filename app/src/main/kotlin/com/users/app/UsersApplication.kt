package com.users.app

import android.app.Application
import com.users.app.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*

class UsersApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UsersApplication)
            modules(AppModule().module)
        }
    }
}