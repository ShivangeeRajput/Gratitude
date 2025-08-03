package com.example.gratitude.ui.fragments.onboarding.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gratitude.R
import com.example.gratitude.databinding.RvCardviewItemBinding
import com.example.gratitude.ui.fragments.onboarding.steps.models.FocusArea


class OnboardingStepFocusAreaAdapter(
    private val items: List<FocusArea>,
    private val onSelectionChanged: (List<FocusArea>) -> Unit
) : RecyclerView.Adapter<OnboardingStepFocusAreaAdapter.FocusAreaViewHolder>() {

    private val selectedItems = mutableSetOf<Int>()

    inner class FocusAreaViewHolder(val binding: RvCardviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FocusArea, position: Int) {
            binding.tvTitle.text = item.title
            binding.ivBackground.setImageResource(item.imageRes)

            val isSelected = selectedItems.contains(position)

            binding.cardRoot.strokeWidth = if (isSelected) 4 else 1
            binding.cardRoot.strokeColor = if (isSelected)
                ContextCompat.getColor(binding.root.context, R.color.dark_pink)
            else
                Color.TRANSPARENT

            binding.root.setOnClickListener {
                if (isSelected) selectedItems.remove(position)
                else selectedItems.add(position)

                notifyItemChanged(position)
                onSelectionChanged(selectedItems.map { items[it] })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FocusAreaViewHolder {
        val binding = RvCardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FocusAreaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FocusAreaViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    fun getSelectedItems(): List<FocusArea> = selectedItems.map { items[it] }
}




