package nio.netty.demo04;

import org.msgpack.annotation.MessagePackBeans;

import java.io.Serializable;

/**
 * Created by wangjufeng on 2017/9/11.
 */
@MessagePackBeans
public class UserInfo implements Serializable {
    private String name;
    private String gender;
    private Integer age;

    public UserInfo(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserInfo() {

    }

    @Override
    public String toString() {
        return "[" + name + "," + age + "," + gender + "]";
    }
}
