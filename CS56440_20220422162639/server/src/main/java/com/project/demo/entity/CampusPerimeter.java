package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *校园周边：(CampusPerimeter)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CampusPerimeter")
public class CampusPerimeter implements Serializable {

    //CampusPerimeter编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campus_perimeter_id")
    private Integer campus_perimeter_id;
    // 店铺名称
    @Basic
    private String store_name;
    // 店铺地址
    @Basic
    private String store_address;
    // 店家电话
    @Basic
    private String store_telephone;
    // 店铺封面
    @Basic
    private String shop_cover;
    // 经营范围
    @Basic
    private String nature_of_business;
    // 店铺详情
    @Basic
    private String shop_details;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
