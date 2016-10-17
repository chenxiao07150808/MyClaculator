package cn.edu.gdmec.s07150808.myclaculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    private EditText tv1;
    private RadioButton man;
    private RadioButton woman;
    private TextView result;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= (EditText) findViewById(R.id.tv1);
        man= (RadioButton) findViewById(R.id.man);
        woman= (RadioButton) findViewById(R.id.woman);
        result= (TextView) findViewById(R.id.result);
        button= (Button) findViewById(R.id.button);
    }
    private void showMessage(String Message){
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();

        alertDialog.setTitle("消息提醒!");
        alertDialog.setIcon(android.R.drawable.ic_dialog_info);
        alertDialog.setMessage(Message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        registerEvent();
        super.onStart();
    }

    private void registerEvent(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tv1.getText().toString().trim().equals("")){
                    if(man.isChecked()||woman.isChecked()){
                        Double  weight=Double.parseDouble(tv1.getText().toString());
                       StringBuilder builder=new StringBuilder();
                        builder.append("--------------------------------------------------\n");
                        if(man.isChecked()){
                            builder.append("男性标准身高");
                            double result=checkWeight(weight,"男");
                            builder.append((int)result+"厘米"+"魔鬼身材值得你拥有！");
                        }else{
                            builder.append("女性标准身高");
                            double result=checkWeight(weight,"女");
                            builder.append((int)result+"厘米，出来看看帅哥，也让帅哥看看美女");

                        }
                        result.setText(builder);
                    }else{
                        showMessage("你选择性别后操作,不急慢慢来。");
                    }
                }else{
                    showMessage("骚年,你的体重呢！重于泰山，或轻于鸿毛。");
                }
            }
        });
    }
    private  double checkWeight(Double weight,String sex){
        Double height;
        if (sex == "男") {
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case 1:
               finish();
               break;
       }
        return super.onOptionsItemSelected(item);

    }
}
