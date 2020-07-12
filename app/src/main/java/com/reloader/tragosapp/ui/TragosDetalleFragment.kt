package com.reloader.tragosapp.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.reloader.tragosapp.R
import com.reloader.tragosapp.data.model.Drink
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

/**
 * A simple [Fragment] subclass.
 */
class TragosDetalleFragment : Fragment() {

    private lateinit var drink: Drink

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


    }
}
