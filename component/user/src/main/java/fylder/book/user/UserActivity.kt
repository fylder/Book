package fylder.book.user

import android.Manifest
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.orhanobut.logger.Logger
import com.tbruyelle.rxpermissions2.RxPermissions
import fylder.book.lib.config.router.RouterConfig
import kotlinx.android.synthetic.main.activity_user.*
import java.io.File
import java.io.FileOutputStream


@Route(path = RouterConfig.USER_USER)
class UserActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        user_save.setOnClickListener(this)
        user_read.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            user_save -> {
                RxPermissions(this)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe {
                        if (it) {
                            save()
                        }
                    }
            }
            user_read -> {
                RxPermissions(this)
                    .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe {
                        if (it) {
                            show()
                        }
                    }
            }
        }
    }

    private fun save() {
        Logger.w("save start")
        val dView = window.decorView
        dView.isDrawingCacheEnabled = true
        dView.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(dView.drawingCache)
        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                val sdCardPath =
                    Environment.getExternalStorageDirectory().absolutePath + File.separator + "Book" + File.separator + "demo"
                val dirfile = File(sdCardPath)
                if (!dirfile.exists()) {
                    dirfile.mkdirs()
                }
                // 图片文件路径
                val filePath = sdCardPath + File.separator + "screenshot.png"
                val file = File(filePath)
                val os = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
                os.flush()
                os.close()
                Logger.w("save finish")
            } catch (e: Exception) {
                Logger.e("save error:${e.message}")
            }
        }
    }

    private fun show() {
        try {
            val filePath =
                Environment.getExternalStorageDirectory().absolutePath + File.separator + "Book" + File.separator + "demo" + File.separator + "screenshot.png"
            Logger.w("filePath: $filePath")
            val outputFile = File(filePath)

//            if (!outputFile.parentFile.exists()) {
////                outputFile.parentFile.mkdir()
//                Logger.i("file is not exists")
//            } else {
//                Logger.i("file size:${outputFile.length()}")
//            }
            val authority = application.packageName + ".provider"
            val contentUri = FileProvider.getUriForFile(this, authority, outputFile)
            Logger.i("path: $contentUri")
            Glide.with(this)
                .load(contentUri)
                .placeholder(R.drawable.ic_book_svg)
                .error(R.drawable.ic_icecream)
                .into(user_img)
        } catch (e: Exception) {
            e.printStackTrace()
            Logger.e("error:${e.message}")
            Toast.makeText(this, "error:${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
