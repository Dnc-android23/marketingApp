package com.dnc.marketingapp.data.networkManager

import android.content.Context
import com.dnc.marketingapp.data.utils.isNetworkAvailable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManagerImplementation @Inject constructor(
        context: Context
) : NetworkManager {

    override var hasNetworkConnection: Boolean = isNetworkAvailable(context)
        set(value) {
            field = value
            onlineSubject.onNext(value)
        }

    override var onlineSubject: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(hasNetworkConnection)
}