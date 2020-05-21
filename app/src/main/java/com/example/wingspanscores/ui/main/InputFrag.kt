package com.example.wingspanscores.ui.main

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.wingspanscores.R

class InputFrag: Fragment(), TextWatcher, View.OnClickListener {

    // プレーヤー
    private lateinit var editTextP1: AutoCompleteTextView
    private lateinit var editTextP2: AutoCompleteTextView
    private lateinit var editTextP3: AutoCompleteTextView
    private lateinit var editTextP4: AutoCompleteTextView
    private lateinit var editTextP5: AutoCompleteTextView

    // 1人目の点数
    private lateinit var editTextP1Birds: EditText
    private lateinit var editTextP1Bonus: EditText
    private lateinit var editTextP1Round: EditText
    private lateinit var editTextP1Eggs: EditText
    private lateinit var editTextP1Food: EditText
    private lateinit var editTextP1Tucked: EditText
    private lateinit var textViewP1Total: TextView

    // 2人目の点数
    private lateinit var editTextP2Birds: EditText
    private lateinit var editTextP2Bonus: EditText
    private lateinit var editTextP2Round: EditText
    private lateinit var editTextP2Eggs: EditText
    private lateinit var editTextP2Food: EditText
    private lateinit var editTextP2Tucked: EditText
    private lateinit var textViewP2Total: TextView

    // 3人目の点数
    private lateinit var editTextP3Birds: EditText
    private lateinit var editTextP3Bonus: EditText
    private lateinit var editTextP3Round: EditText
    private lateinit var editTextP3Eggs: EditText
    private lateinit var editTextP3Food: EditText
    private lateinit var editTextP3Tucked: EditText
    private lateinit var textViewP3Total: TextView

    // 4人目の点数
    private lateinit var editTextP4Birds: EditText
    private lateinit var editTextP4Bonus: EditText
    private lateinit var editTextP4Round: EditText
    private lateinit var editTextP4Eggs: EditText
    private lateinit var editTextP4Food: EditText
    private lateinit var editTextP4Tucked: EditText
    private lateinit var textViewP4Total: TextView

    // 5人目の点数
    private lateinit var editTextP5Birds: EditText
    private lateinit var editTextP5Bonus: EditText
    private lateinit var editTextP5Round: EditText
    private lateinit var editTextP5Eggs: EditText
    private lateinit var editTextP5Food: EditText
    private lateinit var editTextP5Tucked: EditText
    private lateinit var textViewP5Total: TextView

    // SAVEボタン
    private lateinit var buttonSave: Button

    private lateinit var players: Array<String>

    private lateinit var dbHelper: WingspanDbHelper

    private lateinit var mView: View

    private lateinit var model: Model

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = view
        dbHelper = WingspanDbHelper(requireContext())
        model = Model(requireContext())

        // プレーヤー取得
        players = model.getPlayers()

