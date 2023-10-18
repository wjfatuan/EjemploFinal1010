package co.edu.uan.android.ejemplofinal1010.services

import co.edu.uan.android.ejemplofinal1010.models.Cat
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CatApi {

    @GET("/v1/images/search")
    suspend fun search(@Query("limit") n: Int): List<Cat>

    @POST("/v1/images/")
    fun createCat(cat: Cat)

    companion object {
        fun getInstance() : CatApi {
            var retrofit = Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retrofit.create(CatApi::class.java)
            return api
        }
    }
}