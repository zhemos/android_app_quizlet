package by.zm.quizlet.feature.modules

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

class ModulesFragment : Fragment(R.layout.fragment_modules) {

    val vm: ModulesViewModel by viewModels<ModulesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm = ViewModelProvider(requireActivity()).get(ModulesViewModel::class.java)
        Log.d("zm1996", vm.test())
    }
}