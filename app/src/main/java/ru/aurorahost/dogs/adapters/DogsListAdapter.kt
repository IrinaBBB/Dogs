package ru.aurorahost.dogs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.aurorahost.dogs.R
import ru.aurorahost.dogs.databinding.ItemDogBinding
import ru.aurorahost.dogs.model.DogBreed
import ru.aurorahost.dogs.view.ListFragmentDirections

class DogsListAdapter(private val dogsList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {

    private val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return dogsList.size
        }

        override fun getNewListSize(): Int {
            return dogsList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            // Here you should check whether two items represent the same item (e.g., by comparing their IDs)
            return dogsList[oldItemPosition].breedId == dogsList[newItemPosition].breedId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            // Here you check whether the contents of two items are the same
            return dogsList[oldItemPosition] == dogsList[newItemPosition]
        }
    })

    fun updateDogsList(newDogsList: ArrayList<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogsList[position]
        holder.bind(dog)
    }

    override fun getItemCount(): Int = dogsList.size

    inner class DogViewHolder(private val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dog: DogBreed) {
            binding.dogImageView.setImageResource(R.drawable.dog)
            binding.dogBreedTextView.text = dog.dogBreed
            binding.dogLifespanTextView.text = dog.lifeSpan
            binding.mcvDogItem.setOnClickListener {
                Navigation.findNavController(it).navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
            }
        }
    }
}
