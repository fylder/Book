package fylder.book.lib.config.router

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService

/**
 * create by fylder on 2018/7/23
 **/
//path里面的内容可以任意，注意两个斜杠就行
@Route(path = "/router/degrade")
class DegradeServiceImpl : DegradeService {

    override fun init(context: Context?) {

    }

    //失败的时候处理，注意：如果在navigation时候没有传递context，这个方法的context会是空的
    override fun onLost(context: Context?, postcard: Postcard?) {
        Toast.makeText(context, "找不到路径" + postcard!!.path, Toast.LENGTH_SHORT).show()
        Log.w("123", postcard.path)
    }

}