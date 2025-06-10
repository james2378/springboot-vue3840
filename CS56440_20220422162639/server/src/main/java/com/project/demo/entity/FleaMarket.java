package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *跳蚤市场：(FleaMarket)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "FleaMarket")
public class FleaMarket implements Serializable {

    //FleaMarket编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flea_market_id")
    private Integer flea_market_id;
    // 物品名称
    @Basic
    private String item_name;
    // 物品类型
    @Basic
    private String item_type;
    // 物品图片
    @Basic
    private String item_picture;
    // 物品成色
    @Basic
    private String article_fineness;
    // 物品价格
    @Basic
    private Integer item_price;
    // 物主
    @Basic
    private Integer owner;
    // 学生姓名
    @Basic
    private String student_name;
    // 联系电话
    @Basic
    private String contact_number;
    // 物品详情
    @Basic
    private String item_details;
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
