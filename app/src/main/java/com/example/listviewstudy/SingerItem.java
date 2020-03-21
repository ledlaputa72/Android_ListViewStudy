package com.example.listviewstudy;

public class SingerItem {
    //3. 걸그룹에 들어갈 정보
    String name;
    String mobile;
    int resId; // 이미지 아이콘용

    //4. 생성자 생성
    public SingerItem(String name, String mobile, int resId) {
        this.name = name;
        this.mobile = mobile;
        this.resId = resId; //이미지 아이콘용
    }

    //5.getter setter 생성
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    //22. 이미지 위한 getter setter 생성
    public int getResId() {

        return resId;
    }

    public void setResId(int resId) {

        this.resId = resId;
    }

    //6.toString Override
    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
