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
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            val list = mutableListOf<Cat>()
            list.addAll(api.search(4))
            catsList.setValue(list)
        }
    }
}