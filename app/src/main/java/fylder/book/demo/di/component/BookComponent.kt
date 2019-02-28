package fylder.book.demo.di.component

import dagger.BindsInstance
import dagger.Component
import fylder.book.demo.BookActivity
import fylder.book.demo.di.moudle.BookModule
import fylder.book.demo.mvp.contract.BookContract
import fylder.book.lib.di.AppComponent
import fylder.book.lib.di.common.CommonModule
import fylder.book.lib.di.common.RetrofitModule
import fylder.book.lib.di.scope.ActivityScope

@ActivityScope
@Component(
    modules = [BookModule::class, CommonModule::class, RetrofitModule::class],
    dependencies = [AppComponent::class]
)
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