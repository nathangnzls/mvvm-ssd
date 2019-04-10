package ph.com.southstardrug.www.southstardrug.ViewHolder

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import ph.com.southstardrug.www.southstardrug.R

class BranchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var branchConstraintLayout: ConstraintLayout
    var branchCardView: View
    var branchName: TextView
    var branchStreet: TextView
    var branchCity: TextView
    var branchZipCode: TextView
    var branchMobileContact : TextView
    var mobileIcon: ImageView
    var branchPhoneContact: TextView
    var phoneIcon: ImageView
    var branchOpeningHours : TextView
    var openinghoursIcon: ImageView



    init {
        branchConstraintLayout = itemView.findViewById(R.id.branchConstraintLayout)
        branchCardView = itemView.findViewById(R.id.branchCardViewLayout)
        branchName = itemView.findViewById(R.id.storeName)
        branchStreet = itemView.findViewById(R.id.storeStreet)
        branchCity = itemView.findViewById(R.id.storeCity)
        branchZipCode = itemView.findViewById(R.id.storeZipCode)
        branchMobileContact = itemView.findViewById(R.id.storeMobileContact)
        mobileIcon = itemView.findViewById(R.id.mobileIcon)
        branchPhoneContact = itemView.findViewById(R.id.storePhoneContact)
        phoneIcon = itemView.findViewById(R.id.phoneIcon)
        branchOpeningHours = itemView.findViewById(R.id.storeOperatingHours)
        openinghoursIcon = itemView.findViewById(R.id.operatinghoursIcon)
    }
}