package com.dnc.marketingapp.presentation.ui.reviewselection

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dnc.core.marketingapp.model.Campaign
import com.dnc.marketingapp.BuildConfig
import com.dnc.marketingapp.R
import com.dnc.marketingapp.data.di.utils.obtainViewModel
import com.dnc.marketingapp.data.model.ScreenSelectedState
import com.dnc.marketingapp.presentation.ui.base.BaseFragment
import com.dnc.marketingapp.presentation.ui.channels.ChannelsFragment
import kotlinx.android.synthetic.main.review_campaign_fragment.*
import kotlinx.android.synthetic.main.review_campaign_fragment.screen_counter
import javax.inject.Inject

class ReviewCampaignFragment : BaseFragment<ReviewCampaignViewModel>(), View.OnClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun initViewModel(): ReviewCampaignViewModel = viewModelFactory.obtainViewModel(this)

    private var selectedCampaignDetails: Campaign? = null
    private var selectedCampaignName: String? = null

    companion object {
        const val KEY_CAMPAIGN_DETAILS = "campaign_details"
        const val KEY_CAMPAIGN_TITLE = "campaign_name"

        fun newInstance(campaignName: String, campaignDetails: Campaign) =
                ChannelsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(KEY_CAMPAIGN_DETAILS, campaignDetails)
                        putString(KEY_CAMPAIGN_TITLE, campaignName)
                    }
                }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        selectedCampaignDetails = arguments?.getSerializable(KEY_CAMPAIGN_DETAILS) as Campaign?
        selectedCampaignName = arguments?.getString(KEY_CAMPAIGN_TITLE)
        return inflater.inflate(R.layout.review_campaign_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        screen_counter.apply {
            firstImageDrawable = ScreenSelectedState.ReviewCampaignScreenSelected().firstImageResourceId
            secondImageDrawable = ScreenSelectedState.ReviewCampaignScreenSelected().secondImageResourceId
            thirdImageDrawable = ScreenSelectedState.ReviewCampaignScreenSelected().thirdImageResourceId
            forthImageDrawable = ScreenSelectedState.ReviewCampaignScreenSelected().forthImageResourceId
        }

        campaign_title.text = getString(R.string.campaign, selectedCampaignName)
        campaign_details.text = selectedCampaignDetails?.getCampaignOptions()
        optimization_no_text.text = (selectedCampaignDetails?.optimizationNo ?: 0).toString()
        listing_text.text = selectedCampaignDetails?.getListings()
        price_text.text = selectedCampaignDetails?.getPrice()


        send_campaign_button.setOnClickListener(this)
        close_campaign_review.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.send_campaign_button -> handleEmailAction()
            R.id.close_campaign_review -> findNavController().popBackStack(R.id.targetingSpecifics, false)
        }
    }

    private fun handleEmailAction() {
        val emailAppIntent = Intent(Intent.ACTION_SENDTO)

        emailAppIntent.apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(BuildConfig.EMAIL_ADDRESS))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject, selectedCampaignName))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body, selectedCampaignDetails?.getCampaignOptions(), selectedCampaignDetails?.getPrice()))
        }

        try {
            startActivity(Intent.createChooser(emailAppIntent, getString(R.string.email_chooser)))
        } catch (ex: ActivityNotFoundException) {
            with(AlertDialog.Builder(context)) {
                setTitle(getString(R.string.action_failed_title))
                setMessage(R.string.email_failed_message)
                setCancelable(false)
                setPositiveButton(android.R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }

                show()
            }
        }
    }

}