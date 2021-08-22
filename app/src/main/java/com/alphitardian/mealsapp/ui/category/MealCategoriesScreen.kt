package com.alphitardian.mealsapp.ui.category

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.alphitardian.mealsapp.model.response.CategoryResponse
import com.alphitardian.mealsapp.ui.composable.CircularLoadingIndicator
import com.alphitardian.mealsapp.ui.theme.MealsAppTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MealCategoriesScreen(navController: NavHostController?) {
    val viewModel: MealCategoryViewModel = viewModel()
    val categories = viewModel.mealCategoryState.value
    val loading = viewModel.loading.value

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var temp: CategoryResponse? by remember {
        mutableStateOf(null)
    }

    BottomSheetScaffold(
        topBar = {
            CategoryTopAppBar(actionOptionClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Option Menu Coming Soon!")
                }
            })
        },
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(16.dp),
        sheetContent = { CategoryBottomSheet(category = temp) },
        sheetPeekHeight = 0.dp
    ) {
        if (loading) {
            CircularLoadingIndicator(isDisplay = loading)
        } else {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(categories) { item ->
                    MealCategoryCard(category = item, actionClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.collapse()
                            temp = null
                            navController?.navigate("category/detail/${item.category}")
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
fun MealCategoryCard(
    category: CategoryResponse,
    actionClick: () -> Unit,
    actionLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(200.dp),
        shape = RoundedCornerShape(14.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .combinedClickable(
                    onClick = actionClick,
                    onLongClick = actionLongClick
                )
        ) {
            Image(
                painter = rememberImagePainter(data = category.imageUrl),
                contentDescription = category.category,
                contentScale = ContentScale.Crop,
                modifier = Modifier.align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.LightGray
                            ), startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = category.category,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = category.desc,
                        style = TextStyle(color = Color.DarkGray),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryBottomSheet(category: CategoryResponse?) {
    Box(modifier = Modifier.height(500.dp)) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = rememberImagePainter(data = category?.imageUrl),
                    contentDescription = category?.category,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(200.dp),
                )
                Column(horizontalAlignment = Alignment.Start) {
                    Text(text = category?.category.orEmpty(), style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = category?.desc.orEmpty(), style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}

@Composable
fun CategoryTopAppBar(actionOptionClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Mangan.",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
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

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealsAppTheme {
        MealCategoriesScreen(navController = null)
    }
}