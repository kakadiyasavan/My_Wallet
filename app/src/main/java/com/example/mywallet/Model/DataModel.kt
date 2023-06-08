package com.example.mywallet.Model

class DataModel {

    var id = 0
    var amount : Int = 0
    lateinit var category:String
    lateinit var note:String
    var isexpence =0

    constructor(id: Int, amount: Int, category: String, note: String, isexpence: Int) {
        this.id = id
        this.amount = amount
        this.category = category
        this.note = note
        this.isexpence = isexpence
    }
}