package r.stookey.hikr.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import r.stookey.hikr.R
import r.stookey.hikr.ui.adapters.PostListAdapter
import r.stookey.hikr.viewmodel.ListViewModel
import r.stookey.hikr.viewmodel.ViewModelFactory

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PostListFragment.OnListFragmentInteractionListener] interface.
 */
class PostListFragment : Fragment() {

    private var TAG = "PostListFragment"
    private lateinit var viewModel: ListViewModel
    private lateinit var mUserID: String

    companion object {
        fun newInstance(userID: String) = PostListFragment().apply {
            arguments = Bundle().apply {
                putString("userID", userID)
            }
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_message_list, container, false)
        val adapter = PostListAdapter()

        arguments?.getString("userID")?.let {
            mUserID = it
        }
        //Factory should be injected
        val factory = ViewModelFactory(mUserID)
        viewModel = ViewModelProviders.of(activity!!, factory).get(ListViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        subscribetoUI(adapter)
        setHasOptionsMenu(true)

        //TOOL Bar Stuff

        Log.d(TAG, "onCreateView: PostListFragment view inflated")
        return view
    }

    private fun subscribetoUI(adapter: PostListAdapter){
        viewModel.getAllPostsByUserID().observe(this, Observer {
            if (it != null) {
                adapter.loadValues(it)
            }
        })
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }


}
