/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.cardemulation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Utility class for persisting account numbers to disk.
 *
 * <p>The default SharedPreferences instance is used as the backing storage. Values are cached
 * in memory for performance.
 *
 * <p>This class is thread-safe.
 */
public class AccountStorage {
    private static final String PREF_ACCOUNT_NUMBER = "account_number";
	private static final String PREF_ACCOUNT_NUMBER_READS = "account_number_reads";
	private static final String PREF_ACCOUNT_NUMBER_WRITES = "account_number_writes";
    private static final String DEFAULT_ACCOUNT_NUMBER = "00000000";
    private static final String TAG = "AccountStorage";
    private static String sAccount = null;
    private static final Object sAccountLock = new Object();
	private static int accountReads = -1;
	private static int accountWrites = -1;
	private static final Object sAccountLockReads = new Object();
	private static final Object sAccountLockWrites = new Object();

    public static void SetAccount(Context c, String s) {
        synchronized(sAccountLock) {
            Log.i(TAG, "Setting account number: " + s);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
            prefs.edit().putString(PREF_ACCOUNT_NUMBER, s).commit();
            sAccount = s;
        }
    }

    public static String GetAccount(Context c) {
        synchronized (sAccountLock) {
            if (sAccount == null) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
                String account = prefs.getString(PREF_ACCOUNT_NUMBER, DEFAULT_ACCOUNT_NUMBER);
                sAccount = account;
            }
            return sAccount;
        }
    }
	
	
	
	public static void SetAccountReads(Context c, int s) {
        synchronized(sAccountLockReads) {
            Log.i(TAG, "Setting account number reads: " + s);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
            prefs.edit().putInt(PREF_ACCOUNT_NUMBER_READS, s).commit();
            accountReads = s;
        }
    }

    public static int GetAccountReads(Context c) {
        synchronized (sAccountLockReads) {
            if (accountReads == -1) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
                accountReads = prefs.getInt(PREF_ACCOUNT_NUMBER_READS, 0);
            }
            return accountReads;
        }
    }
	
	public static void SetAccountWrites(Context c, int s) {
        synchronized(sAccountLockWrites) {
            Log.i(TAG, "Setting account number writes: " + s);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
            prefs.edit().putInt(PREF_ACCOUNT_NUMBER_WRITES, s).commit();
            accountWrites = s;
        }
    }

    public static int GetAccountWrites(Context c) {
        synchronized (sAccountLockWrites) {
            if (accountWrites == -1) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
                accountWrites = prefs.getInt(PREF_ACCOUNT_NUMBER_WRITES, 0);
            }
            return accountWrites;
        }
    }
	
}
