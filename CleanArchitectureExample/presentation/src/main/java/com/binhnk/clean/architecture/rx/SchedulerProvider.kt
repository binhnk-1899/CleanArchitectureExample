package com.binhnk.clean.architecture.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler
}