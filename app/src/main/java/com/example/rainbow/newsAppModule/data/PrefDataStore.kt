package com.example.rainbow.newsAppModule.data

import kotlinx.coroutines.flow.Flow

interface PrefDataStore {
    suspend fun saveAppOnboarding(data:Boolean)

    fun getAppOnboarding(): Flow<Boolean>
}