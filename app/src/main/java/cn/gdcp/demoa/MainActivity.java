package cn.gdcp.demoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IOnDelListener{
    private ListView stuListView;
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    private ArrayList<Student> searchResultList = new ArrayList<>();
    private StuAdapter adapter;
    private Button btnAddStu;

    private EditText edtKeyword;
    private Button btnSearch;

    private boolean isResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initStuArrayList();

        initAdapter();

        initListView();

        initAddBtn();

        initSearchView();
    }

    /**
     * 初始化搜索模块
     */
    private void initSearchView() {
        edtKeyword =(EditText)findViewById(R.id.edt_keyword);
        btnSearch = (Button)findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = edtKeyword.getText().toString().trim();
                // 使用学生列表和关键字 得到搜索结果列表
                search(studentArrayList, keyword);
                adapter.changeData(searchResultList);
                isResultList = true;
            }
        });

        edtKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String keyword = edtKeyword.getText().toString().trim();
                // 使用学生列表和关键字 得到搜索结果列表
                search(studentArrayList, keyword);
                adapter.changeData(searchResultList);
                isResultList = true;
            }
        });
    }

    /**
     * 使用学生列表和关键字 得到搜索结果列表
     * @param studentArrayList
     * @param keyword
     */
    private void search(ArrayList<Student> studentArrayList,
                        String keyword) {
        searchResultList.clear();
        for(int i=0; i<studentArrayList.size(); i++){
            Student s = studentArrayList.get(i);
            // 如果学生姓名包含了关键字 这个学生就加入到结果列表
            if(s.getName().contains(keyword)){
                searchResultList.add(s);
            }
            // 如果学生年龄等于关键字 这个学生就加入到结果列表
            else if(String.valueOf(s.getAge()).equals(keyword)){
                searchResultList.add(s);
            }
        }
    }

    /**
     * 初始化添加学生按钮
     */
    private void initAddBtn() {
        btnAddStu = (Button)findViewById(R.id.btn_addStu);
        btnAddStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        AddActivity.class);
                startActivityForResult(intent, 1001);
            }
        });
    }

    /**
     * 初始化ListView
     */
    private void initListView() {
        stuListView = (ListView)findViewById(R.id.lv_stu);

        stuListView.setAdapter(adapter);

        stuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student s =  studentArrayList.get(i);

                if(isResultList){
                    s = searchResultList.get(i);
                }

                Toast.makeText(MainActivity.this,
                        "姓名"+s.getName() + " 年龄"
                                +  s.getAge(),
                        Toast.LENGTH_SHORT)
                        .show();

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        EditActivity.class);
                intent.putExtra("NAME", s.getName());
                intent.putExtra("AGE", s.getAge());
                intent.putExtra("IMGID",s.getImgId());
                intent.putExtra("NO", s.getNo());
                startActivityForResult(intent, 1002);
            }
        });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new StuAdapter(MainActivity.this,
                studentArrayList, MainActivity.this );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("MainActivity",
                "requestCode = " + requestCode + " resultCode = " + resultCode);

        if(data == null){
            return;
        }

        if(resultCode == 2001){
            String name = data.getStringExtra("NAME");
            int age = data.getIntExtra("AGE", 0);
            int imgId = data.getIntExtra("IMG", R.drawable.dog1);

            Student s = new Student("18006",name, age, imgId);
            studentArrayList.add(s);
            adapter.notifyDataSetChanged();
        }

        else if(resultCode == 3001){
            String name = data.getStringExtra("NAME");
            int age = data.getIntExtra("AGE", 0);
            int imgId = data.getIntExtra("IMG", R.drawable.dog1);
            String no = data.getStringExtra("NO");

            for(int i=0; i<studentArrayList.size(); i++){
                Student s = studentArrayList.get(i);

                if(s.getNo().equals(no)) {
                    s.setName(name);
                    s.setAge(age);
                    s.setImgId(imgId);
                }
            }

            String keyword = edtKeyword.getText().toString().trim();
            // 使用学生列表和关键字 得到搜索结果列表
            search(studentArrayList, keyword);
            adapter.changeData(searchResultList);
            isResultList = true;
        }
    }

    /**
     * 初始化学生列表的数据
     */
    private void initStuArrayList() {
        Student s1 = new Student("18001","张一", 18, R.drawable.dog1);
        Student s2 = new Student("18002","张二", 19, R.drawable.dog2);
        Student s3 = new Student("18003","张三", 20, R.drawable.dog3);
        Student s4 = new Student("18004","张四", 21, R.drawable.dog4);
        Student s5 = new Student("18005","张五", 22, R.drawable.dog5);

        studentArrayList.add(s1);
        studentArrayList.add(s2);
        studentArrayList.add(s3);
        studentArrayList.add(s4);
        studentArrayList.add(s5);


    }

    @Override
    public void del(int i) {
        Log.e("MainActivity", " del " + i);
        studentArrayList.remove(i);
        adapter.notifyDataSetChanged();
    }
}
