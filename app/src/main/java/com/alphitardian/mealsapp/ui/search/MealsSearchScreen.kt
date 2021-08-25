package com.alphitardian.mealsapp.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.alphitardian.mealsapp.model.response.MealDetail
import com.alphitardian.mealsapp.ui.composable.CircularLoadingIndicator
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun MealsSearchScreen(navController: NavHostController?) {
    val viewModel: SearchViewModel = viewModel()
    val textValueState = viewModel.textFieldState.observeAsState()
    val meals = viewModel.mealSearchState.value
    val loading = viewModel.loading.value

    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        SearchBar(
            textValue = textValueState.value.orEmpty(),
            onTextValueChange = { viewModel.onTextChanged(it) },
            actionBackIcon = { navController?.popBackStack() },
            actionSearch = {
                scope.launch {
                    viewModel.loading.value = true
                    viewModel.getSearchMeals(textValueState.value.orEmpty())
                    keyboardController?.hide()
                }
            }
        )

        if (loading) {
            CircularLoadingIndicator(isDisplay = loading)
        } else {
            LazyColumn {
                if (meals != null) {
                    items(meals) {
                        ItemTiles(meal = it, actionClick = {
                            navController?.navigate("meal/${it.id}")
                        })
                    }
                } else {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 128.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Delete Icon",
                                tint = Color.LightGray,
                                modifier = Modifier
                                    .height(64.dp)
                                    .width(64.dp)
                            )
                            Text(
                                text = "No Meals Found",
                                style = TextStyle(color = Color.LightGray)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    textValue: String,
    onTextValueChange: (newValue: String) -> Unit,
    actionBackIcon: () -> Unit,
    actionSearch: KeyboardActionScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Arrow Back Icon",
            modifier = Modifier
                .clickable(onClick = actionBackIcon)
                .padding(start = 8.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        )
        OutlinedTextField(
            value = textValue,
            onValueChange = onTextValueChange,
            singleLine = true,
            placeholder = { Text(text = "Please Type Here") },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            shape = RoundedCornerShape(40.dp),
            keyboardActions = KeyboardActions(onDone = actionSearch)
        )
    }
}

@Composable
fun ItemTiles(meal: MealDetail, actionClick: () -> Unit) {
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
                    painter = rememberImagePainter(data = meal.imageUrl),
                    contentDescription = "Meal Image",
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = meal.name,
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

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun SearchPreview() {
    MealsSearchScreen(navController = null)
}