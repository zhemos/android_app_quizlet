package by.zm.quizlet.feature.modules

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.zm.quizlet.core.ui.components.BaseFragment
import by.zm.quizlet.feature.modules.databinding.FragmentModulesBinding

interface IModulesFragment

class ModulesFragment : BaseFragment<FragmentModulesBinding, ModulesNavigation>() {

    private lateinit var viewModel: ModulesViewModel

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentModulesBinding.inflate(inflater, container, false)

    override fun onViewCreated() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ModulesViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("zm1996", viewModel.test())
    }
}