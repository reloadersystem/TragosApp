package com.reloader.tragosapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.reloader.tragosapp.AppDatabase
import com.reloader.tragosapp.R
import com.reloader.tragosapp.data.DataSource
import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.data.model.DrinkEntity
import com.reloader.tragosapp.domain.RepoImpl
import com.reloader.tragosapp.ui.viewmodel.MainViewModel
import com.reloader.tragosapp.ui.viewmodel.VMFactory
import com.reloader.tragosapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_favoritos.*

class FavoritosFragment : Fragment(), MainAdapter.OnTragoClickListener {


    private lateinit var adapter:MainAdapter

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

        setupRecyclerView()
        setupObservers()


    }


    fun setupRecyclerView() {

        rv_tragos_favoritos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos_favoritos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun setupObservers() {
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer { result ->

            when (result) {
                is Resource.Loading -> {


                }
                is Resource.Success -> {
                    // Log.d("Lista_Favoritos", "${result.data}")


                    //map no ayuda a reutilizar el mismo adapter y lo llena  a una nueva lista las que nos  pida el adaptador
                    val lista = result.data.map {
                        Drink(it.tragoId, it.imagen, it.nombre, it.descripcion, it.hasAlcohol)
                    }.toMutableList()

                    adapter = MainAdapter(requireContext(),lista, this)
                    rv_tragos_favoritos.adapter = adapter
                }

                is Resource.Failure -> {

                }
            }
        })
    }

    override fun onTragoClick(drink: Drink, position: Int) {
        viewModel.deleteDrink(
            DrinkEntity(
                drink.tragoId,
                drink.imagen,
                drink.nombre,
                drink.descripcion,
                drink.hasAlcohol
            )
        )
        adapter.deleteDrink(position)
        Toast.makeText(requireContext(), "Se borro el trago de favoritos", Toast.LENGTH_SHORT).show()

    }
}
