package com.rugid.core.data.preferences

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun SharedPreferences.asyncEditData(
    sharPrefMode: SharPrefMode = SharPrefMode.APPLY,
    onSuccess: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    editorBlock: SharedPreferences.Editor.() -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val editor = edit()
            editor.editorBlock()

            val isDataWasWritten = when (sharPrefMode) {
                SharPrefMode.APPLY -> {
                    editor.apply()
                    true
                }

                SharPrefMode.COMMIT -> editor.commit()
            }

            if (isDataWasWritten) {
                onSuccess?.let { onSuccessFun ->
                    withContext(Dispatchers.Main) { onSuccessFun() }
                }
            } else {
                onError?.let { onErrorFun ->
                    withContext(Dispatchers.Main) { onErrorFun(Exception("The commit failed.")) }
                }
            }

        } catch (e: Exception) {
            onError?.let { withContext(Dispatchers.Main) { it(e) } }
        }
    }
}

inline fun <reified T> SharedPreferences.asyncLoadData(
    key: String,
    defaultValue: T,
    noinline onSuccess: ((T) -> Unit)? = null,
    noinline onError: ((Throwable) -> Unit)? = null
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val valueFromSharPref: T = when (defaultValue) {
                is String -> getString(key, defaultValue) as T
                is Int -> getInt(key, defaultValue) as T
                is Long -> getLong(key, defaultValue) as T
                is Float -> getFloat(key, defaultValue) as T
                is Boolean -> getBoolean(key, defaultValue) as T
                else -> throw IllegalArgumentException("This type of value is not supported.")
            }

            onSuccess?.let { withContext(Dispatchers.Main) { it(valueFromSharPref) } }
        } catch (e: Exception) {
            onError?.let { withContext(Dispatchers.Main) { it(e) } }
        }
    }
}