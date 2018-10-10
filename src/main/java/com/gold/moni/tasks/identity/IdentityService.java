package com.gold.moni.tasks.identity;

import com.gold.moni.domain.dto.UserInfoDto;
import com.gold.moni.domain.entity.UserInfo;
import com.gold.moni.helper.exception.PowerException;
import com.gold.moni.tasks.base.BaseTask;
import com.gold.moni.webapi.filter.jwt.data.JwtUser;
import io.github.cotide.dapper.Database;
import io.github.cotide.dapper.query.Sql;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author cotide
 */

@Service
public class IdentityService extends BaseTask implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(
            String userName) throws UsernameNotFoundException {

         Database db = openDb();
         UserInfoDto user =  db.getSqlQuery().getDto(UserInfoDto.class,
                Sql.builder().select(
                        "user_id as userId," +
                                "user_name as userName," +
                                "login," +
                                "create_time as createTime")
                        .from(UserInfo.class)
                        .where("user_name = @0",userName));
         if(user==null)
         {
             throw  new PowerException("无效用户");
         }
         return create(user);
    }

    // region Helper
    private JwtUser create(UserInfoDto user) {
        return new JwtUser(
                user.getUserId(),
                user.getUserName(),
                new Date());
    }
    // endregion
}
