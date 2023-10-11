package co.edu.uan.android.ejemplofinal1010.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
    var text: String = "_text"

    fun changeText(t: String) {
        text = t
    }
}