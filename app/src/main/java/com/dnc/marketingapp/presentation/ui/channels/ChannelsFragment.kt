package com.dnc.marketingapp.presentation.ui.channels

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
import com.dnc.marketingapp.presentation.ui.campaigns.CampaignsDetailsFragment
import kotlinx.android.synthetic.main.channels_fragment.*
import kotlinx.android.synthetic.main.channels_fragment.screen_counter
import javax.inject.Inject

class ChannelsFragment : BaseFragment<ChannelsViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun initViewModel(): ChannelsViewModel= viewModelFactory.obtainViewModel(this)
    private val channelsAdapter = ChannelsAdapter()
    private var targetingSpecificsList: ArrayList<String>? = null

    companion object{
        const val KEY_TARGETING_SPECIFICS_TYPE = "selection"

        fun newInstance(selectionList: ArrayList<String>) =
            ChannelsFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(KEY_TARGETING_SPECIFICS_TYPE, selectionList)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        targetingSpecificsList = arguments?.getStringArrayList(KEY_TARGETING_SPECIFICS_TYPE)
        return inflater.inflate(R.layout.channels_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        viewModel.channelsLiveData.observe(viewLifecycleOwner, {
            channels_progress.visibility = View.GONE
            channelsAdapter.list = ArrayList(it)
        })

        targetingSpecificsList?.let { viewModel.fetchFilteredChannels(it) }

    }

    private fun initViews(){
        screen_counter.apply {
            firstImageDrawable = ScreenSelectedState.ChannelsScreenSelected().firstImageResourceId
            secondImageDrawable = ScreenSelectedState.ChannelsScreenSelected().secondImageResourceId
        }

        channelsAdapter.setOnChannelClickListener(object : ChannelsAdapter.OnChannelClickListener{
            override fun onClick(channel: String) {
                val bundle = bundleOf(CampaignsDetailsFragment.KEY_CHANNEL_NAME to channel)
                findNavController().navigate(R.id.action_channels_to_campaigns, bundle, null, null)
            }
        })

        channels_recycler_view.apply {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(requireContext(), R.drawable.list_line_divider)?.let { dividerItemDecoration.setDrawable(it) }

            addItemDecoration(dividerItemDecoration)
            adapter = channelsAdapter
        }
    }

}