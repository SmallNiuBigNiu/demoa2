package cn.gdcp.demoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtAge;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private Button btnSave;
    private String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setTitle("编辑学生");
        feindViews();

        initViewData();

        setBtnSaveClikcListener();
    }

    /**
     * 设置保存按钮的点击事件
     */
    private void setBtnSaveClikcListener() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                int age = Integer.parseInt(edtAge.getText().toString());

                int imgId = R.drawable.dog1;

                if(radioButton1.isChecked()){
                    imgId = R.drawable.dog1;
                }else if(radioButton2.isChecked()){
                    imgId = R.drawable.dog2;
                }else if(radioButton3.isChecked()){
                    imgId = R.drawable.dog3;
                }else if(radioButton4.isChecked()){
                    imgId = R.drawable.dog4;
                }else if(radioButton5.isChecked()){
                    imgId = R.drawable.dog5;
                }

                Intent intent = new Intent();
                intent.putExtra("NAME", name);
                intent.putExtra("AGE", age);
                intent.putExtra("IMG", imgId);
                intent.putExtra("NO", no);
                setResult(3001, intent);

                finish();
            }
        });
    }

    /**
     * 初始化界面数据
     */
    private void initViewData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        int age = intent.getIntExtra("AGE", 0);
        int imgId = intent.getIntExtra("IMGID", R.drawable.dog1);
        no = intent.getStringExtra("NO");

        edtName.setText(name);
        edtAge.setText(String.valueOf(age));

        switch (imgId){
            case R.drawable.dog1:
                radioButton1.setChecked(true);
                break;

            case R.drawable.dog2:
                radioButton2.setChecked(true);
                break;

            case R.drawable.dog3:
                radioButton3.setChecked(true);
                break;

            case R.drawable.dog4:
                radioButton3.setChecked(true);
                break;

            case R.drawable.dog5:
                radioButton5.setChecked(true);
                break;
        }
    }

    /**
     * 绑定各个自组件
     */
    private void feindViews() {
        edtName = (EditText)findViewById(R.id.edtName);
        edtAge =(EditText)findViewById(R.id.edtAge);

        radioButton1 = (RadioButton)findViewById(R.id.radioBtn1);
        radioButton2 = (RadioButton)findViewById(R.id.radioBtn2);
        radioButton3 = (RadioButton)findViewById(R.id.radioBtn3);
        radioButton4 = (RadioButton)findViewById(R.id.radioBtn4);
        radioButton5 = (RadioButton)findViewById(R.id.radioBtn5);
        btnSave = (Button)findViewById(R.id.btn_save);
    }
}
