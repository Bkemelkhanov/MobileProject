package com.example.projectmobilerestaurant.ui.createaccount

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.*
import com.example.projectmobilerestaurant.core.utils.PrefsStorage
import com.example.projectmobilerestaurant.entities.User
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import javax.inject.Inject

class CreateAccountActivity : AppCompatActivity() {

    companion object {
        fun intent(context: Context): Intent =
            Intent(context, CreateAccountActivity::class.java)
    }

    @Inject
    lateinit var prefsStorage: PrefsStorage

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        if (prefsStorage.getIsAuthenticated()) {
            textNothingToShow.show()
            groupAuth.hide()
        } else {
            initDisposables()
            buttonContinue.setSafeOnClickListener {
                validateData()
            }
        }
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    private fun validateData() {
        val isFirstNameValid = checkForBlank(editFirstName, tilFirstName)
        val isSecondNameValid = checkForBlank(editSecondName, tilSecondName)
        val isPhoneValid = checkForBlank(editPhone, tilPhone)
        val isPasswordValid = checkForBlank(editPassword, tilPassword)

        val isValid = isFirstNameValid
                && isSecondNameValid
                && isPhoneValid
                && isPasswordValid
        if (isValid) {
            prefsStorage.setIsAuthenticated(true)
            prefsStorage.setUser(
                User(
                    editFirstName.text.toString(),
                    editSecondName.text.toString(),
                    editPhone.text.toString(),
                    editEmail.text.toString(),
                    editPassword.text.toString()
                )
            )
            setResult(Activity.RESULT_OK)
            finish()
        } else {
            showToast("Fill required fields")
        }
    }

    private fun initDisposables() {
        disposable.add(
            RxTextView.afterTextChangeEvents(editFirstName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.editable().toString().isNotBlank()) {
                        tilFirstName.error = ""
                    }
                }, {
                    it.printStackTrace()
                })
        )
        disposable.add(
            RxTextView.afterTextChangeEvents(editSecondName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.editable().toString().isNotBlank()) {
                        tilSecondName.error = ""
                    }
                }, {
                    it.printStackTrace()
                })
        )
        disposable.add(
            RxTextView.afterTextChangeEvents(editPhone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.editable().toString().isNotBlank()) {
                        tilPhone.error = ""
                    }
                }, {
                    it.printStackTrace()
                })
        )
        disposable.add(
            RxTextView.afterTextChangeEvents(editPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.editable().toString().isNotBlank()) {
                        tilPassword.error = ""
                    }
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun checkForBlank(
        editText: AppCompatEditText,
        til: TextInputLayout
    ): Boolean =
        if (editText.text.toString().isNotBlank()) {
            true
        } else {
            til.error = "This field is required"
            false
        }

}
