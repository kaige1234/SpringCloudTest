package cn.com.orderserver.bean;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String code;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
