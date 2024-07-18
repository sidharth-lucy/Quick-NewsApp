package com.example.rainbow.newsAppModule.data.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.rainbow.baseUtils.Constants
import com.example.rainbow.newsAppModule.data.PrefDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefDataStoreImpl(private val context: Context): PrefDataStore {

    override suspend fun saveAppOnboarding(data:Boolean) {
        context.dataStore.edit {dbPreferences->
            val key = booleanPreferencesKey(name = Constants.APP_ENTRY_DATA_KEY)
            dbPreferences[key] = data
        }
    }

    override fun getAppOnboarding():Flow<Boolean> {
        return context.dataStore.data.map {dbPreferences->
            val key = booleanPreferencesKey(name = Constants.APP_ENTRY_DATA_KEY)
            dbPreferences[key] ?: false
        }
    }
}

private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = Constants.APP_PREF_DATASTORE_KEY)