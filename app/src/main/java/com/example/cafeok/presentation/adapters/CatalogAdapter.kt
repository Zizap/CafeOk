package com.example.cafeok.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeok.R
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.databinding.CoffeeItemBinding
import com.squareup.picasso.Picasso

class CatalogAdapter(private val openDialogAddCoffee:(CoffeeModel)->Unit,
                     private val deleteCoffee:(CoffeeModel)->Unit,
                     private val openDescription:(CoffeeModel)->Unit): RecyclerView.Adapter<CatalogHolder>() {

    private var coffeeList = ArrayList<CoffeeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogHolder {
        val binding: CoffeeItemBinding = CoffeeItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return CatalogHolder(binding)
    }

    override fun getItemCount(): Int {
        return coffeeList.size
    }

    override fun onBindViewHolder(holder: CatalogHolder, position: Int) {
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_item_anim)
        holder.itemView.startAnimation(animation)
        holder.bind(coffeeList[position],openDialogAddCoffee,deleteCoffee,openDescription)
    }

    fun setList(coffee: List<CoffeeModel>){
        coffeeList.clear()
        coffeeList.addAll(coffee)
    }


}

class CatalogHolder(val binding: CoffeeItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(coffee:CoffeeModel,
             openDialogAddCoffee: (CoffeeModel) -> Unit,
             deleteCoffee: (CoffeeModel) -> Unit,
             openDescription: (CoffeeModel) -> Unit){

        binding.nameCoffee.text = coffee.name.toString()
        val price = coffee.price.toString()
        binding.priceCoffee.text = "$$price"

        val getImage = coffee.image2.toString()
        Picasso.get().load(getImage).into(binding.imageCoffee)

        binding.buttonMinus.setOnClickListener(View.OnClickListener {
            deleteCoffee(coffee)
        })

        binding.buttonPlus.setOnClickListener(View.OnClickListener {
            openDialogAddCoffee(coffee)
        })

        binding.recItem.setOnClickListener(View.OnClickListener {
            openDescription(coffee)
        })

    }


}
