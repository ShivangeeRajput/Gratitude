package com.example.gratitude.ui.fragments.welcomepage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import com.example.gratitude.ui.fragments.welcomepage.models.WelcomePage
import com.example.gratitude.databinding.ItemOnboardingPageBinding

class WelcomePagerAdapter(
    private val pages: List<WelcomePage>
) : RecyclerView.Adapter<WelcomePagerAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(
        private val binding: ItemOnboardingPageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(page: WelcomePage) {
            binding.tvTitle.text = page.title
            binding.subtitle.text = page.subtitle

            if (page.lottieFile != null) {
                binding.lottieView.visibility = View.VISIBLE
                binding.ivWelcomeImage.visibility = View.GONE
                binding.lottieView.setAnimation(page.lottieFile)
                binding.lottieView.playAnimation()
            } else if (page.imageRes != null) {
                binding.lottieView.visibility = View.GONE
                binding.ivWelcomeImage.visibility = View.VISIBLE
                binding.ivWelcomeImage.setImageResource(page.imageRes)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val binding = ItemOnboardingPageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OnboardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    override fun getItemCount(): Int = pages.size
}

