package com.aditech.opaku.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditech.opaku.R
import com.aditech.opaku.databinding.FragmentHomeBinding
import com.aditech.opaku.home.HomeFragment
import com.aditech.opaku.model.Banner
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_banner.view.*

class BannerAdapter(private var data: List<Banner>,
                    private val listener: (Banner) -> Unit)
    : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_banner, parent, false)
        return ViewHolder (inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view : View,) : RecyclerView.ViewHolder(view){

        private val ivBanner: ImageView = view.findViewById(R.id.iv_poster_banner)

        fun bindItem(data:Banner, listener: (Banner) -> Unit, context: Context){

            Glide.with(context)
                .load(data.poster)
                .into(ivBanner)
            
            itemView.setOnClickListener {
                listener(data)
            }

        }

    }

}
