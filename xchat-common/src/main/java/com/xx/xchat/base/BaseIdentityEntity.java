package com.xx.xchat.base;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

/**
 * @MappedSuperclass的类将不是一个完整的实体类，他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中
 */
@Data
@MappedSuperclass
public class BaseIdentityEntity extends BaseBean{

    /**
     * GenerationType:
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * name
     * name属性定义了被标注字段在数据库表中所对应字段的名称；
     *
     * unique
     * unique属性表示该字段是否为唯一标识，默认为false。如果表中有一个字段需要唯一标识，则既可以使用该标记，也可以使用@Table标记中的@UniqueConstraint。
     *
     * nullable
     * nullable属性表示该字段是否可以为null值，默认为true。
     *
     * insertable
     * insertable属性表示在使用“INSERT”脚本插入数据时，是否需要插入该字段的值。
     *
     * updatable
     * updatable属性表示在使用“UPDATE”脚本插入数据时，是否需要更新该字段的值。insertable和updatable属性一般多用于只读的属性，例如主键和外键等。这些字段的值通常是自动生成的。
     *
     * columnDefinition
     * columnDefinition属性表示创建表时，该字段创建的SQL语句，一般用于通过Entity生成表定义时使用。（也就是说，如果DB中表已经建好，该属性没有必要使用。）
     *
     * table
     * table属性定义了包含当前字段的表名。
     *
     * length
     * length属性表示字段的长度，当字段的类型为varchar时，该属性才有效，默认为255个字符。
     *
     * precision和scale
     * precision属性和scale属性表示精度，当字段类型为double时，precision表示数值的总长度，scale表示小数点所占的位数。
     */
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '新增时间'", updatable = false)
    private Date createTime = Date.from(Instant.now());

    @Column(name = "update_time", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Date updateTime = Date.from(Instant.now());
}
