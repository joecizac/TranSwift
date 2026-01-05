package com.jozze.transwift.data.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSBundle
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = documentDirectory() + "/transwift.db"

    // Copy from bundle to documents if not exists
    if (!NSFileManager.defaultManager.fileExistsAtPath(dbFilePath)) {
        val bundlePath = NSBundle.mainBundle.pathForResource("transwift", ofType = "db")
        if (bundlePath != null) {
            try {
                NSFileManager.defaultManager.copyItemAtPath(
                    bundlePath,
                    toPath = dbFilePath,
                    error = null
                )
            } catch (e: Exception) {
                println("Error copying database: ${e.message}")
            }
        } else {
            println("transwift.db not found in bundle")
        }
    }

    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}