package ua.zloydi.recipeapp.ui.mappers

import ua.zloydi.recipeapp.models.dto.IngredientDTO
import ua.zloydi.recipeapp.models.dto.recipes.RecipeDetailDTO
import ua.zloydi.recipeapp.models.dto.recipes.RecipeItemDTO
import ua.zloydi.recipeapp.models.dto.unique
import ua.zloydi.recipeapp.ui.data.IngredientUI
import ua.zloydi.recipeapp.ui.data.RecipeItemUI
import ua.zloydi.recipeapp.ui.data.RecipeUI
import ua.zloydi.recipeapp.ui.data.filterType.CuisineUI
import ua.zloydi.recipeapp.ui.data.filterType.DishUI
import ua.zloydi.recipeapp.ui.data.filterType.MealUI

fun RecipeItemDTO.toUI(onClick: () -> Unit) = RecipeItemUI(
    id = id,
    title = label,
    image = image,
    time = totalTime,
    onClick = onClick
)

fun RecipeDetailDTO.toUI(
    item: RecipeItemDTO,
    dishes: List<DishUI>,
    meals: List<MealUI>,
    cuisines: List<CuisineUI>,
) = RecipeUI(
    title = item.label,
    image = item.image,
    source = source,
    url = url,
    ingredients = ingredients.unique().map{it.toUI()},
    totalTime = item.totalTime,
    dishType = dishes,
    mealType = meals,
    cuisineType = cuisines,
)

fun IngredientDTO.toUI() = IngredientUI(
    food, text, measure, image, weight, quantity
)

