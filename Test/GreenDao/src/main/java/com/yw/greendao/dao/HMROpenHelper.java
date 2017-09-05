package com.yw.greendao.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yw.greendao.bean.DaoMaster;
import com.yw.greendao.bean.HxUsersDao;
import com.yw.greendao.bean.PhoneCardsDao;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

/**
 * 作者：create by YW
 * 日期：2017.03.27 16:57
 * 描述：
 */
public class HMROpenHelper extends DaoMaster.DevOpenHelper {

    /**
     * 初始化一个AbSDDBHelper.
     *  @param context      应用context
     * @param name         数据库名
     */
    /*public HMROpenHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }*/

    /**
     * 初始化一个AbSDDBHelper.
     *
     * @param context      应用context
     * @param name         数据库名
     */
    public HMROpenHelper(Context context, String name) {
        super(context, name);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新
        MigrationHelper.migrate(db, HxUsersDao.class, PhoneCardsDao.class);
    }

}
