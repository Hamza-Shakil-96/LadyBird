package com.perfect.Class


class SchoolData() {
    var name: String? = null
    var phone: String? = null
    var address: String? = null
    var street: String? = null
    var country: String? = null
    var state: String? = null
    var city: String? = null
    var zipcode: String? = null
    var status: String? = null
    var admins = arrayListOf(Admins())
    var rooms = arrayListOf(Rooms())
    var staff = arrayListOf(Staff())
    var student = arrayListOf(Student())

    class Admins() {
        var first_name: String? = null
        var last_name: String? = null
        var email_Address: String? = null
        var password: String? = "12345678"
        var phone: String? = null
        var designation: String? = null
        var isPasswordReset: Boolean = false

    }

    class Rooms() {
        var name: String? = null
        var url: String? = null
    }

    class Student(){
        var first_name: String? = null
        var last_name: String? = null
        var dateOfBirth: String? = null
        var homeRoom: String? = null
        var gender: String? = null
        var address: String? = null
        var parentType: String? = null
        var parentFName: String? = null
        var parentLName: String? = null
        var phoneNumber: String? = null
        var emailAddress: String? = null
        var relationWithChild: String? = null


    }
    class Staff() {
        var first_name: String? = null
        var last_name: String? = null
        var email_Address: String? = null
        var phone: String? = null
        var emergency_name: String? = null
        var emergency_phone: String? = null
        var postion_type: String? = null
        var title: String? = null
        var superUser: String? = null
    }
}