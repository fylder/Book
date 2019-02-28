package fylder.book.demo.di.component

import dagger.BindsInstance
import dagger.Component
import fylder.book.demo.BookActivity
import fylder.book.demo.di.AppComponent
import fylder.book.demo.di.moudle.BookModule
import fylder.book.demo.di.common.RetrofitModule
import fylder.book.demo.di.scope.ActivityScope
import fylder.book.demo.mpv.contract.BookContract

@ActivityScope
@Component(modules = [BookModule::class, RetrofitModule::class], dependencies = [AppComponent::class])
interface BookComponent {

    fun inject(activity: BookActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun view(view: BookContract.View): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): BookComponent
    }
}