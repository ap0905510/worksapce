package com.yw.greendao.utils;

import android.content.Context;

import com.yw.greendao.bean.DaoMaster;
import com.yw.greendao.bean.DaoSession;
import com.yw.greendao.bean.HxUsersDao;
import com.yw.greendao.bean.PhoneCardsDao;
import com.yw.greendao.dao.HMROpenHelper;

import org.greenrobot.greendao.database.Database;

/**
 * 作者：create by YW
 * 日期：2017.03.28 19:17
 * 描述：
 */
public class GreenDBUtils {

    // 数据库名
    private static final String DBNAME = "hxsdk.db";

    private static GreenDBUtils instance = null;

    private DaoSession mDaoSession;

    public static GreenDBUtils instance(Context context) {
        if (instance == null) {
            instance = new GreenDBUtils(context);
        }
        return instance;
    }

    private GreenDBUtils(Context context) {
        if (mDaoSession == null) {
            HMROpenHelper helper = new HMROpenHelper(context, DBNAME);
            Database db = helper.getWritableDb();
            mDaoSession = new DaoMaster(db).newSession();
        }
    }

    public PhoneCardsDao getPhoneCardsDao() {
        return mDaoSession.getPhoneCardsDao();
    }

    public HxUsersDao getHxUsersDao() {
        return mDaoSession.getHxUsersDao();
    }

}
