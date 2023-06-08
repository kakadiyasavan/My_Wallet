package com.example.mywallet.Fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywallet.Adapter.TransAdapter
import com.example.mywallet.DataBase.DBHelper
import com.example.mywallet.Model.DataModel
import com.example.mywallet.databinding.FragmentTransactionBinding
import com.example.mywallet.databinding.UpdateItemBinding

class TransactionFragment : Fragment() {

    lateinit var binding: FragmentTransactionBinding
    lateinit var adapter: TransAdapter
    lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTransactionBinding.inflate(layoutInflater)
        dbHelper = DBHelper(context)
        adapter = TransAdapter({
            dbHelper.deleteTransaction(it)
            adapter.update(dbHelper.getTransaction())
        }, {
            var dialog = Dialog(requireContext())
            var bind = UpdateItemBinding.inflate(layoutInflater)
            dialog.setContentView(bind.root)

            it.apply {
                bind.apply {
                    edtAmount.setText(amount.toString())
                    edtNote.setText(note)

                    btnUpdate.setOnClickListener {
                        var amt2 = edtAmount.text.toString().toInt()
                        var category2=edtCategory.text.toString()
                        var note2 = edtNote.text.toString()

                        var model = DataModel(id,amt2,note2,category2,isexpence)

                        dbHelper.updateTransaction(model)
                        adapter.update(dbHelper.getTransaction())
                        dialog.dismiss()
                    }
                }
            }

            dialog.show()



        })
        adapter.Trans(dbHelper.getTransaction())

        binding.rcvtrans.layoutManager = LinearLayoutManager(context)
        binding.rcvtrans.adapter = adapter



        return binding.root
    }
}



