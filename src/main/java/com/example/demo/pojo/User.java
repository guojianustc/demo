package com.example.demo.pojo;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
/**
 * @Entity 注解该类为数据库表实体类，JPA可自动扫描识别到
 * @Table 注解数据表信息，其中name指定表名
 */

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String username;
    String password;
}
