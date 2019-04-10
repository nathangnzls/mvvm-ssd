package ph.com.southstardrug.www.southstardrug.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import ph.com.southstardrug.www.southstardrug.R

class PromoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var androidLayout: View
    var promoThumbnail: ImageView
    var promoTitle: TextView
    var promoDescription: TextView
    var promoDuration: TextView
    var promoDatePosted: TextView
    init {
        androidLayout = itemView.findViewById(R.id.cardViewLayout)
        promoThumbnail = itemView.findViewById(R.id.imgThumbnail) as ImageView
        promoTitle = itemView.findViewById(R.id.txtTitle) as TextView
        promoDescription = itemView.findViewById(R.id.txtDesc) as TextView
        promoDuration = itemView.findViewById(R.id.txtPromoDuration) as TextView
        promoDatePosted = itemView.findViewById(R.id.txtPromoDatePosted) as TextView
    }
}
