package com.skeleton.android.features.word

import android.os.Bundle
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.skeleton.android.R
import com.skeleton.android.core.exception.Failure
import com.skeleton.android.core.extension.*
import com.skeleton.android.core.functional.DialogCallback
import com.skeleton.android.core.navigation.Navigator
import com.skeleton.android.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_word.*
import javax.inject.Inject


class WordFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_word

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var wordAdapter: WordAdapter

    private lateinit var getGetWordViewModel: GetWordViewModel
    private lateinit var addWordViewModel: AddWordViewModel

    private var word: WordView? = null

    /*companion object {
        fun newInstance(word: WordView): WordFragment{
            val fragment = WordFragment()
            val args = Bundle()
            args.putParcelable("word", word)
            fragment.arguments = args
            return fragment
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        getGetWordViewModel = viewModel(viewModelFactory){
            observe(words,   ::renderWordsList)
            failure(failure, ::handleFailure)
        }

        addWordViewModel = viewModel(viewModelFactory){
            observe(trigger, ::onWordCreated)
            failure(failure, ::handleFailure)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*if (arguments != null) {
            word = arguments!!.getParcelable("word")
            //initLayout()
        }*/
        initializeView()
        initListeners()
        loadWords()
    }

    private fun initListeners(){
        fabAddWord onClick this::addWord
    }

    private fun initializeView(){
        rvWords.adapter = wordAdapter
        rvWords.layoutManager = LinearLayoutManager(activity)
        //Adapter
    }

    private fun loadWords(){
        rvWords.visible()
        showProgress()
        getGetWordViewModel.words()
    }

    private fun renderWordsList(word: List<WordView>?){
        wordAdapter.collection = word.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.CustomError -> renderFailure(failure.errorCode, failure.errorMessage)
        }
    }

    private fun renderFailure(errorCode: Int, errorMessage: String?){
        rvWords.invisible()
        hideProgress()
        showError(errorCode, errorMessage, object : DialogCallback{
            override fun onAccept() {
                loadWords()
            }

            override fun onDecline() {
                onBackPressed()
            }

        })
    }

    private fun addWord(){
        /*showProgress()
        var word = ""
        if (wordAdapter.itemCount % 2 == 0){
            word = "The word"
        } else{
            word = "Is a vampire"
        }
        addWordViewModel.add(Word(wordAdapter.itemCount + 1,word))*/
        navigator.showAddWordFragment(activity!!)
    }

    private fun onWordCreated(any: Any?){
        hideProgress()
        loadWords()
    }
}
