package com.example.listviewstudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

//9. SingerItemView.java를 linearLayout(SingerItem.xml의 최상위 레이아웃 LinearLayout)을 상속해서 만들면 레이아웃 인플레이션(메모리 객체화)한 다음 그대로 붙여줄 수 있다. .
public class SingerItemView extends LinearLayout {

    //13.변수 선언
    TextView textView;
    TextView textView2;
    //20.이미지용 변수 선언
    ImageView imageView;

    //10. 생성자
    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    //11.초기화를 위한 메소드
    private void init(Context context){
        //12. 인플레이션
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        //13. singer_item.xml에 있는 텍스트뷰와 이미지를 참조 가능
        textView = (TextView) findViewById(R.id.textview);
        textView2 = (TextView) findViewById(R.id.textview2);
        imageView = (ImageView) findViewById(R.id.imageIcon);
    }

    //14. 이름과 전화번호, 이미지 데이터를 설정할 수 있게 메서드 정의
    public void setName(String name){

        textView.setText(name);
    }

    public void setMobile(String mobile){

        textView2.setText(mobile);
    }
    //21. 아이콘 이미지를 위한 메서드 정의
    public void setImage(int resId){

        imageView.setImageResource(resId);
    }
}
