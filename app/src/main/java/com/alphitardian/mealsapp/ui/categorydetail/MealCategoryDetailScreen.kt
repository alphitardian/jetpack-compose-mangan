package com.alphitardian.mealsapp.ui.categorydetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.alphitardian.mealsapp.model.response.MealResponse
import com.alphitardian.mealsapp.ui.composable.CircularLoadingIndicator
import com.alphitardian.mealsapp.ui.composable.MealCard
import com.alphitardian.mealsapp.ui.composable.SimpleTopAppBar
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
            SimpleTopAppBar(name = categoryName, actionBackNavigation = {
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
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(mealCategory) { item ->
                    MealCard(meal = item, actionClick = {
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

@Composable
fun DetailBottomSheet(meal: MealResponse?) {
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