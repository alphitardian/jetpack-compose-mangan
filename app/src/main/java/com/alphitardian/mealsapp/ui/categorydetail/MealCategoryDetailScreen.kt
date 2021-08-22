package com.alphitardian.mealsapp.ui.categorydetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
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
import com.alphitardian.mealsapp.model.response.MealCategoryListResponse
import com.alphitardian.mealsapp.ui.composable.CircularLoadingIndicator
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MealCategoryDetailScreen(categoryName: String, navController: NavHostController?) {
    val viewModel: CategoryDetailViewModel = viewModel()
    viewModel.getCategory(categoryName)
    val mealCategory = viewModel.mealCategoryState.value
    val loading = viewModel.loading.value

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var temp: MealCategoryListResponse? by remember {
        mutableStateOf(null)
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CategoryDetailTopAppBar(name = categoryName, actionOptionClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Option Menu Coming Soon!")
                }
            })
        },
        sheetContent = { DetailBottomSheet(meal = temp) },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(16.dp)
    ) {
        if (loading) {
            CircularLoadingIndicator(isDisplay = loading)
        } else {
            LazyColumn {
                items(mealCategory) { item ->
                    CategoryItemTiles(meal = item, actionClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.collapse()
                            temp = null
                            navController?.navigate("meal/${item.id}")
                        }
                    }, actionLongClick = {
                        scope.launch {
                            if (scaffoldState.bottomSheetState.isCollapsed) {
                                temp = item
                                scaffoldState.bottomSheetState.expand()
                            } else {
                                scaffoldState.bottomSheetState.collapse()
                                temp = null
                            }
                        }
                    })
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun CategoryItemTiles(
    meal: MealCategoryListResponse,
    actionClick: () -> Unit,
    actionLongClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(onClick = actionClick, onLongClick = actionLongClick)
    ) {
        Card(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp)
                .padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = meal.imageUrl),
                contentDescription = meal.mealName,
            )
        }
        Text(text = meal.mealName, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CategoryDetailTopAppBar(name: String, actionOptionClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        },
        actions = {
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

@Composable
fun DetailBottomSheet(meal: MealCategoryListResponse?) {
    Box(
        modifier = Modifier
            .height(400.dp)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = meal?.imageUrl),
                    contentDescription = meal?.mealName,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = meal?.mealName.orEmpty(), fontWeight = FontWeight.Bold)
        }
    }
}