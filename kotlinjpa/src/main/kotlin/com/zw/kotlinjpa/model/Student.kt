package com.zw.kotlinjpa.model

data class Student(var id:Long?, var name:String?){
    constructor():this(null,null)
}