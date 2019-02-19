package com.skeleton.android.features.word

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.skeleton.android.R
import com.skeleton.android.core.exception.Failure
import com.skeleton.android.core.extension.*
import com.skeleton.android.core.functional.DialogCallback
import com.skeleton.android.core.navigation.Navigator
import com.skeleton.android.core.platform.BaseFragment
import com.skeleton.android.features.people.GetPeopleViewModel
import com.skeleton.android.features.people.PeopleView
import kotlinx.android.synthetic.main.fragment_add_word.*
import kotlinx.android.synthetic.main.fragment_events.*
import java.util.*
import javax.inject.Inject

class AddWordFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var addWordViewModel: AddWordViewModel
    private lateinit var getPeopleViewModel: GetPeopleViewModel

    private var word: WordView? = null

    override fun layoutId() = R.layout.fragment_add_word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        addWordViewModel = viewModel(viewModelFactory){
            observe(trigger, ::onWordCreated)
            failure(failure, ::handleFailure)
        }

        getPeopleViewModel = viewModel(viewModelFactory){
            observe(people, ::onGetPeople)
            failure(failure, ::handleFailure)
        }

    }

    private fun onGetPeople(peopleView: PeopleView?) {
        hideProgress()
        etWord.setText(peopleView!!.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        btnAleatoryName onClick  this::getPeople
        btnSave onClick this::addWord
    }

    private fun getPeople(){
        showProgress()
        getPeopleViewModel.getPeople((0..88).random())
    }

    private fun addWord(){
        if (!etWord.text!!.isEmpty()){showProgress()
            addWordViewModel.add(Word(0,etWord.text.toString()))
            navigator.showWordFragment(activity!!)
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.CustomError -> renderFailure(failure.errorCode, failure.errorMessage)
        }
    }

    private fun renderFailure(errorCode: Int, errorMessage: String?) {
        eventList.invisible()
        emptyView.visible()
        hideProgress()
        showError(errorCode, errorMessage, object : DialogCallback {
            override fun onAccept() {
                //loadEvents()
            }

            override fun onDecline() {
                onBackPressed()
            }
        })
    }

    private fun onWordCreated(any: Any?){
        hideProgress()
    }


}