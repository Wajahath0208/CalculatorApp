package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_operation,tv_result;
    Button btn_clear,btn_openbrace,btn_closebrace,btn_allclear;
    Button btn_7,btn_8,btn_9,btn_add;
    Button btn_4,btn_5,btn_6,btn_subtract;
    Button btn_1,btn_2,btn_3,btn_multiply;
    Button btn_dot,btn_0,btn_equal,btn_divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv_operation=findViewById(R.id.tv_operation);
        tv_result=findViewById(R.id.tv_result);
        buttonMapping(btn_clear,R.id.btn_clear);
        buttonMapping(btn_openbrace,R.id.btn_openbrace);
        buttonMapping(btn_closebrace,R.id.btn_closebrace);
        buttonMapping(btn_allclear,R.id.btn_allclear);
        buttonMapping(btn_7,R.id.btn_7);
        buttonMapping(btn_8,R.id.btn_8);
        buttonMapping(btn_9,R.id.btn_9);
        buttonMapping(btn_add,R.id.btn_add);
        buttonMapping(btn_4,R.id.btn_4);
        buttonMapping(btn_5,R.id.btn_5);
        buttonMapping(btn_6,R.id.btn_6);
        buttonMapping(btn_subtract,R.id.btn_subtract);
        buttonMapping(btn_1,R.id.btn_1);
        buttonMapping(btn_2,R.id.btn_2);
        buttonMapping(btn_3,R.id.btn_3);
        buttonMapping(btn_multiply,R.id.btn_multiply);
        buttonMapping(btn_dot,R.id.btn_dot);
        buttonMapping(btn_0,R.id.btn_0);
        buttonMapping(btn_equal,R.id.btn_equal);
        buttonMapping(btn_divide,R.id.btn_divide);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void buttonMapping(Button btnClear, int Id) {
        btnClear =findViewById(Id);
        btnClear.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String btntext = button.getText().toString();
        String dataconcat=tv_operation.getText().toString();
        if(btntext.equals("AC")){
            tv_operation.setText("");
            tv_result.setText("0");
            return;
        }
        else if (btntext.equals("C")){
            dataconcat=dataconcat.substring(0,dataconcat.length()-1);
        }
        else if (btntext.equals("=")) {
            tv_operation.setText(tv_result.getText());
            return;

        }
         else {
            dataconcat=dataconcat+btntext;
        }
        tv_operation.setText(dataconcat);
        String finalresult=getResult(dataconcat);
        if (!finalresult.equals("Err")){
            tv_result.setText(finalresult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable= context.initStandardObjects();
           String finalrsult= context.evaluateString(scriptable,data,"javascript",1,null).toString();
           if (finalrsult.endsWith(".0")){
               finalrsult=finalrsult.replace(".0","");
           }
           return finalrsult;
        } catch (Exception e) {
            return "Err";
        }
    }

}
