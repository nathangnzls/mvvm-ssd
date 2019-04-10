package ph.com.southstardrug.www.southstardrug.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.constraint.ConstraintSet
import android.view.View
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO.BranchPOJO
import ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO.Branches
import ph.com.southstardrug.www.southstardrug.network.APIClient
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.ViewHolder.BranchViewHolder
import ph.com.southstardrug.www.southstardrug.adapter.BranchAdapter
import ph.com.southstardrug.www.southstardrug.databinding.FragmentBranchesBinding
import ph.com.southstardrug.www.southstardrug.util.dissmissProgressDialog
import ph.com.southstardrug.www.southstardrug.util.showProgressDialog

class BranchesFragmentViewModel: ViewModel {

    var resultdata = MutableLiveData<BranchPOJO>()
//    private var mBranchesFragmentBinding : FragmentBranchesBinding? = null
    constructor():super()


    fun getBranches(ctx: Context): MutableLiveData<BranchPOJO>{
        showProgressDialog(ctx.getString(R.string.fetching_branches), ctx)
        Fuel.request(APIClient.getBranches())
            .responseObject(BranchPOJO.Deserializer()){
                request, response, result ->
                when (response.statusCode) {
                    200 -> {
                        result.fold(success = {branchpj ->
//                            branchList?.addAll((branchpj.branches)!!.toList())
                            dissmissProgressDialog()
                            resultdata.value = branchpj
                        }, failure = {error ->
                            dissmissProgressDialog()
                            Toast.makeText(ctx, ctx.getString(R.string.fetch_data_failed), Toast.LENGTH_LONG).show()
                        } )
                    }
                }

            }
        return resultdata
    }
    fun loadMoreBranches(url: String, ctx: Context) : MutableLiveData<BranchPOJO> {
            if (!url.isEmpty()) {
                Fuel.get(url)
                    .responseObject(BranchPOJO.Deserializer()){request, response, result ->
                        result.fold(success = {branchpj ->


                            resultdata.value = branchpj
                            dissmissProgressDialog()

                        }, failure = { error ->

                            Toast.makeText(ctx,ctx.getString(R.string.fetch_data_failed), Toast.LENGTH_LONG).show()
                            dissmissProgressDialog()
                        })
                    }
            }else{
                Toast.makeText(ctx,ctx.getString(R.string.fetching_branches_complete), Toast.LENGTH_LONG).show()



            }
    return resultdata
    }
    override fun onCleared() {
        super.onCleared()
//        subscription.dispose()
    }

