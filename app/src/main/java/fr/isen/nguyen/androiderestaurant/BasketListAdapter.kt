package fr.isen.nguyen.androiderestaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.nguyen.androiderestaurant.databinding.BasketItemBinding
import fr.isen.nguyen.androiderestaurant.model.Basket
import fr.isen.nguyen.androiderestaurant.model.Dish
import fr.isen.nguyen.androiderestaurant.model.Order

private lateinit var binding: BasketItemBinding

class BasketListAdapter(val orderList: MutableList<Order>, private val deleteItemClickListener: (Order) -> (Unit)): RecyclerView.Adapter<BasketListAdapter.BasketHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        binding = BasketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val order: Order = orderList[position]
        holder.title.text = order.dishName
        holder.price.text = "Prix : " + order.dishPrice.toString() + "€"
        holder.quantity.text = "Quantité : " + order.quantity.toString()

        holder.delete.setOnClickListener{
            deleteItem(position)
            //deleteItemClickListener.invoke(orderList[position])
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    fun deleteItem(position: Int) {
        orderList.removeAt(position)
        notifyDataSetChanged()
    }

    class BasketHolder(binding: BasketItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.dishName
        val layout = binding.root
        val price = binding.dishPrice
        val quantity = binding.dishQuantity
        val delete = binding.deleteIcon
    }

}