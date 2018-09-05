package com.gold.moni.webapi.db;

import com.gold.moni.domain.entity.UserInfo;
import com.gold.moni.webapi.base.BaseTest;
import io.github.cotide.dapper.Database;
import io.github.cotide.dapper.core.unit.info.TableInfo;
import io.github.cotide.dapper.query.Sql;
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


    @Test
    public void  getPrimaryKey(){

      String primaryKey =   TableInfo.fromPoco(UserInfo.class).getPrimaryKey();
    }

    @Test
    public void  getSql(){
        Sql sql = Sql.builder().select().from(UserInfo.class).where(
                String.format("%s = @0",TableInfo.fromPoco(UserInfo.class).getPrimaryKey()),
                29);
        System.out.println(sql);
    }
}
