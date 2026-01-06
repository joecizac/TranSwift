package com.jozze.transwift.di

import com.jozze.transwift.data.db.AppDatabase
import com.jozze.transwift.data.db.getRoomDatabase
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication {
    return startKoin {
        modules(
            appModule,
            databaseModule
        )
    }
}

val databaseModule = module {
    single<AppDatabase> { getRoomDatabase(get()) }
    single { get<AppDatabase>().trainDao() }
    single { get<AppDatabase>().scheduleDao() }
}