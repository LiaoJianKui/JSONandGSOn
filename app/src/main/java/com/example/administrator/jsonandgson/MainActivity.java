package com.example.administrator.jsonandgson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private Button btn1,btn2,btn3;
    private TextView tvResult;
    private String jsonStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        tvResult= (TextView) findViewById(R.id.tvResult);
        Student s1=new Student("12","张三",33);
        Student s2=new Student("11","李四",34);
        Student s3=new Student("13","王武",23);
        final Student[] stus={s1,s2,s3};
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray array=new JSONArray();
                for (int i = 0; i < stus.length; i++) {
                    JSONObject stuObj=getStudentJsonObj(stus[i]);
                    array.put(stuObj);
                }
                JSONObject obj=new JSONObject();
                try {
                    obj.put("stuList",array);
                } catch (JSONException e) {

                }
                jsonStr=obj.toString();
                tvResult.setText(obj.toString());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject obj=new JSONObject(jsonStr);
                    JSONArray array=obj.getJSONArray("stuList");
                    ArrayList<Student> stuList=new ArrayList<Student>();
                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject stuObj = array.getJSONObject(i);
                        String id = stuObj.getString("id");
                        String name = stuObj.getString("name");
                        int age = stuObj.getInt("age");
                        Student s = new Student(id,name,age);
                        stuList.add(s);
                        tvResult.setText(s.getName() + "   " + s.getAge());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonStr = "{\"id\":\"123\",\"name\":\"Erika\",\"age\":16}";
                Gson gson = new Gson();
                Student s = gson.fromJson(jsonStr, Student.class);
                tvResult.setText(s.getName() + "   " + s.getAge());
            }
        });
    }

    private JSONObject getStudentJsonObj(Student stu) {
        JSONObject obj=new JSONObject();
        try {
            obj.put("id",stu.getId());
            obj.put("name",stu.getName());
            obj.put("age",stu.getAge());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
