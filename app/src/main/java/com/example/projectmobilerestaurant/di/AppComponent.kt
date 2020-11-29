package com.example.projectmobilerestaurant.di

import android.content.Context
import com.example.projectmobilerestaurant.ui.AccountInfoActivity
import com.example.projectmobilerestaurant.ui.MainActivity
import com.example.projectmobilerestaurant.ui.createaccount.CreateAccountActivity
import com.example.projectmobilerestaurant.ui.details.futuretimes.FutureTimesActivity
import com.example.projectmobilerestaurant.ui.details.reserve.ReserveFragment
import com.example.projectmobilerestaurant.ui.details.reserve.TableInfoBottomSheetFragment
import com.example.projectmobilerestaurant.ui.home.HomeFragment
import com.example.projectmobilerestaurant.ui.makereserve.MakeReservationActivity
import com.example.projectmobilerestaurant.ui.notifications.NotificationsFragment
import com.example.projectmobilerestaurant.ui.reservations.ReservationsFragment
import com.example.projectmobilerestaurant.ui.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(makeReservationActivity: MakeReservationActivity)
    fun inject(createAccountActivity: CreateAccountActivity)
    fun inject(accountInfoActivity: AccountInfoActivity)
    fun inject(futureTimesActivity: FutureTimesActivity)

    fun inject(reserveFragment: ReserveFragment)
    fun inject(tableInfoBottomSheetFragment: TableInfoBottomSheetFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(reservationsFragment: ReservationsFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(notificationsFragment: NotificationsFragment)

}