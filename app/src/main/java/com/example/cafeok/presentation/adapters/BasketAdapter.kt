package com.example.cafeok.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeok.R
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.databinding.BasketItemBinding
import com.squareup.picasso.Picasso

class BasketAdapter(private val addCoffee:(BuyBasketModel)->Unit,
                     private val deleteCoffee:(BuyBasketModel)->Unit,
                     private val openDescription:(BuyBasketModel)->Unit): RecyclerView.Adapter<BasketHolder>() {

    private var basketCoffeeList = ArrayList<BuyBasketModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val binding: BasketItemBinding = BasketItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return BasketHolder(binding)
    }

    override fun getItemCount(): Int {
        return basketCoffeeList.size
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_item_anim)
        holder.itemView.startAnimation(animation)
        holder.bind(basketCoffeeList[position],addCoffee,deleteCoffee,openDescription)
    }

    fun setList(coffee: List<BuyBasketModel>){
        basketCoffeeList.clear()
        basketCoffeeList.addAll(coffee)
    }


}

class BasketHolder(val binding: BasketItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(BasketCoffee: BuyBasketModel,
             addCoffee: (BuyBasketModel) -> Unit,
             deleteCoffee: (BuyBasketModel) -> Unit,
             openDescription: (BuyBasketModel) -> Unit){

        binding.nameCoffee.text = BasketCoffee.name.toString()
        val price = BasketCoffee.price.toString()
        binding.priceCoffee.text = "$$price"
        binding.countCoffee.text = BasketCoffee.count.toString()

        val getImage = BasketCoffee.image2.toString()
        Picasso.get().load(getImage).into(binding.imageCoffee)

        binding.buttonMinus.setOnClickListener(View.OnClickListener {
            deleteCoffee(BasketCoffee)
        })

        binding.buttonPlus.setOnClickListener(View.OnClickListener {
            addCoffee(BasketCoffee)
        })

        binding.recItem.setOnClickListener(View.OnClickListener {
            openDescription(BasketCoffee)
        })

    }


}