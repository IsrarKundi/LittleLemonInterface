package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.littlelemon.Destinations.Home.withArgs

@Composable
fun MenuListScreen(navController: NavController) {

    Column {
        UpperPanel()
        LowerPanel(navController)
    }

}

@Composable
private fun UpperPanel() {
    Column(
        modifier = Modifier
            .background(Color(0xFF495E57))
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 40.sp,
            fontWeight = Bold,
            color = Color(0xFFF4CE14)
        )

    }
    Text(
        text = stringResource(id = R.string.order_for_takeaway),
        fontSize = 24.sp,
        fontWeight = Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
private fun LowerPanel(navController: NavController) {
    Column {
        LazyRow {
            items(Categories) { category ->
                MenuCategory(category)
            }
        }
        Divider(
            modifier = Modifier.padding(8.dp),
            color = Color.Gray,
            thickness = 1.dp
        )
        LazyColumn {
            items(Dishes) { Dish ->
                MenuDish(Dish, navController)
            }
        }
    }
}

@Composable
fun MenuCategory(category: String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
        shape = RoundedCornerShape(40),
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = category
        )
    }
}

@Composable
fun MenuDish(Dish: Dish, navController: NavController) {
    Card (modifier = Modifier.clickable {
        navController.navigate(withArgs(Dish.name, Dish.price, Dish.description, Dish.image))
    }){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = Dish.name, fontSize = 18.sp, fontWeight = Bold
                )
                Text(
                    text = Dish.description,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = Dish.price, color = Color.Gray, fontWeight = Bold
                )
            }
            Image(
                painter = painterResource(id = Dish.image),
                contentDescription = "",
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )
}

@Composable
fun ItemDetails(
    name: String,
    price: String,
    description: String,
    image: Int
    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Add background color or other styling as needed
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//adding image to this new screen
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {

            Text(text = name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 16.sp, textAlign = TextAlign.Start)
            Text(text = price, color = Color.Gray, fontWeight = Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))

//        adding button to order
        Button(
            onClick = {  },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14))
        ) {
            Text(
                text = "Order",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
        }
    }

}

//@Composable
//fun ItemDetails(navController: NavController) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val arguments = navBackStackEntry?.arguments
//
//    val dishName = arguments?.getString("name") ?: ""
//    val dishPrice = arguments?.getString("price") ?: ""
//    val dishDescription = arguments?.getString("description") ?: ""
//    val dishImage = arguments?.getInt("image") ?: 0
//
//    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//        // Use the received data in this composable as needed
//        Text(text = "Name: $dishName")
//        Text(text = "Price: $dishPrice")
//        Text(text = "Description: $dishDescription")
//
//        // Load the image using painterResource with the correct resource ID
//        Image(
//            painter = painterResource(id = dishImage),
//            contentDescription = null,
//            modifier = Modifier.size(200.dp) // Adjust the size as needed
//        )
//    }
//}
val Categories = listOf(
    "Lunch",
    "Dessert",
    "A La Carte",
    "Mains",
    "Specials"
)

data class Dish(
    val name: String,
    val price: String,
    val description: String,
    val image: Int
)

val Dishes = listOf(
    Dish(
        "Greek Salad",
        "$12.99",
        "The famous greek salad of crispy lettuce, peppers, olives and our Chicago...",
        R.drawable.greeksalad
    ),
    Dish(
        "Bruschetta",
        "$5.99",
        "Our Bruschetta is made from grilled bread that has been smeared with garlic...",
        R.drawable.bruschetta
    ),
    Dish(
        "Lemon Dessert",
        "$9.99",
        "This comes straight from grandma recipe book, every last ingredient has...",
        R.drawable.lemondessert
    ),
    Dish(
        "Greek Salad",
        "$12.99",
        "The famous greek salad of crispy lettuce, peppers, olives and our Chicago...",
        R.drawable.greeksalad
    ),
    Dish(
        "Bruschetta",
        "$5.99",
        "Our Brushetta is made from grilled bread that has been smeared with garlic...",
        R.drawable.bruschetta
    ),
    Dish(
        "Lemon Dessert",
        "$9.99",
        "This comes straight from grandma recipe book, every last ingredient has...",
        R.drawable.lemondessert
    ),
    Dish(
        "Greek Salad",
        "$12.99",
        "The famous greek salad of crispy lettuce, peppers, olives and our Chicago...",
        R.drawable.greeksalad
    ),
    Dish(
        "Bruschetta",
        "$5.99",
        "Our Brushetta is made from grilled bread that has been smeared with garlic...",
        R.drawable.bruschetta
    ),
    Dish(
        "Lemon Dessert",
        "$9.99",
        "This comes straight from grandma recipe book, every last ingredient has...",
        R.drawable.lemondessert
    )
)