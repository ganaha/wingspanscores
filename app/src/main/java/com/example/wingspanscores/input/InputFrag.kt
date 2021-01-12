package com.example.wingspanscores.input

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.wingspanscores.AppApplication
import com.example.wingspanscores.R
import com.example.wingspanscores.databinding.FragmentInputBinding

class InputFrag : Fragment(), View.OnClickListener {

    private val viewModel: InputViewModel by viewModels {
        InputViewModelFactory((requireContext().applicationContext as AppApplication).repository)
    }

    private lateinit var binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // player一覧を取得して、AutoCompleteTextViewに設定
        viewModel.players.observe(viewLifecycleOwner, { list ->
            /* プレーヤー名表示 */
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                list.map { it.name }.toTypedArray()
            )

            // adapter設定
            setAdapter(binding.editTextP1, adapter)
            setAdapter(binding.editTextP2, adapter)
            setAdapter(binding.editTextP3, adapter)
            setAdapter(binding.editTextP4, adapter)
            setAdapter(binding.editTextP5, adapter)
        })

        // SAVEボタン
        binding.buttonSave.setOnClickListener(this)
    }

    /**
     * adapter設定
     */
    private fun setAdapter(edit: AutoCompleteTextView, adapter: ArrayAdapter<String>) {
        edit.apply {
            setAdapter(adapter)
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) setSelection(0)
            }
        }
    }

    /**
     * SAVEボタンの押下処理
     */
    override fun onClick(v: View?) {
        // スコア登録
        viewModel.insertScores()

        // Clear
        viewModel.clearInputScores()

        // キーボード閉じる
        v?.let { hideKeyboard(it) }

        // 次のタブへ移動
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem = 1
    }

    /**
     * キーボードを閉じる
     */
    private fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
