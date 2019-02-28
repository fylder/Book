package fylder.book.lib.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 注: greenDao目前不支持.kt文件类型的注解
 * 不支持int,double等基本类型，需Integer,Double
 */
@Entity
public class Book {

    // 主键,autoincrement设置自增
    @Id(autoincrement = true)
    public Long id;

    @Property(nameInDb = "name")
    public String name;

    @Property(nameInDb = "price")
    public Integer price;

    @Generated(hash = 1120574162)
    public Book(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
