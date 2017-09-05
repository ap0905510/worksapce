package com.yw.greendao;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yw.greendao.bean.DaoSession;
import com.yw.greendao.bean.HxUsers;
import com.yw.greendao.bean.HxUsersDao;
import com.yw.greendao.bean.PhoneCards;
import com.yw.greendao.bean.PhoneCardsDao;
import com.yw.greendao.utils.GreenDBUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static final String TAG = "YW";
    private PhoneCardsDao mPhoneCardsDao;
    private HxUsersDao mHxUsersDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the card DAO

        mPhoneCardsDao = GreenDBUtils.instance(this).getPhoneCardsDao();
        mHxUsersDao = GreenDBUtils.instance(this).getHxUsersDao();
    }

    /**
     * 增加插入单条
     *
     * @param view
     */
    public void insertSingle(View view) {
        PhoneCards card = new PhoneCards();
        card.setMsisdn("4000");
        card.setName("i");
        mPhoneCardsDao.insert(card);
        Log.e(TAG, "Inserted single card, ID: " + card.getId());

        List<HxUsers> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HxUsers user = new HxUsers();
            user.setMsisdn("4000");
            user.setNname("i");
            Log.e(TAG, "Inserted new user, ID: " + card.getId());
            list.add(user);
        }
        mHxUsersDao.insertInTx(list);
        Log.e(TAG, "Inserted new HxUsers List: " + list.toString());
    }

    /**
     * 插入批量数据
     * @param view
     */
    public void insertAll(View view) {
        List<PhoneCards> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PhoneCards card = new PhoneCards();
            card.setMsisdn("4000");
            card.setName("i");
            Log.e(TAG, "Inserted new card, ID: " + card.getId());
            list.add(card);
        }
        mPhoneCardsDao.insertInTx(list);
        Log.e(TAG, "Inserted new List: " + list.toString());
    }

    /**
     * 删除特指数据
     * @param view
     */
    public void deleteSingle(View view) {
        PhoneCards card = new PhoneCards();
        card.setId(6L);
        card.setMsisdn("4000");
        card.setName("i");
        card.setSex("男");
        mPhoneCardsDao.delete(card);
        Log.e(TAG, "delete single card, ID: " + card.getId());
    }

    /**
     * 删除全部数据
     * @param view
     */
    public void deleteAll(View view) {
        mPhoneCardsDao.deleteAll();
        Log.e(TAG, "Delete All, ID: ");
    }

    /**
     * update 单条数据
     * @param view
     */
    public void updateOne(View view) {
        PhoneCards card = new PhoneCards();
        card.setId(1L);
        card.setMsisdn("4000");
        card.setName("i");
        card.setSex("男");
        mPhoneCardsDao.update(card);
        Log.e(TAG, "update single card, ID: " + card.getId());
    }

    /**
     * update 所有数据
     * @param view
     */
    public void updateAll(View view) {
        List<PhoneCards> data = getData();
        if (data == null || data.size() <= 0) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            if (i == 7 || i == 3) {
                data.get(i).setName("YW");
            }
        }
        mPhoneCardsDao.updateInTx(data);
    }

    /**
     * 拉取特定条件数据
     * @param view
     */
    public void getSingle(View view) {
        List<PhoneCards> cardsList = mPhoneCardsDao.queryRaw("where name = ?", new String[]{"i"});
        if (cardsList == null || cardsList.size() == 0) {
            Log.e(TAG, "查询数据为空");
            return;
        }
        for (int i = 0; i < cardsList.size(); i++) {
            Log.e(TAG, "查询数据列表：" + cardsList.get(i).toString());
        }
    }

    /**
     * 拉取全部数据
     * @param view
     */
    public void getAll(View view) {

        List<PhoneCards> cardsList = mPhoneCardsDao.loadAll();

        if (cardsList == null || cardsList.size() == 0) {
            Log.e(TAG, "查询数据为空");
            return;
        }
        for (int i = 0; i < cardsList.size(); i++) {
            Log.e(TAG, "查询数据列表：" + cardsList.get(i).toString());
        }
    }

    private List<PhoneCards> getData() {
        List<PhoneCards> cardsList = mPhoneCardsDao.loadAll();

        if (cardsList == null || cardsList.size() == 0) {
            Log.e(TAG, "查询数据为空");
            return null;
        }
        for (int i = 0; i < cardsList.size(); i++) {
            Log.e(TAG, "查询数据列表：" + cardsList.get(i).toString());
        }
        return cardsList;
    }
}
