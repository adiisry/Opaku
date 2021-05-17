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
import com.aditech.opaku.model.Produk
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_banner.view.*
import java.text.NumberFormat
import java.util.*

class GridAdapter(private var data: List<Produk>,
                  private val listener: (Produk) -> Unit)
    : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_produk, parent, false)
        return ViewHolder (inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view : View,) : RecyclerView.ViewHolder(view){

        private val ivProduk: ImageView = view.findViewById(R.id.iv_produk)
        private val tvJudulProduk: TextView = view.findViewById(R.id.tv_judul_produk)
        private val tvHarga: TextView = view.findViewById(R.id.tv_harga)

        fun bindItem(data:Produk, listener: (Produk) -> Unit, context: Context){

            val localID = Locale("in", "ID")
            val format = NumberFormat.getCurrencyInstance(localID)
            tvHarga.setText(format.format(data.harga!!.toDouble()))

            tvJudulProduk.setText(data.judul)

            Glide.with(context)
                .load(data.foto)
                .into(ivProduk)
            
            itemView.setOnClickListener {
                listener(data)
            }

        }

    }

}
