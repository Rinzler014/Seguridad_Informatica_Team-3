package com.example.menu_desplegable

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class configuracion_perfil : Fragment() {

    companion object {
        fun newInstance() = configuracion_perfil()
    }

    private lateinit var viewModel: ConfiguracionPerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_configuracion_perfil, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfiguracionPerfilViewModel::class.java)
        // TODO: Use the ViewModel
    }

}