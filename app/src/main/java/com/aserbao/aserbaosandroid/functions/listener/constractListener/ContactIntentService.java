package com.aserbao.aserbaosandroid.functions.listener.constractListener;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.HashMap;

public class ContactIntentService extends IntentService {

    private static final String TAG = "ContactIntentService";
    private HashMap<Long ,Long> hashMap;

    public ContactIntentService() {
        super("contactIntentService");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "onHandleIntent被调用了");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "被创建了");
        hashMap = new HashMap<>();
        initHashMap();
        getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, new contactObserver(new Handler()));
    }

    int i = 0;
    public final class contactObserver extends ContentObserver {
        public contactObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.e(TAG, "contactObserver 被调用了");
            boolean needUpdate = isContactChanged();
            if (needUpdate) {
                Log.e(TAG, "onChange: 更新了" + selfChange + " 第"+ i );
                Intent intent = new Intent();
                onHandleIntent(intent);
            }
            i++;
        }
    }

    public void initHashMap() {
        ContentResolver _contentResolver = getContentResolver();
        Cursor cursor = _contentResolver.query(
            ContactsContract.RawContacts.CONTENT_URI, null, null, null,
            null);
        while (cursor.moveToNext()) {
            Long contactID = cursor.getLong(cursor
                .getColumnIndex(ContactsContract.RawContacts._ID));
            long contactVersion = cursor.getLong(cursor
                .getColumnIndex(ContactsContract.RawContacts.VERSION));
            hashMap.put(contactID, contactVersion);
        }
        cursor.close();
    }

    public boolean isContactChanged(){
        boolean theReturn = false;
        ContentResolver _contentResolver = getContentResolver();
        Cursor cursor = _contentResolver.query(
            ContactsContract.RawContacts.CONTENT_URI, null, null, null,
            null);
        while (cursor.moveToNext()) {

            Long contactID = cursor.getLong(cursor
                .getColumnIndex(ContactsContract.RawContacts._ID));
            long contactVersion = cursor.getLong(cursor
                .getColumnIndex(ContactsContract.RawContacts.VERSION));
            if (hashMap.containsKey(contactID)) {
                long version = hashMap.get(contactID);
                if (version != contactVersion) {
                    hashMap.put(contactID, contactVersion);
                    theReturn = true;
                }
            }else {
                hashMap.put(contactID, contactVersion);
                theReturn = true;
            }
        }
        cursor.close();
        return theReturn;
    }
}
