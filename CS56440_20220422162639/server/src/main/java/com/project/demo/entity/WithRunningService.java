package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *带跑服务：(WithRunningService)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "WithRunningService")
public class WithRunningService implements Serializable {

    //WithRunningService编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "with_running_service_id")
    private Integer with_running_service_id;
    // 代拿物品
    @Basic
    private String take_goods_on_behalf_of_others;
    // 物品位置
    @Basic
    private String item_location;
    // 收取地址
    @Basic
    private String receiving_address;
    // 发布学生
    @Basic
    private Integer release_students;
    // 学生姓名
    @Basic
    private String student_name;
    // 联系电话
    @Basic
    private String contact_number;
    // 带跑费用
    @Basic
    private Integer running_expenses;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
