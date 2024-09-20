package com.tw.expandablerecviewdemo.adapters


import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tw.expandablerecviewdemo.R
import com.tw.expandablerecviewdemo.model.ExpandableModel
import com.tw.expandablerecviewdemo.model.MembersTable


class HeaderAdapter(private val mList: List<ExpandableModel>, activity: Activity, itemClickListener: Activity) : RecyclerView.Adapter<HeaderAdapter.ItemViewHolder>() {

    var mListener: OnItemClickListener
    lateinit var context: Context
    private var list: List<MembersTable> = ArrayList()
    var mListt: List<ExpandableModel> = ArrayList()


    interface OnItemClickListener {
        fun onFamilyClicked(position: Int, data: ExpandableModel)
        fun onFamilyClicked(position: Int, data: ExpandableModel, viewResp:Int)
    }

    //This function call first and initialize all data
    init {
        this.context = activity
        this.mListt = mList
        this.mListener = itemClickListener as OnItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = mListt[holder.adapterPosition]
        Log.e(this.javaClass.simpleName, "onBindViewHolder: $model")

        holder.tvName.text = model.building.name

        val isExpandable = model.isExpandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        if (isExpandable) {
            holder.mArrowImage.setImageResource(R.drawable.arrow_up)
        } else {
            holder.mArrowImage.setImageResource(R.drawable.arrow_down)
        }
        list = model.nestedList
        val adapter = NestedAdapter(list, context as Activity) //This Nested Adapter is used to display members list inside Header(parent)
        holder.nestedRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.nestedRecyclerView.setHasFixedSize(true)
        holder.nestedRecyclerView.adapter = adapter

        holder.llParent.setOnClickListener {
            model.isExpandable = !model.isExpandable
            list = model.nestedList
            notifyDataSetChanged()
//            notifyItemChanged(holder.adapterPosition)
        }

        holder.tvHeader1.setOnClickListener { v: View? ->
            mListener.onFamilyClicked(holder.adapterPosition, mList[holder.adapterPosition])
        }

        holder.tvHeader2.setOnClickListener { v: View? ->
            mListener.onFamilyClicked(holder.adapterPosition, mList[holder.adapterPosition], 0)
        }
    }

    override fun getItemCount(): Int {
        Log.e(this.javaClass.simpleName, "getItemCount: mList.size "+mListt.size )
        return mListt.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // list = mList
        val llParent: LinearLayout = itemView.findViewById(R.id.llParent)
        val expandableLayout: RelativeLayout = itemView.findViewById(R.id.expandable_layout)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvFamilyCode: TextView = itemView.findViewById(R.id.tvFamilyCode)
        val tvHeader1: TextView = itemView.findViewById(R.id.tvHeader1)
        val tvHeader2: TextView = itemView.findViewById(R.id.tvHeader2)
        val mArrowImage: ImageView = itemView.findViewById(R.id.arro_imageview)
        val nestedRecyclerView: RecyclerView = itemView.findViewById(R.id.child_rv)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


}