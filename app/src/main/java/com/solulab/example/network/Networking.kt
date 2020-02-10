package com.solulab.example.network

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

open class Networking(private val context: Context? = null) {

//    private var baseURLL: String = "http://www.mocky.io/v2/"

    private var baseURL: String = "https://tofiktech.000webhostapp.com/techclass/"
    private var fileParams = java.util.HashMap<String, File>()

    companion object {
        /**
         * @param context
         * @return Instance of this class
         * create instance of this class
         */
        fun with(context: Context? = null): Networking {
            return Networking(context)
        }

        fun wrapParams(params: HashMap<String, *>): RequestBody {
            return JSONObject(params).toString()
                .toRequestBody(
                    "application/json; charset=utf-8".toMediaTypeOrNull()
                )

        }

       /* fun wrapParamsTwo(params: HashMap<String, *>, key: String?): RequestBody {
            Log.v("params", params.getValue("name").toString())
            if (key == null) {
                return RequestBody.create(
                    "application/json; charset=utf-8".toMediaTypeOrNull(),
                    JSONObject(params).toString()
                )
            } else {
                return RequestBody.create(
                    "application/json; charset=utf-8".toMediaTypeOrNull(),
                    params.getValue(key).toString()
                )
            }

        }
*/
    }


    fun getServices(): APIInterface {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)

        //Log
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)

        //GSON converter
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return retrofit2.Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build().create(APIInterface::class.java)
    }

}

