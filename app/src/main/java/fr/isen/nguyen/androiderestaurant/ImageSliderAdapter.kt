package fr.isen.nguyen.androiderestaurant

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import fr.isen.nguyen.androiderestaurant.model.Dish

class ImageSliderAdapter (val activity: AppCompatActivity, val imageList: List<String>): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = imageList.size

    override fun createFragment(position: Int): Fragment = DetailImageFragment.newInstance(imageList[position])
}