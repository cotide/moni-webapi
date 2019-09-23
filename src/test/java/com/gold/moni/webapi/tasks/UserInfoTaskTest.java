package com.gold.moni.webapi.tasks;

import com.gold.moni.webapi.domain.dto.UserInfoDto;
import com.gold.moni.webapi.tasks.UserInfoTask;
import com.gold.moni.webapi.base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserInfoTaskTest extends BaseTest {


    @Autowired
    protected UserInfoTask userInfoTask;

    @Test
    public void  get()
    {
        int id = 29;
        UserInfoDto dto =  userInfoTask.get(id);
        assert (dto!=null)
                :"UserInfoDto is null";
    }

    @Test
    public void  getList(){

        List<UserInfoDto> list = userInfoTask.getList();
        assert (list!=null):"list getList is null";
    }
}
