package com.app.recipefinder.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.app.recipefinder.ui.components.ErrorComponent
import com.app.recipefinder.ui.components.LoadingComponent
import com.app.recipefinder.ui.components.SuccessComponent
import com.app.recipefinder.ui.viewmodel.RecipeViewIntent
import com.app.recipefinder.ui.viewmodel.RecipeViewModel
import com.app.recipefinder.ui.viewmodel.RecipeViewState

@Composable
fun HomeScreen(recipeViewModel: RecipeViewModel){
    val state by recipeViewModel.state

    when(state){
        is RecipeViewState.Loading -> LoadingComponent()
        is RecipeViewState.Success -> {
            val recipes = (state as RecipeViewState.Success).recipes
            SuccessComponent(recipes = recipes, onSearchClicked = {query ->
                recipeViewModel.processIntent(RecipeViewIntent.SearchRecipes(query))
            })
        }
        is RecipeViewState.Error -> {
            val message = (state as RecipeViewState.Error).message
            ErrorComponent(message = message, onRefreshClicked = {
                recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
            })
        }
    }
    
    LaunchedEffect(Unit){
        recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
    }
}