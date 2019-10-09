package com.examples.joshuayingwhat.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DataBaseManager {

    private DaoSession daoSession = null;
    private UserProfileDao mDao = null;

    public DataBaseManager init(Context context) {
        initDao(context);
        return this;
    }

    public static DataBaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final DataBaseManager INSTANCE = new DataBaseManager();
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        mDao = daoSession.getUserProfileDao();
    }

    public final UserProfileDao getmDao() {
        return mDao;
    }
}
