package com.example.controlarterial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.controlarterial.adapter.TomaArterialAdapter
import com.example.controlarterial.dao.tomaDAO
import com.example.controlarterial.databinding.FragmentFirstBinding
import com.example.controlarterial.entity.TomaArterial
import com.example.controlarterial.entity.TomasArteriales
import com.example.controlarterial.viewModel.fragmentViewModel
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var viewModel: fragmentViewModel
    private lateinit var adapter: TomaArterialAdapter
    private lateinit var listView: ListView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        viewModel = ViewModelProvider(this).get(fragmentViewModel::class.java)
        val adapter = TomaArterialAdapter(requireActivity(), mutableListOf())

        viewModel.textLiveData.observe(viewLifecycleOwner) {
            adapter.clear()
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        }
        listView = view.findViewById(R.id.listTomaArterial)
        listView.adapter = adapter

            viewModel.recargarLista()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}