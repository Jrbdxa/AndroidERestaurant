package fr.isen.nguyen.androiderestaurant.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.nguyen.androiderestaurant.databinding.CategoryCellBinding

class CategoryListAdapter(val categories: List<Dish>, private val categoriesClickListener: (Dish) -> (Unit)): RecyclerView.Adapter<CategoryListAdapter.CategoryHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CategoryHolder {
        val itemBinding = CategoryCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(
            itemBinding
        )
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val currentDish = categories[position]
        holder.title.text = currentDish.title
        holder.price.text = currentDish.prices[0].value
        holder.description.text = currentDish.ingredients.map{ e -> e.title }.joinToString(", " )

        val thumbnailUrl = currentDish.getThumbnail()
        if (thumbnailUrl != null) {
            Picasso.get()
                .load(thumbnailUrl)
                .into(holder.image)
        }

        holder.layout.setOnClickListener {
            categoriesClickListener.invoke(categories[position])
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryHolder(binding: CategoryCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.dishName
        val layout = binding.root
        val image = binding.imageView
        val price = binding.dishPrice
        val description = binding.dishDescription
    }
}