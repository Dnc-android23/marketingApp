package com.dnc.marketingapp.presentation.ui.campaigns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.core.marketingapp.model.Campaign
import com.dnc.marketingapp.R
import kotlinx.android.synthetic.main.campaign_item_view.view.*

class CampaignsAdapter: RecyclerView.Adapter<CampaignsAdapter.CampaignsHolder>() {

    private var onCampaignClickListener: OnCampaignClickListener? = null

    var list = ArrayList<Campaign>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignsHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.campaign_item_view,
            parent,
            false
        )
        return CampaignsHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CampaignsHolder, position: Int) =
        holder.bind(list[position])

    inner class CampaignsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(campaign: Campaign) {
            itemView.apply {
                campaign_price_text.text = campaign.getPrice()
                options_text.text = campaign.getCampaignOptions()
                optimization_no_text.text = (campaign.optimizationNo ?: 0).toString()
                listing_text.text = campaign.getListings()

                select_campaign.setOnClickListener {
                    onCampaignClickListener?.onClick(campaign)
                }

            }
        }
    }

    interface OnCampaignClickListener{
        fun onClick(campaign: Campaign)
    }

    fun setOnCampaignClickListener(listener: OnCampaignClickListener){
        onCampaignClickListener = listener
    }
}