package com.gold.moni.domain.entity;

import io.github.cotide.dapper.basic.domain.Entity;
import io.github.cotide.dapper.core.attr.Column;
import io.github.cotide.dapper.core.attr.Ignore;
import io.github.cotide.dapper.core.attr.PrimaryKey;
import io.github.cotide.dapper.core.attr.Table;

import java.util.Date;

/**
 * 用户信息
 */
@lombok.Getter
@lombok.Setter
@Table("user_info")
public class UserInfo extends Entity {

    @PrimaryKey("user_id")
    private int id;

    @Column("user_Name")
    private String userName;

    @Column("password")
    private String password;

    private int login;

    @Column("create_time")
    private Date createTime;

    @Ignore
    private String other;
}
