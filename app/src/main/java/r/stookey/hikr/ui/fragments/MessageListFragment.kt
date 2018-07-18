package r.stookey.hikr.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import r.stookey.hikr.viewmodel.PostViewModel
import r.stookey.hikr.viewmodel.UserViewModel
import r.stookey.hikr.R
import r.stookey.hikr.Repo
import r.stookey.hikr.di.DaggerHikrComponent
import r.stookey.hikr.di.modules.StorageModule

import r.stookey.hikr.model.Post
import r.stookey.hikr.ui.adapters.MyMessageListRecyclerViewAdapter
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MessageListFragment.OnListFragmentInteractionListener] interface.
 */
class MessageListFragment : Fragment() {

    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null

    //May not be needed since the PostViewModel already has the UserID
    private lateinit var mUserID: Any

    @Inject lateinit var repo: Repo

    private val userViewModel by lazy {
        ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
    }
    private val postViewModel by lazy {
        ViewModelProviders.of(activity!!).get(PostViewModel::class.java)
    }



    companion object {
        fun newInstance(userID: String): MessageListFragment {
            val messageListFragment = MessageListFragment()
            var args = Bundle()
            args.putString("createdBy", userID)
            messageListFragment.arguments = args
            return messageListFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments!=null){
            if(arguments!!.containsKey("userID")){
                mUserID = arguments!!["userID"]
            }
        }

        DaggerHikrComponent.builder()
                .storageModule(StorageModule().storageModule(activity!!.application))
                .build()




    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_message_list, container, false)

        val listAdapterObserver:Observer<List<Post>> = Observer {
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    //When the data changes in all post list, data is added to the list
                    adapter = MyMessageListRecyclerViewAdapter(it!!, listener)
                }
            }
        }
        //TODO Call the postViewModel.getAllPostsByUserId function
        userViewModel.getAllPostsByUserID().observe(this, listAdapterObserver)



        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Post?)

    }
}
