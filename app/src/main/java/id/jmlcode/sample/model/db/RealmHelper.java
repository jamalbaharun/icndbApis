package id.jmlcode.sample.model.db;


import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import id.jmlcode.sample.model.bean.realm.TestOneMillion;
import id.jmlcode.sample.util.Constants;
import id.jmlcode.sample.util.EncodeRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmHelper implements DBHelper {

    private static final String DB_NAME = "local_app.realm";

    private Realm mRealm;

    @Inject
    public RealmHelper() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .encryptionKey(EncodeRealm.encrypt(Constants.KEY64))
                .name(DB_NAME)
                .build());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void insertOneMillionData(final TestOneMillion testOneMillion) {
        try { // I could use try-with-resources here
            mRealm = Realm.getDefaultInstance();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(testOneMillion);
                }
            });
        } finally {
            if(mRealm != null) {
                mRealm.close();
            }
        }
    }

    @Override
    public List<TestOneMillion> selectOneMillion() {
        RealmResults<TestOneMillion> results = mRealm.where(TestOneMillion.class)
                .findAll();
        return mRealm.copyFromRealm(results);
    }


    /*@Override
    public void insertReminder(ReminderRealmBean RminderRealmBean) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(RminderRealmBean);
        mRealm.commitTransaction();
    }

    @Override
    public void updateReminder(ReminderRealmBean reminderRealmBean) {
        ReminderRealmBean data = mRealm.where(ReminderRealmBean.class).equalTo("reminderId", reminderRealmBean.getReminderId())
                .findFirst();
        mRealm.beginTransaction();
        if (data != null) {
            data.deleteFromRealm();
        }
        mRealm.copyToRealm(reminderRealmBean);
        mRealm.commitTransaction();
    }

    @Override
    public void deleteReminder(int id) {
        ReminderRealmBean data = mRealm.where(ReminderRealmBean.class).equalTo("reminderId",id).findFirst();
        mRealm.beginTransaction();
        if (data != null) {
            data.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    @Override
    public List<ReminderRealmBean> selectReminderById(Integer clientId) {
        RealmResults<ReminderRealmBean> results = mRealm.where(ReminderRealmBean.class)
                .equalTo("clientId", clientId)
                .findAllSorted("reminderId",Sort.DESCENDING);
        return mRealm.copyFromRealm(results);
    }

    @Override
    public List<ReminderRealmBean> selectReminderById(Integer clientId, Date date) {
        RealmResults<ReminderRealmBean> results = mRealm.where(ReminderRealmBean.class)
                .equalTo("clientId", clientId)
                .lessThan("reminderDateTime", date)
                .findAllSorted("reminderId",Sort.DESCENDING);
        return mRealm.copyFromRealm(results);
    }

    @Override
    public ReminderRealmBean selectByIdAlram(Integer idAlarm) {
        ReminderRealmBean bean = mRealm.where(ReminderRealmBean.class).equalTo("reminderId", idAlarm).findFirst();
        if (bean == null)
            return null;
        return mRealm.copyFromRealm(bean);
    }*/
}
