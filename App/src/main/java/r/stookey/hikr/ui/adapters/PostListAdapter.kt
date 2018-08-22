package r.stookey.hikr.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_message.view.*
import r.stookey.hikr.R
import r.stookey.hikr.db.entity.PostEntity



/**
 * [RecyclerView.Adapter] that can display an Item and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */



class PostListAdapter: RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    var mValues: List<PostEntity> = emptyList()


    fun loadValues(items: List<PostEntity>){
        mValues = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind("test", "test")
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(title: String, date: String){
            mView.tvTitle.text = title
            mView.tvDate.text = date
        }

    }
}
