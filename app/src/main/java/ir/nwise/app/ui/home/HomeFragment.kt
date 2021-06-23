package ir.nwise.app.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ir.nwise.app.R
import ir.nwise.app.databinding.FragmentHomeBinding
import ir.nwise.app.ui.base.BaseFragment
import ir.nwise.app.ui.utils.toastOopsError
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewState, HomeViewModel, FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()

    private val userPostAdapter: UserPostAdapter =
        UserPostAdapter(
            onItemClicked = { model ->
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show()
                binding.root.findNavController().navigate(
                    HomeFragmentDirections.openDetail(model)
                )
            }
        )

    override fun getLayout(): Int = R.layout.fragment_home

    override fun onCreateViewCompleted() {
        setHasOptionsMenu(true)
        binding.lifecycleOwner = this@HomeFragment
        setupSwipeRefreshLayout()
        viewModel.getAllPost()
        initRecyclerView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = homeViewModel
        super.onCreate(savedInstanceState)
    }

    override fun render(state: HomeViewState) {
        binding.swipeRefresh.isRefreshing = false
        when (state) {
            is HomeViewState.Loading -> {
                //TODO: Add custom spinner
            }
            is HomeViewState.Loaded -> {
                userPostAdapter.submitItems(state.albums)
                userPostAdapter.notifyDataSetChanged()
            }
            is HomeViewState.Error -> {
                Log.e(
                    "HomeFragment",
                    state.throwable.message,
                    state.throwable
                )
                binding.root.toastOopsError()
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = userPostAdapter
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            viewModel.getAllPost()
        }
    }
}