package com.alphitardian.mealsapp.ui.country

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.alphitardian.mealsapp.model.response.CountryResponse
import com.alphitardian.mealsapp.ui.composable.CircularLoadingIndicator
import com.alphitardian.mealsapp.ui.composable.DrawerContent
import com.alphitardian.mealsapp.utils.getFlag
import kotlinx.coroutines.launch

@Composable
fun MealCountriesScreen(navController: NavHostController?) {
    val viewModel: MealCountriesViewModel = viewModel()
    val countries = viewModel.countriesState.value
    val loading = viewModel.loading.value

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CountryTopAppBar(actionOptionClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Option Menu Coming Soon!")
                }
            }, actionDrawer = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            })
        },
        drawerContent = { DrawerContent(navController = navController) },
        drawerGesturesEnabled = true,
        drawerBackgroundColor = Color.White
    ) {
        if (loading) {
            CircularLoadingIndicator(isDisplay = loading)
        } else {
            LazyColumn {
                items(countries) {
                    CountryItemTiles(country = it, actionClick = {
                        navController?.navigate("country/detail/${it.country}")
                    })
                }
            }
        }
    }
}

@Composable
fun CountryItemTiles(country: CountryResponse, actionClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .clickable(onClick = actionClick)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = getFlag(country.country)),
                    contentDescription = "Country Flag",
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = country.country,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Divider(
            color = Color(0xffEEEEEE),
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun CountryTopAppBar(actionOptionClick: () -> Unit, actionDrawer: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Mangan.",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu Drawer Icon",
                tint = Color.Black,
                modifier = Modifier
                    .clickable(onClick = actionDrawer)
                    .padding(8.dp)
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Option Icon",
                tint = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable(onClick = actionOptionClick)
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Option Icon",
                tint = Color.Black,
                modifier = Modifier.clickable(onClick = actionOptionClick)
            )
        },
        backgroundColor = Color.White
    )
}
