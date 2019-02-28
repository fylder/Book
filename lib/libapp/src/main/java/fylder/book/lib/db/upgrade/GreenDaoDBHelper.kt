package fylder.book.lib.db.upgrade

import android.content.Context
import fylder.book.lib.db.dao.BookDao
import fylder.book.lib.db.dao.DaoMaster
import fylder.book.lib.db.dao.DaoSession
import org.greenrobot.greendao.query.QueryBuilder

/**
 * Created by fylder on 2018/6/26.
 */
class GreenDaoDBHelper {

    // 数据库名
    private val DBNAME = "book-"

    private val mContext: Context
    private var mDaoSession: DaoSession? = null

    companion object {

        @Volatile
        var manager: GreenDaoDBHelper? = null

        fun getInstance(context: Context): GreenDaoDBHelper {
            if (manager == null) {
                synchronized(GreenDaoDBHelper::class) {
                    if (manager == null) {
                        manager = GreenDaoDBHelper(context)
                    }
                }
            }
            return manager!!
        }
    }

    constructor(context: Context) {
        mContext = context.applicationContext
        initDBDao()
    }

    fun initDBDao() {
        try {
            if (mDaoSession == null) {
                val helper = HMROpenHelper(mContext, DBNAME)
                val db = helper.writableDb
                mDaoSession = DaoMaster(db).newSession()
                QueryBuilder.LOG_SQL = true
                QueryBuilder.LOG_VALUES = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                synchronized(mDaoSession!!) {
                    if (mDaoSession == null) {
                        val helper = HMROpenHelper(mContext, DBNAME)
                        val db = helper.writableDb
                        mDaoSession = DaoMaster(db).newSession()
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }


    fun setDaoSession(mDaoSession: DaoSession) {
        this.mDaoSession = mDaoSession
        initDBDao()
    }


    fun getBookDao(): BookDao {
        initDBDao()
        return mDaoSession!!.bookDao
    }

}