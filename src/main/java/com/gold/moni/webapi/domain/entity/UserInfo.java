package com.gold.moni.webapi.domain.entity;

import com.gold.moni.webapi.domain.entity.enums.EnumGroup;
import com.gold.moni.webapi.domain.entity.enums.EnumUserStatus;
import com.gold.moni.webapi.domain.entity.enums.EnumVipLevel;
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

    @Column("status")
    private EnumUserStatus status;

    @Column("level")
    private EnumVipLevel level;

    @Column("`group`")
    private EnumGroup group;

    @Column("create_time")
    private Date createTime;

    @Ignore
    private String other;
}
