package com.example.android_projekt.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_projekt.databinding.CustomListBinding
import com.example.android_projekt.model.Restaurants


class PhotoGridAdapter(private val onClickListener: OnClickListener):
    ListAdapter<Restaurants, PhotoGridAdapter.RestaurantsViewHolder>(DiffCallback) {

    class RestaurantsViewHolder(private var binding: CustomListBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rest: Restaurants) {
            binding.property = rest
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Restaurants>() {
        override fun areItemsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RestaurantsViewHolder {
        return RestaurantsViewHolder(CustomListBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val rest = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(rest)
        }
        holder.bind(rest)
    }

    class OnClickListener(val clickListener: (rest: Restaurants) -> Unit) {
        fun onClick(rest:Restaurants) = clickListener(rest)
    }
}