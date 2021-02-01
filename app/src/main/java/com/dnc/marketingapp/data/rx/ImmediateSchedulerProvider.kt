package com.dnc.marketingapp.data.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ImmediateSchedulerProvider: SchedulersProvider {
    override fun ui(): Scheduler =  Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.newThread()
}