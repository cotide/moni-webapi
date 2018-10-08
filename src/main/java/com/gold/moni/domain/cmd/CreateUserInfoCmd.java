package com.gold.moni.domain.cmd;

import io.github.cotide.dapper.core.utility.Guard;
import lombok.Getter;

/**
 * 创建用户命令
 */
public class CreateUserInfoCmd {

   @Getter
   private   String userName;

    /**
     * 构造函数
     * @param userName 用户名
     */
   public CreateUserInfoCmd(String userName)
   {
       Guard.isNotNullOrEmpty(userName,"username");
       this.userName = userName;
   }
}
