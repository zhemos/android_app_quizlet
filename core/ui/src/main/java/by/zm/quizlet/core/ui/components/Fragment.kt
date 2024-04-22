package by.zm.quizlet.core.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding, N : Navigation> : Fragment() {

    abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): B

    abstract fun onViewCreated()

    private var _binding: B? = null
    val binding: B get() = _binding ?: error("binding exception")

    private var _navigation: N? = null
    val navigation: N get() = _navigation ?: error("navigation exception")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated()
    }

    override fun onStop() {
        _navigation = null
        super.onStop()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}