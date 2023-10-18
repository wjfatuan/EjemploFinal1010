package co.edu.uan.android.ejemplofinal1010.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.edu.uan.android.ejemplofinal1010.databinding.FragmentHomeBinding
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONObject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.vm = homeViewModel
        homeViewModel.changeText("Hello, this is the home")

        homeViewModel.loadCats() // 1. invocar logica de negocio
        homeViewModel.catsList.observe(viewLifecycleOwner) {
            Log.d("CATAPI","Cats list: $it")
            // 2. observamos cambios en el viewmodel. si cambian , refrescamos la pantalla
            showCatImage(it.get(0).url) // si los datas cambian, mostramos el nuevo gato
        }
        return root
    }

    fun showCatImage(url: String) {
        Log.d("CATAPI","The url is $url")
        Picasso.get()
            .load(url)
            .into(binding.imgCat)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}