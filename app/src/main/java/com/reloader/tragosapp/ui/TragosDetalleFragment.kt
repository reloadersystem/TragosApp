package com.reloader.tragosapp.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.reloader.tragosapp.AppDatabase
import com.reloader.tragosapp.R
import com.reloader.tragosapp.data.DataSource
import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.data.model.DrinkEntity
import com.reloader.tragosapp.domain.RepoImpl
import com.reloader.tragosapp.ui.viewmodel.MainViewModel
import com.reloader.tragosapp.ui.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

/**
 * A simple [Fragment] subclass.
 */
class TragosDetalleFragment : Fragment() {

    private lateinit var drink: Drink

//    private val viewModel by viewModels<MainViewModel> {
//        VMFactory(RepoImpl(DataSource()))
//    }

    private val viewModel by activityViewModels<MainViewModel> {
        // usamos activityModels para usar la misma instancia siempre
        VMFactory(RepoImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            drink = it.getParcelable<Drink>("drink")!!
            Log.v("Detalles_frag", "$drink")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_tragos_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Glide.with(requireContext()).load(drink.imagen)
            .centerCrop()
            .into(img_trago)
        trago_title.text = drink.nombre
        trago_desc.text = drink.descripcion

        if (drink.hasAlcohol.equals(("Non_Alcoholic"))) {
            txt_has_alcohol.text = "Bebida sin alcohol"
        } else {
            txt_has_alcohol.text = "Bebida con alcohol"
        }


        btn_guardar_trago.setOnClickListener {

            viewModel.guardarTrago(
                DrinkEntity(
                    drink.tragoId,
                    drink.imagen,
                    drink.nombre,
                    drink.descripcion,
                    drink.hasAlcohol
                )
            )

            Toast.makeText(requireContext(), "Se guardo el trago a favoritos", Toast.LENGTH_SHORT)
                .show()
        }


    }
}
