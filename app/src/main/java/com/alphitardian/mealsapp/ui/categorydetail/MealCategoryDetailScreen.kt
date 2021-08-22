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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
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
    val bottomSheetValue = viewModel.bottomSheetValue.value

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CategoryDetailTopAppBar(name = categoryName, actionOptionClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Option Menu Coming Soon!")
                }
            }, actionBackNavigation = {
                navController?.popBackStack()
            })
        },
        sheetContent = { DetailBottomSheet(meal = bottomSheetValue) },
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
                            viewModel.bottomSheetValue.value = null
                            navController?.navigate("meal/${item.id}")
                        }
                    }, actionLongClick = {
                        scope.launch {
                            if (scaffoldState.bottomSheetState.isCollapsed) {
                                viewModel.bottomSheetValue.value = item
                                scaffoldState.bottomSheetState.expand()
                            } else {
                                scaffoldState.bottomSheetState.collapse()
                                viewModel.bottomSheetValue.value = null
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
    Column {
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
        Divider(
            color = Color(0xffEEEEEE),
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun CategoryDetailTopAppBar(
    name: String,
    actionOptionClick: () -> Unit,
    actionBackNavigation: () -> Unit
) {
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
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back Icon",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable(onClick = actionBackNavigation)
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