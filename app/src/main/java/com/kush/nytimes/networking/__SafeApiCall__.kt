package com.kush.nytimes.networking

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Result<T> {
    return withContext(dispatcher) {
        try {
            Result.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                // is IOException -> Result.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    Result.GenericError(code, errorResponse)
                }
                else -> {
                    Log.d("Generic Error: ", throwable.localizedMessage?:"")
                    Result.GenericError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        val body = throwable.response()?.errorBody()?.charStream()
        val gson = GsonBuilder().create()
        val responseBody = gson.fromJson(body, JsonObject::class.java)
        val errorEntity = gson.fromJson(responseBody, ErrorResponse::class.java)

        errorEntity
    } catch (exception: Exception) {
        null
    }
}