package ru.aurorahost.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.aurorahost.dogs.databinding.FragmentDetailBinding
import ru.aurorahost.dogs.util.getProgressDrawable
import ru.aurorahost.dogs.util.loadImage
import ru.aurorahost.dogs.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var dogUuid = 1
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using ViewBinding
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        viewModel.dog.observe(viewLifecycleOwner) { dog ->
            dog?.let {
                binding.dogBreedTextView.text = dog.dogBreed
                binding.dogLifespanTextView.text = dog.lifeSpan
                context?.let {
                    binding.dogDetailImageView.loadImage(dog.imageUrl, getProgressDrawable(it))
                }
            }
        }

        viewModel.loadDog(dogUuid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
