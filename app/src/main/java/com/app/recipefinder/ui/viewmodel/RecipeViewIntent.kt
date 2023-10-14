package com.app.recipefinder.ui.viewmodel

sealed class RecipeViewIntent{
    object LoadRandomRecipe : RecipeViewIntent()
    data class SearchRecipes(val query: String) : RecipeViewIntent()
}
