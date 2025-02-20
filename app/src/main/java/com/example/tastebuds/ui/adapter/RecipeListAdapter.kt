package com.example.tastebuds.ui.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tastebuds.R
import com.example.tastebuds.databinding.ItemRecipeListBinding
import com.example.tastebuds.model.IngredientUIModel
import com.example.tastebuds.model.RecipesModel
import com.example.tastebuds.ui.utils.Utils

class RecipeListAdapter(private var recipes: MutableList<RecipesModel>) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    var onItemClick: ((RecipesModel) -> Unit)? = null

    class RecipeViewHolder(private val binding: ItemRecipeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipesModel, onItemClick: ((RecipesModel) -> Unit)?) {
            Utils.loadImage(binding.imgImage, recipe.imageUrl)

            binding.imgFavorite.setOnClickListener {
                it.isSelected = !it.isSelected
            }
            binding.txtTitle.text = recipe.mealName

            val fullText = recipe.description
            val maxCharLimit = 70  // 2 lines
            val viewMoreText = " View More"

            if (fullText.length > maxCharLimit) {
                val truncatedText = fullText.substring(0, maxCharLimit) + "..."
                val spannableString = SpannableString(truncatedText + viewMoreText)

                val viewMoreColor = ContextCompat.getColor(binding.root.context, R.color.buttonColor)

                val startIndex = truncatedText.length
                val endIndex = startIndex + viewMoreText.length
                spannableString.setSpan(ForegroundColorSpan(viewMoreColor), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                binding.txtDesc.text = spannableString

                binding.txtDesc.setOnClickListener {
                    onItemClick?.invoke(recipe)
                }
            } else {
                binding.txtDesc.text = fullText
            }

            setupInnerRecyclerView(recipe.ingredients)

            itemView.setOnClickListener { onItemClick?.invoke(recipe) }
            binding.txtDesc.setOnClickListener { onItemClick?.invoke(recipe) }
        }

        private fun setupInnerRecyclerView(ingredients: List<IngredientUIModel>) {
            binding.recyclerViewItems.apply {
                layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = IngredientsAdapter(ingredients)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClick)
    }

    override fun getItemCount(): Int = recipes.size

    fun setData(newRecipes: List<RecipesModel>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }
}