package com.reloader.tragosapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.reloader.tragosapp.AppDatabase
import com.reloader.tragosapp.R
import com.reloader.tragosapp.data.DataSource
import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.domain.RepoImpl
import com.reloader.tragosapp.ui.viewmodel.MainViewModel
import com.reloader.tragosapp.ui.viewmodel.VMFactory
import com.reloader.tragosapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.FieldPosition

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), MainAdapter.OnTragoClickListener {


    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(RepoImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    } // inyeccion de  dependencias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()// viewLifecycleOwner cuando pasa el onDestroy del fragment  se elimina

        setupSearchView()

        setupObservers()

        btn_ir_favoritos.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
        }

    }


    private fun setupObservers() {
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(), result.data.toMutableList(), this)
                }

                is Resource.Failure -> {

                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error al traer los datos ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }


    override fun onTragoClick(drink: Drink, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        //findNavController().navigate(R.id.tragosDetalleFragment, bundle)
        findNavController().navigate(
            R.id.action_mainFragment_to_tragosDetalleFragment,
            bundle
        ) //para que funcione las animaciones agregamos el segue
    }

    fun setupRecyclerView() {
        rv_tragos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                viewModel.setTrago(query!!)
                return false

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }


}
