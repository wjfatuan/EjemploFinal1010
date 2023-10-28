package co.edu.uan.android.ejemplofinal1010.ui.dashboard

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel(val app: Application) : AndroidViewModel(app) {

    var catUrlList = MutableLiveData<List<String>>()

    fun loadCatsFromDatabase() {
        val db =
            app.openOrCreateDatabase("cats_database_sqlite", Context.MODE_PRIVATE, null)
        val cursor = db.rawQuery("SELECT url FROM cats", null)
        val list = mutableListOf<String>()
        while(cursor.moveToNext()) {
            val url = cursor.getString(0)
            list.add(url)
        }
        cursor.close()
        catUrlList.postValue(list)
        db.close()
    }
}