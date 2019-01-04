package id.jmlcode.sample.model.db;


import java.util.List;

import id.jmlcode.sample.model.bean.realm.TestOneMillion;

public interface DBHelper {

    /*void insertReminder(ReminderRealmBean RminderRealmBean);
    void updateReminder(ReminderRealmBean RminderRealmBean);
    void deleteReminder(int id);
    List<ReminderRealmBean> selectReminderById(Integer clientId);
    List<ReminderRealmBean> selectReminderById(Integer clientId, Date date);
    ReminderRealmBean selectByIdAlram(Integer idAlarm);*/

    void insertOneMillionData(TestOneMillion testOneMillion);
    List<TestOneMillion> selectOneMillion();
}