        /* プレーヤー先頭表示 */
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, players)
        editTextP1 = view.findViewById<AutoCompleteTextView>(R.id.editTextP1)
        editTextP1.setAdapter(adapter)
        editTextP1.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) editTextP1.setSelection(0)
        }

        editTextP2 = view.findViewById<AutoCompleteTextView>(R.id.editTextP2)
        editTextP2.setAdapter(adapter)
        editTextP2.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) editTextP2.setSelection(0)
        }

        editTextP3 = view.findViewById<AutoCompleteTextView>(R.id.editTextP3)
        editTextP3.setAdapter(adapter)
        editTextP3.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) editTextP3.setSelection(0)
        }

        editTextP4 = view.findViewById<AutoCompleteTextView>(R.id.editTextP4)
        editTextP4.setAdapter(adapter)
        editTextP4.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) editTextP4.setSelection(0)
        }

        editTextP5 = view.findViewById<AutoCompleteTextView>(R.id.editTextP5)
        editTextP5.setAdapter(adapter)
        editTextP5.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) editTextP5.setSelection(0)
        }

        // P1自動計算
        editTextP1Birds = view.findViewById(R.id.editTextP1Birds)
        editTextP1Birds.addTextChangedListener(this)
        editTextP1Bonus = view.findViewById(R.id.editTextP1Bonus)
        editTextP1Bonus.addTextChangedListener(this)
        editTextP1Round = view.findViewById(R.id.editTextP1Round)
        editTextP1Round.addTextChangedListener(this)
        editTextP1Eggs = view.findViewById(R.id.editTextP1Eggs)
        editTextP1Eggs.addTextChangedListener(this)
        editTextP1Food = view.findViewById(R.id.editTextP1Food)
        editTextP1Food.addTextChangedListener(this)
        editTextP1Tucked = view.findViewById(R.id.editTextP1Tucked)
        editTextP1Tucked.addTextChangedListener(this)
        textViewP1Total = view.findViewById(R.id.textViewP1Total)

        // P2自動計算
        editTextP2Birds = view.findViewById(R.id.editTextP2Birds)
        editTextP2Birds.addTextChangedListener(this)
        editTextP2Bonus = view.findViewById(R.id.editTextP2Bonus)
        editTextP2Bonus.addTextChangedListener(this)
        editTextP2Round = view.findViewById(R.id.editTextP2Round)
        editTextP2Round.addTextChangedListener(this)
        editTextP2Eggs = view.findViewById(R.id.editTextP2Eggs)
        editTextP2Eggs.addTextChangedListener(this)
        editTextP2Food = view.findViewById(R.id.editTextP2Food)
        editTextP2Food.addTextChangedListener(this)
        editTextP2Tucked = view.findViewById(R.id.editTextP2Tucked)
        editTextP2Tucked.addTextChangedListener(this)
        textViewP2Total = view.findViewById(R.id.textViewP2Total)

        // P3自動計算
        editTextP3Birds = view.findViewById(R.id.editTextP3Birds)
        editTextP3Birds.addTextChangedListener(this)
        editTextP3Bonus = view.findViewById(R.id.editTextP3Bonus)
        editTextP3Bonus.addTextChangedListener(this)
        editTextP3Round = view.findViewById(R.id.editTextP3Round)
        editTextP3Round.addTextChangedListener(this)
        editTextP3Eggs = view.findViewById(R.id.editTextP3Eggs)
        editTextP3Eggs.addTextChangedListener(this)
        editTextP3Food = view.findViewById(R.id.editTextP3Food)
        editTextP3Food.addTextChangedListener(this)
        editTextP3Tucked = view.findViewById(R.id.editTextP3Tucked)
        editTextP3Tucked.addTextChangedListener(this)
        textViewP3Total = view.findViewById(R.id.textViewP3Total)

        // P4自動計算
        editTextP4Birds = view.findViewById(R.id.editTextP4Birds)
        editTextP4Birds.addTextChangedListener(this)
        editTextP4Bonus = view.findViewById(R.id.editTextP4Bonus)
        editTextP4Bonus.addTextChangedListener(this)
        editTextP4Round = view.findViewById(R.id.editTextP4Round)
        editTextP4Round.addTextChangedListener(this)
        editTextP4Eggs = view.findViewById(R.id.editTextP4Eggs)
        editTextP4Eggs.addTextChangedListener(this)
        editTextP4Food = view.findViewById(R.id.editTextP4Food)
        editTextP4Food.addTextChangedListener(this)
        editTextP4Tucked = view.findViewById(R.id.editTextP4Tucked)
        editTextP4Tucked.addTextChangedListener(this)
        textViewP4Total = view.findViewById(R.id.textViewP4Total)

        // P5自動計算
        editTextP5Birds = view.findViewById(R.id.editTextP5Birds)
        editTextP5Birds.addTextChangedListener(this)
        editTextP5Bonus = view.findViewById(R.id.editTextP5Bonus)
        editTextP5Bonus.addTextChangedListener(this)
        editTextP5Round = view.findViewById(R.id.editTextP5Round)
        editTextP5Round.addTextChangedListener(this)
        editTextP5Eggs = view.findViewById(R.id.editTextP5Eggs)
        editTextP5Eggs.addTextChangedListener(this)
        editTextP5Food = view.findViewById(R.id.editTextP5Food)
        editTextP5Food.addTextChangedListener(this)
        editTextP5Tucked = view.findViewById(R.id.editTextP5Tucked)
        editTextP5Tucked.addTextChangedListener(this)
        textViewP5Total = view.findViewById(R.id.textViewP5Total)

        // SAVEボタン
        buttonSave = view.findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener(this)
    }

    /**
     * 計算
     */
    fun calc() {
        // P1
        val editTextP1Birds = editTextP1Birds.text.toString().toIntOrNull() ?: 0
        val editTextP1Bonus = editTextP1Bonus.text.toString().toIntOrNull() ?: 0
        val editTextP1Round = editTextP1Round.text.toString().toIntOrNull() ?: 0
        val editTextP1Eggs = editTextP1Eggs.text.toString().toIntOrNull() ?: 0
        val editTextP1Food = editTextP1Food.text.toString().toIntOrNull() ?: 0
        val editTextP1Tucked = editTextP1Tucked.text.toString().toIntOrNull() ?: 0
        textViewP1Total.text =
            (editTextP1Birds + editTextP1Bonus + editTextP1Round + editTextP1Eggs + editTextP1Food + editTextP1Tucked).toString()

        // P2
        val editTextP2Birds = editTextP2Birds.text.toString().toIntOrNull() ?: 0
        val editTextP2Bonus = editTextP2Bonus.text.toString().toIntOrNull() ?: 0
        val editTextP2Round = editTextP2Round.text.toString().toIntOrNull() ?: 0
        val editTextP2Eggs = editTextP2Eggs.text.toString().toIntOrNull() ?: 0
        val editTextP2Food = editTextP2Food.text.toString().toIntOrNull() ?: 0
        val editTextP2Tucked = editTextP2Tucked.text.toString().toIntOrNull() ?: 0
        textViewP2Total.text =
            (editTextP2Birds + editTextP2Bonus + editTextP2Round + editTextP2Eggs + editTextP2Food + editTextP2Tucked).toString()

        // P3
        val editTextP3Birds = editTextP3Birds.text.toString().toIntOrNull() ?: 0
        val editTextP3Bonus = editTextP3Bonus.text.toString().toIntOrNull() ?: 0
        val editTextP3Round = editTextP3Round.text.toString().toIntOrNull() ?: 0
        val editTextP3Eggs = editTextP3Eggs.text.toString().toIntOrNull() ?: 0
        val editTextP3Food = editTextP3Food.text.toString().toIntOrNull() ?: 0
        val editTextP3Tucked = editTextP3Tucked.text.toString().toIntOrNull() ?: 0
        textViewP3Total.text =
            (editTextP3Birds + editTextP3Bonus + editTextP3Round + editTextP3Eggs + editTextP3Food + editTextP3Tucked).toString()

        // P4
        val editTextP4Birds = editTextP4Birds.text.toString().toIntOrNull() ?: 0
        val editTextP4Bonus = editTextP4Bonus.text.toString().toIntOrNull() ?: 0
        val editTextP4Round = editTextP4Round.text.toString().toIntOrNull() ?: 0
        val editTextP4Eggs = editTextP4Eggs.text.toString().toIntOrNull() ?: 0
        val editTextP4Food = editTextP4Food.text.toString().toIntOrNull() ?: 0
        val editTextP4Tucked = editTextP4Tucked.text.toString().toIntOrNull() ?: 0
        textViewP4Total.text =
            (editTextP4Birds + editTextP4Bonus + editTextP4Round + editTextP4Eggs + editTextP4Food + editTextP4Tucked).toString()

        // P5
        val editTextP5Birds = editTextP5Birds.text.toString().toIntOrNull() ?: 0
        val editTextP5Bonus = editTextP5Bonus.text.toString().toIntOrNull() ?: 0
        val editTextP5Round = editTextP5Round.text.toString().toIntOrNull() ?: 0
        val editTextP5Eggs = editTextP5Eggs.text.toString().toIntOrNull() ?: 0
        val editTextP5Food = editTextP5Food.text.toString().toIntOrNull() ?: 0
        val editTextP5Tucked = editTextP5Tucked.text.toString().toIntOrNull() ?: 0
        textViewP5Total.text =
            (editTextP5Birds + editTextP5Bonus + editTextP5Round + editTextP5Eggs + editTextP5Food + editTextP5Tucked).toString()
    }

    /**
     * 点数入力後の処理
     */
    override fun afterTextChanged(p0: Editable?) {
        calc()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    /**
     * SAVEボタンの押下処理
     */
    override fun onClick(v: View?) {
        val database = dbHelper.writableDatabase

        val p1 = editTextP1.text.toString()
        val p1Birds = editTextP1Birds.text.toString().toIntOrNull() ?: 0
        val p1Bonus = editTextP1Bonus.text.toString().toIntOrNull() ?: 0
        val p1Round = editTextP1Round.text.toString().toIntOrNull() ?: 0
        val p1Eggs = editTextP1Eggs.text.toString().toIntOrNull() ?: 0
        val p1Food = editTextP1Food.text.toString().toIntOrNull() ?: 0
        val p1Tucked = editTextP1Tucked.text.toString().toIntOrNull() ?: 0
        val p1Total = textViewP1Total.text.toString().toIntOrNull() ?: 0

        val p2 = editTextP2.text.toString()
        val p2Birds = editTextP2Birds.text.toString().toIntOrNull() ?: 0
        val p2Bonus = editTextP2Bonus.text.toString().toIntOrNull() ?: 0
        val p2Round = editTextP2Round.text.toString().toIntOrNull() ?: 0
        val p2Eggs = editTextP2Eggs.text.toString().toIntOrNull() ?: 0
        val p2Food = editTextP2Food.text.toString().toIntOrNull() ?: 0
        val p2Tucked = editTextP2Tucked.text.toString().toIntOrNull() ?: 0
        val p2Total = textViewP2Total.text.toString().toIntOrNull() ?: 0

        val p3 = editTextP3.text.toString()
        val p3Birds = editTextP3Birds.text.toString().toIntOrNull() ?: 0
        val p3Bonus = editTextP3Bonus.text.toString().toIntOrNull() ?: 0
        val p3Round = editTextP3Round.text.toString().toIntOrNull() ?: 0
        val p3Eggs = editTextP3Eggs.text.toString().toIntOrNull() ?: 0
        val p3Food = editTextP3Food.text.toString().toIntOrNull() ?: 0
        val p3Tucked = editTextP3Tucked.text.toString().toIntOrNull() ?: 0
        val p3Total = textViewP3Total.text.toString().toIntOrNull() ?: 0

        val p4 = editTextP4.text.toString()
        val p4Birds = editTextP4Birds.text.toString().toIntOrNull() ?: 0
        val p4Bonus = editTextP4Bonus.text.toString().toIntOrNull() ?: 0
        val p4Round = editTextP4Round.text.toString().toIntOrNull() ?: 0
        val p4Eggs = editTextP4Eggs.text.toString().toIntOrNull() ?: 0
        val p4Food = editTextP4Food.text.toString().toIntOrNull() ?: 0
        val p4Tucked = editTextP4Tucked.text.toString().toIntOrNull() ?: 0
        val p4Total = textViewP4Total.text.toString().toIntOrNull() ?: 0

        val p5 = editTextP5.text.toString()
        val p5Birds = editTextP5Birds.text.toString().toIntOrNull() ?: 0
        val p5Bonus = editTextP5Bonus.text.toString().toIntOrNull() ?: 0
        val p5Round = editTextP5Round.text.toString().toIntOrNull() ?: 0
        val p5Eggs = editTextP5Eggs.text.toString().toIntOrNull() ?: 0
        val p5Food = editTextP5Food.text.toString().toIntOrNull() ?: 0
        val p5Tucked = editTextP5Tucked.text.toString().toIntOrNull() ?: 0
        val p5Total = textViewP5Total.text.toString().toIntOrNull() ?: 0

        val p1Rank = getRank(p1Total, p2Total, p3Total, p4Total, p5Total)
        val p2Rank = getRank(p2Total, p1Total, p3Total, p4Total, p5Total)
        val p3Rank = getRank(p3Total, p1Total, p2Total, p4Total, p5Total)
        val p4Rank = getRank(p4Total, p1Total, p2Total, p3Total, p5Total)
        val p5Rank = getRank(p5Total, p1Total, p2Total, p3Total, p4Total)

        model.insertData(p1, p1Birds, p1Bonus, p1Round, p1Eggs, p1Food, p1Tucked, p1Total, p1Rank)
        model.insertData(p2, p2Birds, p2Bonus, p2Round, p2Eggs, p2Food, p2Tucked, p2Total, p2Rank)
        model.insertData(p3, p3Birds, p3Bonus, p3Round, p3Eggs, p3Food, p3Tucked, p3Total, p3Rank)
        model.insertData(p4, p4Birds, p4Bonus, p4Round, p4Eggs, p4Food, p4Tucked, p4Total, p4Rank)
        model.insertData(p5, p5Birds, p5Bonus, p5Round, p5Eggs, p5Food, p5Tucked, p5Total, p5Rank)

        // Clear
        clear()

        // キーボード閉じる
        hideKeyboard(mView)

        // 次のタブへ移動
        requireActivity().findViewById<ViewPager>(R.id.view_pager).setCurrentItem(1)
    }

    /**
     * 入力内容をクリア
     */
    private fun clear() {
//        editTextP1.setText("")
//        editTextP2.setText("")
//        editTextP3.setText("")
//        editTextP4.setText("")
//        editTextP5.setText("")

        editTextP1Birds.setText("")
        editTextP2Birds.setText("")
        editTextP3Birds.setText("")
        editTextP4Birds.setText("")
        editTextP5Birds.setText("")

        editTextP1Bonus.setText("")
        editTextP2Bonus.setText("")
        editTextP3Bonus.setText("")
        editTextP4Bonus.setText("")
        editTextP5Bonus.setText("")

        editTextP1Round.setText("")
        editTextP2Round.setText("")
        editTextP3Round.setText("")
        editTextP4Round.setText("")
        editTextP5Round.setText("")

        editTextP1Eggs.setText("")
        editTextP2Eggs.setText("")
        editTextP3Eggs.setText("")
        editTextP4Eggs.setText("")
        editTextP5Eggs.setText("")

        editTextP1Food.setText("")
        editTextP2Food.setText("")
        editTextP3Food.setText("")
        editTextP4Food.setText("")
        editTextP5Food.setText("")

        editTextP1Tucked.setText("")
        editTextP2Tucked.setText("")
        editTextP3Tucked.setText("")
        editTextP4Tucked.setText("")
        editTextP5Tucked.setText("")
    }

    /**
     * 順位を算出する。
     */
    private fun getRank(myTotal: Int, p2Total: Int, p3Total: Int, p4Total: Int, p5Total: Int): Int {
        Log.i("RANK1", "${myTotal}, ${p2Total}, ${p3Total}, ${p4Total}, ${p5Total}")
        val list = listOf<Int>(myTotal, p2Total, p3Total, p4Total, p5Total)
        val sorted = list.sortedDescending()
        Log.i("RANK2", "${sorted[0]}, ${sorted[1]}, ${sorted[2]}, ${sorted[3]}, ${sorted[4]}")
        val rank = sorted.indexOf(myTotal) + 1
        Log.i("RANK3", "${rank}")
        return rank
    }

    /**
     * キーボードを閉じる
     */
    private fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
    }
}
