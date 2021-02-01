package com.dnc.marketingapp.data.networkManager

import io.reactivex.subjects.BehaviorSubject

interface NetworkManager {
    var hasNetworkConnection : Boolean

    val onlineSubject : BehaviorSubject<Boolean>
}