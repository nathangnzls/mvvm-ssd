package ph.com.southstardrug.www.southstardrug.adapter

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import ph.com.southstardrug.www.southstardrug.Model.PromoPOJO.Promo
import ph.com.southstardrug.www.southstardrug.Model.PromoPOJO.PromoPOJO
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.ViewHolder.PromoViewHolder
import java.text.SimpleDateFormat
import java.util.*


class PromoAdapter(internal var context: Context, internal var androidList: List<Promo>) : RecyclerView.Adapter<PromoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.child_promo_layout, parent, false)
        return PromoViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        Picasso.with(context).load(androidList[position].imageUrl).into(holder.promoThumbnail)
        holder.promoTitle.text = androidList[position].title
        holder.promoDescription.text = androidList[position].desc
        holder.promoDuration.text = androidList[position].due
        holder.promoDatePosted.text = getDateTime(androidList[position].datePosted.toString())

        //holder.androidLayout.setOnClickListener { Toast.makeText(context, holder.name.text, Toast.LENGTH_SHORT).show() }
    }
    override fun getItemCount(): Int {
        return androidList.size
    }
    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("MMMM dd, yyyy")
            val netDate = Date(s.toLong())
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}