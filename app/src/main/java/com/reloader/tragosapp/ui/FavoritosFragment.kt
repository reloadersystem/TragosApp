package com.reloader.tragosapp.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.reloader.tragosapp.AppDatabase
import com.reloader.tragosapp.R
import com.reloader.tragosapp.data.DataSource
import com.reloader.tragosapp.domain.RepoImpl
import com.reloader.tragosapp.ui.viewmodel.MainViewModel
import com.reloader.tragosapp.ui.viewmodel.VMFactory
import com.reloader.tragosapp.vo.Resource

class FavoritosFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepoImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    } // in

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer { result ->

            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Log.d("Lista_Favoritos", "${result.data}")
                }

                is Resource.Failure -> {

                }
            }
        })
    }


}
