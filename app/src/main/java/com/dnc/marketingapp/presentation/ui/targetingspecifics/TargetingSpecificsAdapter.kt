package com.dnc.marketingapp.presentation.ui.targetingspecifics

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.util.remove
import androidx.recyclerview.widget.RecyclerView
import com.dnc.marketingapp.R
import kotlinx.android.synthetic.main.targeting_specifics_item_view.view.*


class TargetingSpecificsAdapter: RecyclerView.Adapter<TargetingSpecificsAdapter.TargetingSpecificsHolder>(){
    private val selectedItems: SparseBooleanArray = SparseBooleanArray()

    var list = ArrayList<String>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TargetingSpecificsHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.targeting_specifics_item_view,
            parent,
            false
        )
        return TargetingSpecificsHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TargetingSpecificsHolder, position: Int)
            = holder.bind(list[position], position)

    inner class TargetingSpecificsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(title: String, position: Int){
            itemView.apply {
                target_title.text = title
                toggleItemViewSelection(itemView, position)

                targeting_specifics_item_view.setOnClickListener {
                    if (selectedItems.get(position, false)) {
                        selectedItems.remove(position, true)
                    } else {
                        selectedItems.put(position, true)
                    }
                    toggleItemViewSelection(itemView, position)
                }
            }
        }
    }

    fun toggleItemViewSelection(itemView: View, position: Int){
        with(itemView){
            if (selectedItems.get(position, false)) {
                target_title.setTextColor(ContextCompat.getColor(context, R.color.neonGreen))
                target_specific_image_view.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checkbox_selected))
            } else {
                target_title.setTextColor(ContextCompat.getColor(context, R.color.white))
                target_specific_image_view.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checkbox_not_selected))
            }
        }
    }

    fun getSelectedItems(): ArrayList<String> {
        val items = mutableListOf<String>()
        for (i in 0 until selectedItems.size()) {
            items.add(list[selectedItems.keyAt(i)])
        }
        return items as ArrayList<String>
    }

    fun selectedItemCount(): Int
        = selectedItems.size()
}