package ph.com.southstardrug.www.southstardrug.module.home_module


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.adapter.BranchAdapter
import ph.com.southstardrug.www.southstardrug.network.APIClient
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO.*
import ph.com.southstardrug.www.southstardrug.databinding.FragmentBranchesBinding
import ph.com.southstardrug.www.southstardrug.util.EndlessScrollListener
import ph.com.southstardrug.www.southstardrug.util.dismissProgressDialog
import ph.com.southstardrug.www.southstardrug.util.showProgressDialog
import ph.com.southstardrug.www.southstardrug.viewmodels.BranchesFragmentViewModel
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener



class BranchesFragment: Fragment(){
    private var mBranchesFragmentBinding : FragmentBranchesBinding? = null
    lateinit var viewModel: BranchesFragmentViewModel
    var setNextBranchesUrl: String = ""
    var mLayoutManager: LinearLayoutManager? = null
    var branchesList: MutableList<Branches> = mutableListOf()
    lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBranchesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_branches, container, false)
        val view = mBranchesFragmentBinding!!.getRoot()
        viewModel = ViewModelProviders.of(this).get(BranchesFragmentViewModel::class.java)
        viewModel.getBranches(activity!!).observe( this, Observer { branchesViewModel ->
            scrollListener.notifyMorePages()
            setNextBranchesUrl = branchesViewModel!!.next!!
            branchesList.removeAll(branchesViewModel.branches!!)
            branchesList.addAll(branchesViewModel.branches!!)
            mLayoutManager = LinearLayoutManager(activity!!.applicationContext)
            mBranchesFragmentBinding!!.branchesRecycleView!!.layoutManager = mLayoutManager as RecyclerView.LayoutManager
            mBranchesFragmentBinding!!.branchesRecycleView.adapter = BranchAdapter(context!!,branchesList)
            mBranchesFragmentBinding!!.branchesRecycleView.adapter!!.notifyDataSetChanged()
            mBranchesFragmentBinding!!.branchesRecycleView.adapter!!.notifyItemRangeChanged(0, mBranchesFragmentBinding!!.branchesRecycleView.adapter!!.getItemCount())

        })
        mBranchesFragmentBinding!!.branchesRecycleView.addOnScrollListener(scrollListener)
        return view
    }
    private val scrollListener = EndlessScrollListener(object : EndlessScrollListener.RefreshList {
        override fun onRefresh(pageNumber: Int) {
            mBranchesFragmentBinding!!.itemProgressBar.setVisibility(View.VISIBLE)
            viewModel.loadMoreBranches(setNextBranchesUrl,activity!!).observe(activity!!, Observer { branchViewModel ->
                var count: Int = branchesList.size
                mBranchesFragmentBinding!!.branchesRecycleView.scrollToPosition(count-(count-1))

            })
        }
    })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dismissProgressDialog()
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        dismissProgressDialog()
        mContext = context!!
    }
}




