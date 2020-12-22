package com.zhangbo.po.user;
import lombok.Data;
import java.util.Date;

/**
 * @ClassName User
 * @Author zhangbo
 * @Date 2020/12/22 15:19
 * @Version 1.0
 **/
@Data
public class User {
    private Integer id;
    private String headPortrait;
    private String nickName;
    private String phone;
    private String token;
    private Integer gender;
    private String realName;
    private String identityCard;
    private Date createDate;
    private Date updateDate;
    private Date loginDate;
    private String address;
}
