package com.gold.moni.webapi.db;

import com.gold.moni.webapi.domain.entity.UserInfo;
import com.gold.moni.webapi.base.BaseTest;
import io.github.cotide.dapper.Database;
import io.github.cotide.dapper.repository.inter.IRepository;
import org.junit.Test;

public class DbTest extends BaseTest {

    @Test
    public  void getById()
    {
        Database db =  new Database(getDataSource());
        IRepository<UserInfo> userInfoIRepository =  db.getRepository(UserInfo.class);
        UserInfo userInfo =   userInfoIRepository.getById(29);
    }

}
