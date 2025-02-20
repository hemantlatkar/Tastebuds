package com.example.tastebuds.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastebuds.databinding.ItemIngredientsBinding
import com.example.tastebuds.model.IngredientUIModel
import com.example.tastebuds.ui.utils.Utils

class IngredientsAdapter(private val ingredientList: List<IngredientUIModel>) :
    RecyclerView.Adapter<IngredientsAdapter.InnerAdapterViewHolder>() {

    class InnerAdapterViewHolder(private val binding: ItemIngredientsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: IngredientUIModel) {
            binding.txtIngredientName.text = ingredient.name
            Utils.loadImage(
                binding.ivIngredientImage,
                ingredient.imageUrl
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerAdapterViewHolder {
        val binding = ItemIngredientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InnerAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerAdapterViewHolder, position: Int) {
        holder.bind(ingredientList[position])
    }

    override fun getItemCount(): Int = ingredientList.size
}
