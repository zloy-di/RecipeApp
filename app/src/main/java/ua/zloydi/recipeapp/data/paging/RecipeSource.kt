package ua.zloydi.recipeapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.zloydi.recipeapp.data.repository.RecipeRepository
import ua.zloydi.recipeapp.data.retrofit.RecipeQuery
import ua.zloydi.recipeapp.models.dto.QueryDTO
import ua.zloydi.recipeapp.models.dto.recipes.RecipeItemDTO

class RecipeSource(private val repository: RecipeRepository, private val query: RecipeQuery.Search) : PagingSource<String, RecipeItemDTO>(){
    override fun getRefreshKey(state: PagingState<String, RecipeItemDTO>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RecipeItemDTO> {
        val response: QueryDTO = repository.query(query, params.key) ?: return LoadResult.Error(Exception("No data"))
        Log.d("Debug141", "load: from=${response.from}, to=${response.to}, count=${response.count}")
        val recipes = response.hits.map { it.recipe }
        val next = response._links.next?.hash

        return LoadResult.Page(recipes, null, next)
    }
}