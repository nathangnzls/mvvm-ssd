package ph.com.southstardrug.www.southstardrug.adapter

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.renderscript.ScriptGroup

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO.Branches
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.ViewHolder.BranchViewHolder
import android.support.constraint.ConstraintSet
import android.support.constraint.ConstraintSet.BOTTOM
import android.support.constraint.ConstraintSet.TOP
import ph.com.southstardrug.www.southstardrug.databinding.FragmentBranchesBinding
import ph.com.southstardrug.www.southstardrug.module.home_module.BranchesFragment
import ph.com.southstardrug.www.southstardrug.viewmodels.BranchesFragmentViewModel

class BranchAdapter(internal var context: Context, internal var branchList: List<Branches>) : RecyclerView.Adapter<BranchViewHolder>() {
    lateinit var viewModel: BranchesFragmentViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        viewModel = BranchesFragmentViewModel()
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.child_branches_layout, parent, false)
        return BranchViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val a  = 0
        holder.branchName.text = branchList[position].branchName
        holder.branchStreet.text = branchList[position].branchAddress?.get(a)?.branchStreet
        holder.branchCity.text = branchList[position].branchAddress?.get(a)?.branchCity
        holder.branchZipCode.text = branchList[position].branchAddress?.get(a)?.branchZIPCode.toString()
        viewModel.setContacts(context, holder,position,branchList)
        holder.branchOpeningHours.text = (branchList[position].branchOperatingHours?.get(a)?.opening + " - " + branchList[position].branchOperatingHours?.get(a)?.closing)
    }
    override fun getItemCount(): Int {
        return branchList.size
    }

}