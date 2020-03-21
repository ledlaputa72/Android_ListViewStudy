package com.example.listviewstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SingerAdapter adapter; //24. 변수로 만들어서 하위 메소드에서 접근 가능하게

    //25. 입력을 받기 위한 변수 선언
    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //25. 입력을 받기 위한 리소스를 변수에 할당
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        ListView listView = (ListView) findViewById(R.id.listView1); //1. activity_mail.xml의 listView를 참조

        //18. ListView에 Adapter 객체를 만든 다음에 설정해주는 과정
        adapter = new SingerAdapter(); //24. 변수로 만들어서 하위 메소드에서 접근 가능하게

        //19.addItem 메소드를 사용하여 데이터 입력 --> 이미지 아이콘 데이터 추가
        adapter.addItem(new SingerItem("소녀시대", "010-222-3333", R.drawable.icon01));
        adapter.addItem(new SingerItem("카라", "010-222-4444", R.drawable.icon02));
        adapter.addItem(new SingerItem("여자친구", "010-222-5555", R.drawable.icon03));
        adapter.addItem(new SingerItem("티아라", "010-222-6666", R.drawable.icon04));
        adapter.addItem(new SingerItem("애프터스쿨", "010-222-7777", R.drawable.icon05));

        //18.listView에 setAdapter를 통해 adapter 객체를 파라미터로 전달하고, 리스트뷰가 어뎁터를 알게 되고 화면에 보일때 getCount를 호출하여 객수를 파악, getView를 그 개수만큰 받아서 화면에 출력
        listView.setAdapter(adapter);

        //23. listView를 클릭할때 이벤트 발생
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : "+ item.getName(),Toast.LENGTH_LONG).show();
            }
        });

        //26. 버튼을 찾아 이벤트 적용
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String mobile = editText2.getText().toString();

                adapter.addItem(new SingerItem(name, mobile, R.drawable.icon01));
                adapter.notifyDataSetChanged();
            }
        });

    }

    //2. 데이터를 넣고 뺴는 하는 클래스
    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>(); //여러 정보를 위한 객체 정의 (SingerItem.java)

        @Override
        public int getCount() { //7. 리스트안에 몇개의 아이템이 있는지를 Count로 알아본다.

            return items.size();
        }

        //17. view 안에 데이터가 없다. 데이터를 추가하는 메소드
        public void addItem(SingerItem item){

            items.add(item);
        }

        @Override
        public Object getItem(int position) {

            return items.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        //8. 보여지는 걸그룹의 정보를 레이아웃으로 보여진다. (singer_item.xml 과 SingerItemView.java가 필요)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //SingerItemView view = new SingerItemView(getApplicationContext()); // 15.SingerItemView 객체를 생성(view) - 어떤 뷰든 안드로이드에서는 전부 컨텍스트 객체를 받는다.

            //27. 메모리 활용을 위해 재사용 할수 있게 바꾼다.
            SingerItemView view = null;

            if(convertView == null ) {
                view = new SingerItemView(getApplicationContext());
            } else {
                view = (SingerItemView) convertView;
            }

            //16. position을 item변수로 받아 데이터를 설정하여 view 객체에 입력 : item(번호).get을 이용하여 이름과 전화번호, 이미지를 입력한다.
            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId()); //23. 이미지 아이콘용 추가

            return view; //16.이제 view 객체 안에 위의 데이터가 들어간 완성된 형태가 저장되어 리턴한다.
        }
    }
}
