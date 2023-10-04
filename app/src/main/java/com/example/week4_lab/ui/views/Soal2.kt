package com.example.week4_lab.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4_lab.R
import com.example.week4_lab.data.dummy_data
import com.example.week4_lab.model.categories
import com.example.week4_lab.model.products
import java.util.Locale.Category

@Composable
fun Soal2(listCategory : List<categories>, listProduct : List<products>){

    Column {
        Row(
            modifier = Modifier
                .background(
                    Color(0xFF4B8F4B)
                )
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text ="Tokopakedi",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Image(painter = painterResource(id = R.drawable.round_menu_24),
                contentDescription ="Menu",
                modifier = Modifier.size(38.dp)
            )
        }



        Column {
            Image(painter = painterResource(id = R.drawable.tokopakedi),
                contentDescription ="Tokopakedi Banner",
                modifier = Modifier
                    .padding(8.dp)
                    .height(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Categories",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 8.dp ,horizontal= 16.dp)
            )
            LazyRow{
                items(listCategory){
                    CardCategory(
                        category = it,
                        modifier = Modifier.padding( start= 16.dp))
                }
            }

            Text(
                text = "Products",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ){
                items(listProduct){
                    CardProduct(it, Modifier.padding(4.dp))
                }
            }
        }
    }
}

@Composable
fun CardCategory(category : categories, modifier: Modifier = Modifier){
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            Image(painter = painterResource(id = category.image_path) ,
                contentDescription = "Category",
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = category.category_name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "${category.number_of_items} Products",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp, bottom = 4.dp)
            )
        }

    }
}

@Composable
fun CardProduct(product: products, modifier: Modifier = Modifier){
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Image(painter = painterResource(id =product.image_path) ,
                contentDescription = "Category",
                modifier = Modifier.size(190.dp)
            )
            Text(
                text = product.product_name,
                fontSize= 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp)
            )
            Text(
                text = "Rp ${product.price}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp)
            )
            Text(
                text = product.location,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp)
            )
            Text(
                text = "${product.sold} Sold",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp, start = 8.dp)
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview(){
Soal2(listCategory = dummy_data().get_data_tokopedia_category(), listProduct = dummy_data().get_data_tokopedia_product())
//    CardProduct(dummy_data().get_data_tokopedia_product()[0])
}