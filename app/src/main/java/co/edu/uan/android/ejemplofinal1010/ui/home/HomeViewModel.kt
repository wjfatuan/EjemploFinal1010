package co.edu.uan.android.ejemplofinal1010.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uan.android.ejemplofinal1010.models.Cat
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion

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
        val context = getApplication<Application>().applicationContext
        Ion.with(context) // llama el API
            .load("https://api.thecatapi.com/v1/images/search?limit=1&api_key=REPLACE_ME")
            .asString()
            .setCallback { e, result ->
                // Procesa el resultado de llamar al API
                Log.d("CATAPI","$result")
                // CONvertir a objetos de Kotlin usando Gson
                var gson = Gson()
                var data = gson.fromJson(result, Array<Cat>::class.java)
                // Actualizar los valores en el view model con lo que obtuvo desde el API
                val list = mutableListOf<Cat>()
                list.addAll(data)
                catsList.setValue(list)
            }
    }
}