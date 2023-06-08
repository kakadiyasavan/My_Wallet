package com.example.mywallet.Adapter

import android.view.View
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.example.mywallet.Model.DataModel
import com.example.mywallet.R

class MyAdapter (dataSet: ArrayList<DataModel>)
    : DragDropSwipeAdapter<DataModel, MyAdapter.ViewHolder>(dataSet) {

    class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val txtAmt: TextView = itemView.findViewById(R.id.txtamount)
        val txtNote: TextView = itemView.findViewById(R.id.txtnote)
    }

    override fun getViewHolder(itemLayout: View) = MyAdapter.ViewHolder(itemLayout)

    override  fun onBindViewHolder(item: DataModel, viewHolder: MyAdapter.ViewHolder, position: Int) {
        viewHolder.txtAmt.text = item.amount.toString()
        viewHolder.txtNote.text = item.note.toString()


    }

    override fun getViewToTouchToStartDraggingItem(item: DataModel, viewHolder: MyAdapter.ViewHolder, position: Int): View? {
        // We return the view holder's view on which the user has to touch to drag the item
        return viewHolder.txtAmt
    }


}
