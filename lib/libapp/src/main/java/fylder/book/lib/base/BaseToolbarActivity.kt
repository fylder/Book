package fylder.book.lib.base;

import android.os.Build
import android.os.Bundle


/**
 * 包含toolbar的基类，方便设置各种属性并添加沉浸
 * Created by Lauzy on 2017/1/10.
 */

abstract class BaseToolbarActivity<P : BasePresenter<out BaseView>> : BaseActivity<BasePresenter<out BaseView>>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBar()
        setWindowAnim()

    }

    private fun setWindowAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations()
        }
    }

    protected fun setupWindowAnimations() {
    }

    protected fun setToolBar() {
//        mToolBar=findViewById(R.id.normal_toolbar);
//        mToolBar.getLayoutParams().height += ScreenUtils.getStatusHeight(AutoMateApplication.getInstance());
//        mToolBar.setPadding(0, ScreenUtils.getStatusHeight(AutoMateApplication.getInstance()), 0, 0);
//        setSupportActionBar(mToolBar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        mToolBar.setLeftImageClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}
