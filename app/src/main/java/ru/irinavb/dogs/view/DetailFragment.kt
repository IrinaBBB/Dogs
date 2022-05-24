package ru.irinavb.dogs.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.irinavb.dogs.R
import ru.irinavb.dogs.databinding.ActivityMainBinding.inflate
import ru.irinavb.dogs.databinding.FragmentDetailBinding
import ru.irinavb.dogs.databinding.FragmentListBinding

private const val TAG = "DetailFragment"

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var dogUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}