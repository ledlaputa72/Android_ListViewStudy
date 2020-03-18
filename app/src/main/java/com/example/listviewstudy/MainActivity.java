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

    SingerAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        ListView listView = (ListView) findViewById(R.id.listView1); //1

        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("소녀시대", "010-222-3333", R.drawable.icon01));
        adapter.addItem(new SingerItem("카라", "010-222-4444", R.drawable.icon02));
        adapter.addItem(new SingerItem("여자친구", "010-222-5555", R.drawable.icon03));
        adapter.addItem(new SingerItem("티아라", "010-222-6666", R.drawable.icon04));
        adapter.addItem(new SingerItem("애프터스쿨", "010-222-7777", R.drawable.icon05));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : "+ item.getName(),Toast.LENGTH_LONG).show();
            }
        });

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
        public int getCount() {

            return items.size();
        }

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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SingerItemView view = null;

            if(convertView == null ) {
                view = new SingerItemView(getApplicationContext());
            } else {
                view = (SingerItemView) convertView;
            }

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());

            return view;
        }
    }
}
