package com.dnc.marketingapp.presentation.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.dnc.marketingapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T: BaseViewModel>: Fragment() {
    protected lateinit var viewModel: T

    abstract fun initViewModel(): T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

        viewModel = initViewModel()
    }

    override fun onDestroyView() {
        viewModel.clearSession()
        super.onDestroyView()
    }

    override fun onDetach() {
        viewModel.clearSession()
        super.onDetach()
    }

    override fun onDestroy() {
        viewModel.clearSession()
        super.onDestroy()
    }

    fun doIfHasInternetConnectivity(doAfter: () -> Unit){
        viewModel.let {
            if (!it.hasInternetConnection) {
                MaterialAlertDialogBuilder(requireContext())
                        .setTitle(getString(R.string.no_internet))
                        .setMessage(getString(R.string.no_internet_message))
                        .setPositiveButton(android.R.string.ok, null)
                        .show()
            } else {
                doAfter.invoke()
            }
        }
    }
}