package com.gold.moni.tasks.base;

import com.gold.moni.helper.exception.DbErrorException;
import com.gold.moni.helper.spring.SpringHelper;
import io.github.cotide.dapper.Database;

import javax.sql.DataSource;

public abstract class BaseTask {


    protected Database openDb(){
        DataSource dbSource = getDataSource(DbName.Main);
        return new Database(dbSource);
    }


    protected Database openDb(DbName dbName){
        DataSource dbSource = getDataSource(dbName);
        return new Database(dbSource);
    }


    // region Helper

    /**
     * 获取数据源
     * @param dbName 数据库实例
     * @return
     */
    private DataSource getDataSource(DbName dbName)
    {

        switch (dbName)
        {
            case Main:
                DataSource dbSource =
                        SpringHelper.getBean("mainDataSource", DataSource.class);
                return dbSource;
        }
        throw  new DbErrorException("无效的数据库驱动");

    }
    // endregion


    /**
     * 数据库实例
     */
    protected enum DbName{
        /**
         * 主库
         */
        Main
    }
}
