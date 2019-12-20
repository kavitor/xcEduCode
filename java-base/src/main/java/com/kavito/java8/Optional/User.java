package com.kavito.java8.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/9/30 5:56 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Address{
    private String city;
}
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Address address;


    public static void main(String[] args) throws Exception {
        User user = new User("zhangSan",new Address("深圳"));
        //User user1 = new User();
        String city = getCity(user);
        System.out.println(city);

        System.out.println(getUser(user));

    }

    public String getCityOld(User user)  throws Exception{
        if(user!=null){
            if(user.getAddress()!=null){
                Address address = user.getAddress();
                if(address.getCity()!=null){
                    return address.getCity();
                }
            }
        }
        throw new Exception("error");
    }

    public static String getCity(User user) throws Exception{
        return Optional.ofNullable(user)
                .map(u->u.getAddress())
                .map(a->a.getCity())
                .orElseThrow(()->new Exception("error"));
    }


    //java8以前写法
    public static User getUserOld(User user) {
        if (user != null) {
            String name = user.getName();
            if ("zhangSan".equals(name)) {
                return user;
            }
        } else {
            User u = new User();
            u.setName("张三");
            u.setAddress(new Address("北京"));
            return u;
        }
        return  null;
    }

    public static User getUser(User user) {
       return Optional.ofNullable(user)
                .filter(u->"zhangSan".equals(u.getName()))
                .orElseGet(()-> {
                    User u = new User();
                    u.setName("张三");
                    u.setAddress(new Address("北京"));
                    return u;
                });
       // return Optional.ofNullable(user).filter(u-> "zhangSan".equals(u.getName())).get();
    }


}
