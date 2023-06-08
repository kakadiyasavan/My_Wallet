package com.example.mywallet.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.Model.DataModel
import com.example.mywallet.databinding.RcvTransBinding

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.MenuItem
import android.widget.PopupMenu
import com.example.mywallet.R

class TransAdapter(delete: (Int) -> Unit,update: (DataModel)->Unit) : RecyclerView.Adapter<TransAdapter.TransHolder>() {

    lateinit var transaction: ArrayList<DataModel>
    var deleteClick = delete
    var updateClick = update

    class TransHolder(itemView: RcvTransBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransHolder {
        var binding = RcvTransBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransHolder(binding)
    }

    override fun getItemCount(): Int {
        return transaction.size
    }

    override fun onBindViewHolder(
        holder: TransHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.binding.apply {
            txtamount.text = transaction.get(position).amount.toString()
            category.text = transaction.get(position).category
            txtnote.text = transaction.get(position).note

            if (transaction.get(position).isexpence == 0) {
                txtamount.setTextColor(Color.GREEN)
            } else {
                txtamount.setTextColor(Color.RED)
            }

        }

        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {

                var option = PopupMenu(holder.itemView.context, holder.itemView)
                option.menuInflater.inflate(R.menu.pop_menu, option.menu)

                option.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem?): Boolean {

                        when (item?.itemId) {
                            R.id.delete -> {
                                deleteClick.invoke(transaction.get(position).id)
                            }

                            R.id.update -> {
                                updateClick.invoke(transaction.get(position))
                            }
                        }

                        return false
                    }

                })

                option.show()

                return true
            }

        })
    }


    fun Trans(transaction: ArrayList<DataModel>) {
        this.transaction = transaction
    }

    fun update(transaction: java.util.ArrayList<DataModel>) {
        this.transaction = transaction
        notifyDataSetChanged()
    }
}