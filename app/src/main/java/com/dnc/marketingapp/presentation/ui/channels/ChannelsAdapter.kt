package com.dnc.marketingapp.presentation.ui.channels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.marketingapp.R
import kotlinx.android.synthetic.main.channels_item_view.view.*

class ChannelsAdapter: RecyclerView.Adapter<ChannelsAdapter.ChannelsHolder>() {

    private var onChannelClickListener: OnChannelClickListener? = null

    var list = ArrayList<String>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelsHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.channels_item_view,
            parent,
            false
        )
        return ChannelsHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChannelsHolder, position: Int) =
        holder.bind(list[position])

    inner class ChannelsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(channel: String) {
            itemView.apply {
                channel_title.text = channel

                setOnClickListener {
                    onChannelClickListener?.onClick(channel)
                }
            }
        }
    }

    interface OnChannelClickListener{
        fun onClick(channel:String)
    }

    fun setOnChannelClickListener(listener: OnChannelClickListener){
        onChannelClickListener = listener
    }
}