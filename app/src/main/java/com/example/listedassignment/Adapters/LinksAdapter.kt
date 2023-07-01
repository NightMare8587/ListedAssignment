package com.example.listedassignment.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listedassignment.Models.LinksModel
import com.example.listedassignment.Models.ListedModel
import com.example.listedassignment.Models.RecentLink
import com.example.listedassignment.Models.TopLink
import com.example.listedassignment.R
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class LinksAdapter : RecyclerView.Adapter<LinksAdapter.Holder>() {
    private var commonList: List<LinksModel> = emptyList()

    fun setModel(commonList: List<LinksModel>) {
        this.commonList = commonList
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.linksCardViewImage)
        val linkName : TextView = itemView.findViewById(R.id.linkNameCardView)
        val count : TextView = itemView.findViewById(R.id.totalClicksCardView)
        val date : TextView = itemView.findViewById(R.id.textView3)
        val link : TextView = itemView.findViewById(R.id.linkCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_linksback,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return commonList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Picasso.get().load(commonList[position].original_image).into(holder.image)
        holder.linkName.text = commonList[position].title
        holder.link.text = commonList[position].web_link
        holder.count.text = commonList[position].total_clicks.toString()
        val date = DateFormat.getDateInstance().format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(commonList[position].created_at)?.time)
        holder.date.text = date.toString()
    }
}