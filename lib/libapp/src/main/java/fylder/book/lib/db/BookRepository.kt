package fylder.book.lib.db

import android.content.Context
import fylder.book.lib.db.dao.BookDao
import fylder.book.lib.db.entity.Book
import fylder.book.lib.db.upgrade.GreenDaoDBHelper

class BookRepository {


    /**
     * 添加一条
     * return 是否插入成功
     */
    fun add(context: Context, Book: Book): Boolean {
        return if (!queryHas(context, Book)) {
            GreenDaoDBHelper.getInstance(context.applicationContext).getBookDao()
                .insert(Book)
            true
        } else {
            false
        }
    }

    /**
     * 查询是否存在
     */
    fun queryHas(context: Context, book: Book): Boolean {
        val datas: ArrayList<Book> =
            GreenDaoDBHelper.getInstance(context.applicationContext).getBookDao().queryBuilder()
                .where(
                    BookDao.Properties.Name.eq(book.name)
                )
                .list() as ArrayList<Book>
        return datas.size > 0
    }

    /**
     * 查询一条
     */
    fun query(context: Context, name: String): Book? {
        val datas: ArrayList<Book> =
            GreenDaoDBHelper.getInstance(context.applicationContext).getBookDao().queryBuilder()
                .where(BookDao.Properties.Name.eq(name))
                .list() as ArrayList<Book>
        if (datas.size > 0) {
            return datas.first()
        } else {
            return null
        }

    }

    /**
     * 查询所有
     */
    fun queryAll(context: Context): ArrayList<Book> {
        val datas: ArrayList<Book> =
            GreenDaoDBHelper.getInstance(context.applicationContext).getBookDao().queryBuilder()
                .list() as ArrayList<Book>
        return datas
    }

    /**
     * 更新数据
     */
    fun update(context: Context, book: Book) {
        GreenDaoDBHelper.getInstance(context.applicationContext).getBookDao().update(book)

    }

    /**
     * 删除一条记录
     */
    fun deleteByName(context: Context, name: String): Boolean {
        val data = query(context.applicationContext, name)
        return if (data == null) {
            false
        } else {
            if (data.id == 0L) {
                false
            } else {
                GreenDaoDBHelper.getInstance(context.applicationContext).getBookDao()
                    .delete(data)
                true
            }
        }
    }

}