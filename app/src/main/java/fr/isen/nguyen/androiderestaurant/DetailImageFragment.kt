package fr.isen.nguyen.androiderestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import fr.isen.nguyen.androiderestaurant.databinding.FragmentDetailImageBinding

private lateinit var adapter: ImageSliderAdapter
private lateinit var viewPager2: ViewPager2
private lateinit var binding: FragmentDetailImageBinding

class DetailImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("URL")?.let{
            Picasso.get()
                .load(it)
                    .placeholder(R.drawable.search)
                    .error(R.drawable.error)
                    .into(binding.fragmentImage)
        }
    }

    companion object {
        fun newInstance(picture: String) =
            DetailImageFragment().apply {
                arguments = Bundle().apply {
                    putString("URL", picture)
                }
            }
    }
}