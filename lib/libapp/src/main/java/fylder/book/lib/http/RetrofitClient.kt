package fylder.book.lib.http

import com.orhanobut.logger.Logger
import fylder.book.lib.http.converter.StringConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    private val baseUrl = "https://api.github.com"

    private fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    /**
     * 获取一个Api Service
     */
    fun <T> getInstance(service: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //RxJava回调
            .addConverterFactory(StringConverterFactory())              //解析String 而且必须放在json解析之上
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(service)
    }
}