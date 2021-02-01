package com.dnc.marketingapp.presentation.ui.campaigns

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dnc.core.marketingapp.model.Campaign
import com.dnc.marketingapp.R
import com.dnc.marketingapp.data.di.utils.obtainViewModel
import com.dnc.marketingapp.data.model.ScreenSelectedState
import com.dnc.marketingapp.presentation.ui.base.BaseFragment
import com.dnc.marketingapp.presentation.ui.channels.ChannelsFragment
import com.dnc.marketingapp.presentation.ui.reviewselection.ReviewCampaignFragment
import kotlinx.android.synthetic.main.campaign_details_fragment.*
import kotlinx.android.synthetic.main.campaign_details_fragment.screen_counter
import javax.inject.Inject

class CampaignsDetailsFragment : BaseFragment<CampaignDetailsViewModel>(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun initViewModel(): CampaignDetailsViewModel = viewModelFactory.obtainViewModel(this)
    private var channelName :String ? = null
    private val campaignAdapter = CampaignsAdapter()

    companion object{
        const val KEY_CHANNEL_NAME = "channel_name"

        fun newInstance(channel: String) =
            ChannelsFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_CHANNEL_NAME, channel)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        channelName = arguments?.getString(KEY_CHANNEL_NAME)
        return inflater.inflate(R.layout.campaign_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        viewModel.campaignDetailsLiveData.observe(viewLifecycleOwner, {
            campaigns_progress.visibility = View.GONE
            campaignAdapter.list = ArrayList(it)
        })

        viewModel.serverErrorLiveData.observe(viewLifecycleOwner, {
            handleError()
        })

        channelName?.let { viewModel.fetchCampaignDetails(it) }

    }

    private fun handleError(){
        campaigns_progress.visibility = View.GONE
        with(AlertDialog.Builder(context)) {
            setTitle(getString(R.string.action_failed_title))
            setMessage(R.string.campaigns_details_failed_message)
            setCancelable(false)
            setPositiveButton(android.R.string.ok) { dialog, _ ->
                channelName?.let { viewModel.fetchCampaignDetails(it) }
                dialog.dismiss()
            }

            show()
        }
    }

    private fun initViews(){
        screen_counter.apply {
            firstImageDrawable = ScreenSelectedState.CampaignsScreenSelected().firstImageResourceId
            secondImageDrawable = ScreenSelectedState.CampaignsScreenSelected().secondImageResourceId
            thirdImageDrawable = ScreenSelectedState.CampaignsScreenSelected().thirdImageResourceId
        }

        campaignAdapter.setOnCampaignClickListener(object : CampaignsAdapter.OnCampaignClickListener {
            override fun onClick(campaign: Campaign) {
                val bundle = bundleOf(ReviewCampaignFragment.KEY_CAMPAIGN_DETAILS to campaign,
                    ReviewCampaignFragment.KEY_CAMPAIGN_TITLE to channelName)
                findNavController().navigate(R.id.action_campaigns_to_reviewSelection, bundle, null, null)
            }

        })
        campaigns_recycler_view.adapter = campaignAdapter
        channel_name.text = channelName
    }

}