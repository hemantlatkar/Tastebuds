package com.example.tastebuds.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastebuds.databinding.ItemDetailScreenBinding
import com.example.tastebuds.model.IngredientUIModel
import com.example.tastebuds.ui.utils.Utils

class DetailScreenAdapter(private val ingredients: List<IngredientUIModel>) :
    RecyclerView.Adapter<DetailScreenAdapter.DetailScreenViewHolder>() {

    inner class DetailScreenViewHolder(private val binding: ItemDetailScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: IngredientUIModel) {
            binding.apply {
                txtName.text = ingredient.name
                txtMeasure.text = ingredient.measure
                Utils.loadImage(ivIngredientImage, ingredient.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailScreenViewHolder {
        val binding = ItemDetailScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailScreenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailScreenViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size
}
