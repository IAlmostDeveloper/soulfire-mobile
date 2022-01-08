package ru.ialmostdeveloper.soulfire_mobile.di

import android.content.Context
import ru.ialmostdeveloper.soulfire_mobile.Activities.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity?)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent?

        @BindsInstance
        fun context(context: Context?): Builder?

        @BindsInstance
        fun appModule(appModule: AppModule?): Builder?
    }
}