    fun setContacts(ctx: Context, holder: BranchViewHolder, position: Int, branchList: List<Branches>) {
        val set = ConstraintSet()
        set.clone(holder.branchConstraintLayout);
        val a  = 0
        when {
            branchList[position].branchContact?.size == 1 -> {
                when {
                    !branchList[position].branchContact?.get(a)?.phone.isNullOrEmpty() ->
                    {
                        holder.branchPhoneContact.text = (branchList[position].branchContact?.get(a)?.phone)
                        set.connect(holder.openinghoursIcon.id, ConstraintSet.TOP, holder.phoneIcon.id, ConstraintSet.BOTTOM, 4)
                        set.connect(holder.branchOpeningHours.id, ConstraintSet.TOP, holder.branchPhoneContact.id, ConstraintSet.BOTTOM, 16)
                        set.applyTo(holder.branchConstraintLayout)
                        holder.phoneIcon.visibility = View.VISIBLE
                        holder.branchPhoneContact.visibility = View.VISIBLE
                        holder.mobileIcon.visibility = View.GONE
                        holder.branchMobileContact.visibility = View.GONE

                    }
                    !branchList[position].branchContact?.get(a)?.mobile.isNullOrEmpty() ->
                    {
                        holder.branchMobileContact.text = (branchList[position].branchContact?.get(a)?.mobile)
                        set.connect(holder.openinghoursIcon.id, ConstraintSet.TOP, holder.mobileIcon.id, ConstraintSet.BOTTOM, 4)
                        set.connect(holder.branchOpeningHours.id, ConstraintSet.TOP, holder.branchMobileContact.id, ConstraintSet.BOTTOM, 16)
                        set.applyTo(holder.branchConstraintLayout)
                        holder.branchMobileContact.visibility = View.VISIBLE
                        holder.mobileIcon.visibility = View.VISIBLE
                        holder.phoneIcon.visibility = View.GONE
                        holder.branchPhoneContact.visibility = View.GONE
                    }
                }
            }
            branchList[position].branchContact?.size == 2 -> {
                when {
                    !branchList[position].branchContact?.get(a)?.phone.isNullOrEmpty() && !branchList[position].branchContact?.get(a+1)?.mobile.isNullOrEmpty() ->
                    {
                        holder.branchPhoneContact.text = branchList[position].branchContact?.get(a)?.phone
                        holder.branchMobileContact.text = branchList[position].branchContact?.get(a+1)?.mobile
                        set.connect(holder.openinghoursIcon.id, ConstraintSet.TOP, holder.mobileIcon.id, ConstraintSet.BOTTOM, 4)
                        set.connect(holder.branchOpeningHours.id, ConstraintSet.TOP, holder.branchMobileContact.id, ConstraintSet.BOTTOM, 16)
                        set.applyTo(holder.branchConstraintLayout)
                        holder.phoneIcon.visibility = View.VISIBLE
                        holder.branchPhoneContact.visibility = View.VISIBLE
                        holder.branchMobileContact.visibility = View.VISIBLE
                        holder.mobileIcon.visibility = View.VISIBLE

                    }
                    !branchList[position].branchContact?.get(a)?.mobile.isNullOrEmpty() && !branchList[position].branchContact?.get(a+1)?.phone.isNullOrEmpty() ->
                    {
                        holder.branchPhoneContact.text = branchList[position].branchContact?.get(a)?.mobile
                        holder.branchMobileContact.text = branchList[position].branchContact?.get(a+1)?.phone
                        set.connect(holder.openinghoursIcon.id, ConstraintSet.TOP, holder.mobileIcon.id, ConstraintSet.BOTTOM, 4)
                        set.connect(holder.branchOpeningHours.id, ConstraintSet.TOP, holder.branchMobileContact.id, ConstraintSet.BOTTOM, 16)
                        set.applyTo(holder.branchConstraintLayout)
                        holder.branchMobileContact.visibility = View.VISIBLE
                        holder.mobileIcon.visibility = View.VISIBLE
                        holder.phoneIcon.visibility = View.VISIBLE
                        holder.branchPhoneContact.visibility = View.VISIBLE
                    }

                    !branchList[position].branchContact?.get(a)?.phone.isNullOrEmpty()->
                    {
                        holder.branchPhoneContact.text = (branchList[position].branchContact?.get(a)?.phone + ", " + branchList[position].branchContact?.get(a+1)?.phone)
                        set.connect(holder.openinghoursIcon.id, ConstraintSet.TOP, holder.phoneIcon.id, ConstraintSet.BOTTOM, 4)
                        set.connect(holder.branchOpeningHours.id, ConstraintSet.TOP, holder.branchPhoneContact.id, ConstraintSet.BOTTOM, 16)
                        set.applyTo(holder.branchConstraintLayout)
                        holder.branchPhoneContact.visibility = View.VISIBLE
                        holder.phoneIcon.visibility = View.VISIBLE
                        holder.mobileIcon.visibility = View.GONE
                        holder.branchMobileContact.visibility= View.GONE
                    }

                    !branchList[position].branchContact?.get(a)?.mobile.isNullOrEmpty() ->
                    {
                        holder.branchMobileContact.text = (branchList[position].branchContact?.get(a)?.mobile + ", " + branchList[position].branchContact?.get(a+1)?.mobile)
                        set.connect(holder.openinghoursIcon.id, ConstraintSet.TOP, holder.mobileIcon.id, ConstraintSet.BOTTOM, 4)
                        set.connect(holder.branchOpeningHours.id, ConstraintSet.TOP, holder.branchMobileContact.id, ConstraintSet.BOTTOM, 16)
                        set.applyTo(holder.branchConstraintLayout)
                        holder.branchMobileContact.visibility = View.VISIBLE
                        holder.mobileIcon.visibility = View.VISIBLE
                        holder.phoneIcon.visibility = View.GONE
                        holder.branchPhoneContact.visibility = View.GONE
                    }

                }
            }
        }
    }


}