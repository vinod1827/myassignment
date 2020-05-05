package com.vinu.vinodassigment.ui.adapters

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.vinu.vinodassigment.R
import com.vinu.vinodassigment.models.ResponseModel
import kotlinx.android.synthetic.main.single_item_layout.view.*

class ResponseRecyclerViewAdapter(
    private var newsList: ArrayList<ResponseModel>
) :
    RecyclerView.Adapter<ResponseRecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = newsList.size

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = newsList[position]
        holder.itemView.titleTextView.text = item.title?.label
        loadImage(holder, item)
    }

    private fun loadImage(holder: MyViewHolder, item: ResponseModel) {

        if (newsList.size > 0) {

            Glide.with(holder.itemView.context)
                .load(item.images?.get(2)?.label)
                .placeholder(R.drawable.ic_placeholder)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.itemView.imageView.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.itemView.imageView.visibility = View.VISIBLE
                        return false
                    }

                })
                .into(holder.itemView.imageView)
        } else {
            Glide.with(holder.itemView.context).clear(holder.itemView.imageView)
            holder.itemView.imageView.visibility = View.GONE
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}