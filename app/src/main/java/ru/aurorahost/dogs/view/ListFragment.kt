package ru.aurorahost.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.aurorahost.dogs.adapters.DogsListAdapter
import ru.aurorahost.dogs.databinding.FragmentListBinding
import ru.aurorahost.dogs.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModels()

    private val dogsAdapter = DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the RecyclerView with the adapter
        binding.rvDogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsAdapter
        }

        viewModel.refresh()

        binding.refreshLayout.setOnRefreshListener {
            binding.rvDogsList.visibility = View.GONE
            binding.tvError.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        viewModel.dogs.observe(viewLifecycleOwner) { dogs ->
            dogs?.let {
                dogsAdapter.updateDogsList(ArrayList(it))
            }
        }

        viewModel.dogsError.observe(viewLifecycleOwner) { isError ->
            isError?.let {
                binding.tvError.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.rvDogsList.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}