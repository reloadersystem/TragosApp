package com.reloader.tragosapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.reloader.tragosapp.data.DataSource
import com.reloader.tragosapp.domain.RepoImpl
import com.reloader.tragosapp.ui.viewmodel.MainViewModel
import com.reloader.tragosapp.ui.viewmodel.VMFactory

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    // no se puede instanciar sino creas un factory

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(RepoImpl(DataSource()))
    } // inyeccion de  dependencias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
