package com.cheiseproj.bik_krl.personalkotlin

import com.cheiseproj.bik_krl.personalkotlin.di.AppComponent
import com.cheiseproj.bik_krl.personalkotlin.di.DaggerAppComponent
import dagger.android.DaggerApplication
import timber.log.Timber

//class PersonalApp:Application(),HasActivityInjector {
//    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
//    override fun onCreate() {
//        super.onCreate()
//        AppInjector.init(this)
//        if (BuildConfig.DEBUG){
//            Timber.plant(Timber.DebugTree())
//        }
//    }
//
//
//    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
//}

class PersonalApp :DaggerApplication(){
    private val appComponent:AppComponent =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector() = appComponent

}