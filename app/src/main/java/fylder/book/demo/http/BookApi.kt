package fylder.book.demo.http

import fylder.book.lib.http.entity.GithubApiEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface BookApi {


    @GET("users/fylder")
    fun user(): Observable<GithubApiEntity>
}