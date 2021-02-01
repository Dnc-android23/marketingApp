package com.dnc.marketingapp.presentation.ui.targetingspecifics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.dnc.marketingapp.R
import com.dnc.marketingapp.data.di.utils.obtainViewModel
import com.dnc.marketingapp.data.model.ScreenSelectedState
import com.dnc.marketingapp.presentation.ui.base.BaseFragment
import com.dnc.marketingapp.presentation.ui.channels.ChannelsFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.targeting_specifics_fragment.*
import javax.inject.Inject

class TargetingSpecificsFragment : BaseFragment<TargetingSpecificsViewModel>(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun initViewModel(): TargetingSpecificsViewModel = viewModelFactory.obtainViewModel(this)

    private val targetingSpecificsAdapter = TargetingSpecificsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =  inflater.inflate(R.layout.targeting_specifics_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        viewModel.targetingSpecificsLiveData.observe(viewLifecycleOwner, {
            targeting_specifics_progress.visibility = View.GONE
            if (it.isNotEmpty()) {
                targetingSpecificsAdapter.list = it as ArrayList<String>
                empty_list_layout.visibility = View.GONE
            } else {
                empty_list_layout.visibility = View.VISIBLE
            }
        })

        viewModel.serverErrorLiveData.observe(viewLifecycleOwner, {
            targeting_specifics_progress.visibility = View.GONE
            empty_list_layout.visibility = View.VISIBLE
        })

        doIfHasInternetConnectivity { viewModel.fetchTargetingSpecifics() }
    }

    private fun initViews(){
        screen_counter.firstImageDrawable = ScreenSelectedState.TargetingSpecificScreenSelected().firstImageResourceId

        targeting_specifics_recycler_view.apply {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(requireContext(), R.drawable.list_line_divider)?.let { dividerItemDecoration.setDrawable(it) }

            addItemDecoration(dividerItemDecoration)
            adapter = targetingSpecificsAdapter
        }

        continue_button.setOnClickListener{
            if(targetingSpecificsAdapter.selectedItemCount() > 0){
                val bundle = bundleOf(ChannelsFragment.KEY_TARGETING_SPECIFICS_TYPE to targetingSpecificsAdapter.getSelectedItems())
                findNavController().navigate(R.id.action_targetingSpecifics_to_channels, bundle, null, null)
            }else{
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.targeting_specifics_error_title))
                    .setMessage(getString(R.string.targeting_specifics_error_message))
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
            }
        }
    }

}