package com.example.projectmobilerestaurant.ui.notifications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectmobilerestaurant.R
import com.example.projectmobilerestaurant.core.extensions.getAppComponent
import com.example.projectmobilerestaurant.core.extensions.hide
import com.example.projectmobilerestaurant.core.extensions.setSafeOnClickListener
import com.example.projectmobilerestaurant.core.extensions.show
import com.example.projectmobilerestaurant.core.utils.CREATE_ACCOUNT_REQUEST_CODE
import com.example.projectmobilerestaurant.core.utils.PrefsStorage
import com.example.projectmobilerestaurant.ui.createaccount.CreateAccountActivity
import kotlinx.android.synthetic.main.fragment_notifications.*
import javax.inject.Inject

class NotificationsFragment : Fragment() {

    @Inject
    lateinit var prefsStorage: PrefsStorage

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_notifications, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (prefsStorage.getIsAuthenticated()) {
            groupAuth.hide()
            textNothingToShow.show()
        } else {
            groupAuth.show()
            textNothingToShow.hide()

            buttonCreateAccount.setSafeOnClickListener {
                requireActivity().startActivityForResult(
                    Intent(requireContext(), CreateAccountActivity::class.java),
                    CREATE_ACCOUNT_REQUEST_CODE
                )
            }
        }
    }

}
