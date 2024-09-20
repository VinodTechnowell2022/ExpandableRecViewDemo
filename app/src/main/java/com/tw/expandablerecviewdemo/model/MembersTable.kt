package com.tw.expandablerecviewdemo.model

data class MembersTable(

    var id: Int,
    var buildingId: Int,    // This id is linked with Building Id
    var memberName: String,
    var dob: String,
    var gender: String,
    var isChecked: Boolean,
)
