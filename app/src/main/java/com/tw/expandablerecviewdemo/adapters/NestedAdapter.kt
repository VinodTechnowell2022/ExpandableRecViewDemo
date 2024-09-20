package com.tw.expandablerecviewdemo.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tw.expandablerecviewdemo.R
import com.tw.expandablerecviewdemo.model.MembersTable

class NestedAdapter(private val mList: List<MembersTable>, mlistener: Activity) : RecyclerView.Adapter<NestedAdapter.NestedViewHolder>() {

    private var lastPosition = -1
    var mListener: OnItemClickListener
    var todosList = mutableListOf<MembersTable?>()

    init {
        this.todosList = mList.toMutableList()
        this.mListener = mlistener as OnItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, data: MembersTable)
        fun onItemClicked(position: Int, data: MembersTable, viewResp:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nested_item, parent, false)
        return NestedViewHolder(view)
    }

    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        holder.tvMemberName.text = mList[position].memberName
        holder.tvHohCode.text = mList[position].memberName
        holder.tvMemberId.text = mList[position].id.toString()
        holder.tvRelationship.text = mList[position].dob
        holder.tvGender.text = mList[position].gender
        holder.tvDob.text = mList[position].dob

        holder.tvChild1.setOnClickListener { v: View? ->
            Toast.makeText(holder.itemView.context, "cliked " + mList[holder.adapterPosition], Toast.LENGTH_SHORT).show()
            mListener.onItemClicked(holder.adapterPosition, mList[holder.adapterPosition])
        }

        holder.tvChild2.setOnClickListener { v : View? ->
            mListener.onItemClicked(holder.adapterPosition, mList[holder.adapterPosition], 0)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class NestedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMemberName: TextView = itemView.findViewById(R.id.tvMemberName)
        val tvHohCode: TextView = itemView.findViewById(R.id.tvHohCode)
        val tvMemberId: TextView = itemView.findViewById(R.id.tvMemberId)
        val tvRelationship: TextView = itemView.findViewById(R.id.tvRelationship)
        val tvGender: TextView = itemView.findViewById(R.id.tvGender)
        val tvOccupation: TextView = itemView.findViewById(R.id.tvOccupation)
        val tvDob: TextView = itemView.findViewById(R.id.tvDob)
        val tvChild1: TextView = itemView.findViewById(R.id.tvChild1)
        val tvChild2: TextView = itemView.findViewById(R.id.tvChild2)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}