package fylder.book.lib.db.upgrade

import android.content.Context
import fylder.book.lib.db.dao.BookDao
import fylder.book.lib.db.dao.DaoMaster
import org.greenrobot.greendao.database.Database

/**
 * 数据库的升级
 * create by fylder on 2018/6/13
 **/
class HMROpenHelper constructor(context: Context, name: String) : DaoMaster.DevOpenHelper(context, name) {

    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
        //操作数据库的更新
        MigrationHelper.migrate(db, BookDao::class.java)
    }
}