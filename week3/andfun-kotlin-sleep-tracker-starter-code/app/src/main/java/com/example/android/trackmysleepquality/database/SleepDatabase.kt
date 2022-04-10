/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 1 Create an abstract class that extends RoomDatabase
@Database(entities = [SleepNight::class],version = 2, exportSchema = false)
//要聲明Database的entity及版本號,每當有更新版本都要升級版本號,不然會有錯,exportSchema導出模式,默認是ture,會保存到文件夾中,就會有版本歷史紀錄
abstract class SleepDatabase : RoomDatabase() {
    // 2 Declare(聲明) an abstract value of type SleepNightDao
    abstract val sleepDatabaseDao : SleepDatabaseDao
    // 3 Declare a companion(伴生OR伴隨=static) object
    companion object{
        // 4 Declare a @Volatile INSTANCE variable
        @Volatile  //確保INSTANCE的值始終是最新的
        private var INSTANCE : SleepDatabase? = null
        // 5 Define a getInstance() method with a synchronized(同步的) block
        fun getInstance (context: Context) : SleepDatabase {
            // 6 Inside the synchronized block
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,SleepDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        //遷移策略,通常在創建database時要提供,如果database架構被改變(例如更改列的數量或類型),
                        // 需要一個將現有table和data轉換為新架構的方法,舊有資料才不會流失,
                        // 這邊用的是全部刪除重新建立
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}







