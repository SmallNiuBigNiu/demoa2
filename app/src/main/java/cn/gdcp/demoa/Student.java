package cn.gdcp.demoa;

/**
 * Created by yls on 2018/10/19.
 */

public class Student {
    private String no;
    private String name;
    private int age;
    private int imgId;

    public Student(String no, String name, int age, int imgId){
        this.no = no;
        this.age = age;
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getImgId() {
        return imgId;
    }

    public String getNo() {
        return no;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

}

