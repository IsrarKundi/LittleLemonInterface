package com.example.littlelemon

//import com.example.littlelemon.Home.route

sealed class Destinations(val route: String){
    object Home : Destinations("Home")
    object Menu : Destinations("Menu")
    object ItemDetails : Destinations("ItemDetails")

    fun withArgs(name: String, price: String, description: String, image: Int): String {
        return "${ItemDetails.route}/$name/$price/$description/$image"
    }
}

//interface Destinations {
//    val route: String
//}
//
//object Home : Destinations {
//    override val route = "Home"
//}
//
//object Menu : Destinations {
//    override val route = "Menu"
//}
//object ItemDetails : Destinations {
//    override val route = "ItemDetails"
//}
//
//fun withArgs(name: String, price: String, description: String, image: Int): String {
//    return "${ItemDetails.route}/$name/$price/$description/$image"
//}