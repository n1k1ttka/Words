package com.example.wordsapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentWordListBinding


class WordListFragment : Fragment() {

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
        fun newInstance(letterId: String) = WordListFragment().apply {
            arguments = Bundle().apply {
                putString(LETTER, letterId)
            }
        }
    }

    private var _binding: FragmentWordListBinding? = null // Нулевая ссылка на макет фрагмента
    private val binding get() = _binding!! // назначили свойство get-only
    private lateinit var recyclerView: RecyclerView
    private lateinit var letterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, // "Раздуватель" макета
        container: ViewGroup?, // Контейнер под views
        savedInstanceState: Bundle? // Сохранение значений на случай выхода?
    ): View? {
        _binding = FragmentWordListBinding.inflate(inflater, container, false) // раздули макет
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //val letterId = activity?.intent?.extras?.getString(LETTER).toString()
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}