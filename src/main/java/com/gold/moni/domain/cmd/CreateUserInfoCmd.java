package com.gold.moni.domain.cmd;

import com.gold.moni.helper.common.Guard;
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
