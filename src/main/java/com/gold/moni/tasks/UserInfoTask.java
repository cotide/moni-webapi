package com.gold.moni.tasks;

import com.gold.moni.domain.cmd.CreateUserInfoCmd;
import com.gold.moni.domain.cmd.UpdateUserInfoCmd;
import com.gold.moni.domain.dto.UserInfoDto;
import com.gold.moni.domain.entity.UserInfo;
import com.gold.moni.helper.common.Guard;
import com.gold.moni.tasks.base.BaseTask;
import io.github.cotide.dapper.Database;
import io.github.cotide.dapper.query.Sql;
import io.github.cotide.dapper.repository.inter.IRepository;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Component
public class UserInfoTask extends BaseTask {

    public UserInfoDto get(int id) {

        Database db = openDb();
        return  db.getSqlQuery().getDto(UserInfoDto.class,
                Sql.builder()
                        .select(
                        "user_id as userId," +
                        "user_name as userName," +
                        "login," +
                        "create_time as createTime," +
                        "status," +
                        "level," +
                        "`group`")
                .from(UserInfo.class)
                .where("user_id=@0",id));
    }

    public UserInfoDto getByUserName(String userName)
    {
        Database db = openDb();
        return db.getSqlQuery().getDto(UserInfoDto.class,
                Sql.builder()
                        .select(
                        "user_id as userId," +
                        "user_name as userName," +
                        "login," +
                        "create_time as createTime," +
                        "status," +
                        "level," +
                        "`group`")
                .from(UserInfo.class)
                .where("user_name = @0",userName));
    }


    public List<UserInfoDto> getList(){

        Database db = openDb();
        return db.getSqlQuery().getDtoList(UserInfoDto.class,
                Sql.builder()
                        .select(
                        "user_id as userId," +
                        "user_name as userName," +
                        "login," +
                        "create_time as createTime," +
                        "status," +
                        "level," +
                        "`group`")
                .from(UserInfo.class));
    }


    public void add(CreateUserInfoCmd cmd)
    {
        Database db = openDb();
        UserInfo domain = new UserInfo();
        domain.setUserName(cmd.getUserName());
        domain.setLogin(10086);
        domain.setPassword("123456");
        domain.setCreateTime(new Date());
        db.getRepository(UserInfo.class).create(domain);
    }


    public void update(UpdateUserInfoCmd cmd)
    {
        Database db = openDb();
        IRepository<UserInfo> userInfoIRepository = db.getRepository(UserInfo.class);
        UserInfo userInfo =  userInfoIRepository.getById(cmd.getId());
        Guard.isNotNull(userInfo,"userInfo");
        userInfo.setUserName(cmd.getUserName());

        if(cmd.getLogin()!=null && cmd.getLogin()>0)
        {
            userInfo.setLogin(cmd.getLogin());
        }
        userInfoIRepository.update(userInfo);
    }
}