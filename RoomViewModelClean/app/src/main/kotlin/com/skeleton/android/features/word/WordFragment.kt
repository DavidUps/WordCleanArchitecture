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
import kotlinx.android.synthetic.main.fragment_events.*
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
            observe(words, ::renderWordsList)
            failure(failure, ::handleFailure)
        }

        addWordViewModel = viewModel(viewModelFactory){
            // Cuando cree el ViewModel del AddWord estará.
            // observe(trigger, ::onWordCreated)
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
        rvWords.layoutManager = LinearLayoutManager(activity)
        rvWords.adapter = wordAdapter
        //Adapter
    }

    private fun loadWords(){
        emptyView.invisible()
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
        emptyView.visible()
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
        showProgress()
        addWordViewModel.add(Word(1,"hola"))
    }
}
