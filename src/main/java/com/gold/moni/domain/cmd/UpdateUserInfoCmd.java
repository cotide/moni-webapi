package com.gold.moni.domain.cmd;

import com.gold.moni.helper.common.Guard;
import lombok.Getter;
import lombok.Setter;


/**
 * 修改用户命令
 */
public class UpdateUserInfoCmd {

    @Getter
    private int id ;

    @Getter
    private String userName;


    /**
     * 构造函数
     * @param id id
     * @param userName 用户名
     */
    public UpdateUserInfoCmd(
            int id,
            String userName)
    {
        Guard.isNotZeroOrNegative(id,"id");
        Guard.isNotNullOrEmpty(userName,"userName");
        this.id = id;
        this.userName = userName;
    }


    @Getter
    @Setter
    private Integer login;
}