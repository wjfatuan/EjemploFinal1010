package co.edu.uan.android.ejemplofinal1010.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uan.android.ejemplofinal1010.models.Cat
import co.edu.uan.android.ejemplofinal1010.services.CatApi
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel(application: Application) : AndroidViewModel(application) {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
    var text: String = "_text"
    val catsList = MutableLiveData<MutableList<Cat>>()

    fun changeText(t: String) {
        text = t
    }

    /**
     * Metodo de logica de negocio, llama el API
     */
    fun loadCats() {
        val api = CatApi.getInstance()
        val search = api.search()
        search.enqueue(object: Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                val list = mutableListOf<Cat>()
                if(response.body()!=null) {
                    list.addAll(response.body()!!)
                }
                catsList.setValue(list)
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                Log.e("CATAPI","Error del API $t")
            }

        })
    }
}