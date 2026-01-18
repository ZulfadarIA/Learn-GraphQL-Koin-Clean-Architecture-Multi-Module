package com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.zulfadar.core.repository.TokenRepository

class TokenRepositoryImpl(
    context: Context
) : TokenRepository {

    private val preferences: SharedPreferences

    companion object {
        private const val PREF_NAME = "secret_shared_prefs"
        private const val KEY_TOKEN = "TOKEN"
    }

    init {
        val masterKey = MasterKey.Builder(
            context.applicationContext,
            MasterKey.DEFAULT_MASTER_KEY_ALIAS
        )
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        preferences = EncryptedSharedPreferences.create(
            context.applicationContext,
            PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun getToken(): String? {
        return preferences.getString(KEY_TOKEN, null)
    }

    override fun setToken(token: String) {
        preferences.edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    override fun clearToken() {
        preferences.edit()
            .remove(KEY_TOKEN)
            .apply()
    }
}