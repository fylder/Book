package fylder.book.lib.config.router

/**
 *
 * ARouter
 *
 * 一个module对应一个一级路由,否则跳转失败
 * 举个栗子
 *  app:    /app/[**]
 *  user:   /user/[**]
 *
 * create by fylder on 2018/7/23.
 */
object RouterConfig {

    //group
    const val GROUP_APP = "app"
    const val GROUP_USER = "user"


    //app
    const val APP_MAIN = "/$GROUP_APP/main"
    const val APP_BOOK = "/$GROUP_APP/book"

    //user
    const val USER_LOGIN = "/$GROUP_USER/login"

}