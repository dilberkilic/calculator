package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {
    EditText display;
    Boolean equalOpen =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);//Klavyenin gelmesini engelledik.
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.tap_here).equals(display.getText().toString())) //Yazıyı kaldırmak
                {
                      display.setText("");
                }
            }
        });
    }

    public void anyButton(View view) {

        if(equalOpen){
            display.setText("");
            equalOpen=false;
        }
        switch (view.getId()){
            case R.id.clear: display.setText(""); break;
            case R.id.brackets:addBrackets(); break;
            case R.id.square:updateDisplay("^"); break;
            case R.id.seven:updateDisplay("7"); break;
            case R.id.six:updateDisplay("6"); break;
            case R.id.five:updateDisplay("5"); break;
            case R.id.four:updateDisplay("4"); break;
            case R.id.nine:updateDisplay("9"); break;
            case R.id.eight:updateDisplay("8"); break;
            case R.id.three:updateDisplay("3"); break;
            case R.id.two:updateDisplay("2"); break;
            case R.id.one:updateDisplay("1"); break;
            case R.id.minus:updateDisplay("-"); break;
            case R.id.divided:updateDisplay("÷"); break;
            case R.id.plus:updateDisplay("+"); break;
            case R.id.dot:updateDisplay("."); break;
            case R.id.times:updateDisplay("x"); break;
            case R.id.zero:updateDisplay("0"); break;
            case R.id.threeZero:updateDisplayThreeZero(); break;
            case R.id.backspace:backSpace(); break;
            case R.id.equal:calculateTheResult(); break;

        }
    }

    private void backSpace() {
 int cursor=display.getSelectionStart();
 if(cursor>0)
 {
     String oldDisplay=display.getText().toString();
     String leftSideOfDisplay =oldDisplay.substring(0,cursor-1); //bir sildiği için.
     String rightSideOfDisplay=oldDisplay.substring(cursor);
     String newText=leftSideOfDisplay+rightSideOfDisplay;
     display.setText(newText);
     display.setSelection(cursor-1);
 }
    }

    private void calculateTheResult() {

        String text=display.getText().toString();
        String reText=text.replaceAll("÷","/");
        reText=text.replaceAll("x","*");
        Expression ifade=new Expression(reText);
        String result=String.valueOf(ifade.calculate()).toString();
        if(!result.equals("NaN")){
            display.setText(result);
            display.setSelection(result.length());
        }else{
             showToast("HATALI GİRİŞ YAPILDI !!!");
        }
        equalOpen=true;
    }

    private void updateDisplayThreeZero() {
        int cursor=display.getSelectionStart();
        if(getString(R.string.tap_here).equals(display.getText().toString())) //Yazıyı kaldırmak
        {
            display.setText("000");
        }else{
            ///123456+45 ise;
            String oldDisplay=display.getText().toString();
            String leftSideOfDisplay =oldDisplay.substring(0,cursor);
            String rightSideOfDisplay=oldDisplay.substring(cursor);
            String newText=leftSideOfDisplay+"000"+rightSideOfDisplay;
            display.setText(newText);
        }
        display.setSelection(cursor+3); //yazıldığında bir yana gittiği için.

    }

    private void updateDisplay(String addCharToDisplay) {
        int cursor=display.getSelectionStart();
        if(getString(R.string.tap_here).equals(display.getText().toString())) //Yazıyı kaldırmak
        {
            display.setText(addCharToDisplay);
        }else{
            ///123456+45 ise;
            String oldDisplay=display.getText().toString();
            String leftSideOfDisplay =oldDisplay.substring(0,cursor);
            String rightSideOfDisplay=oldDisplay.substring(cursor);
            String newText=leftSideOfDisplay+addCharToDisplay+rightSideOfDisplay;
            display.setText(newText);
        }
        display.setSelection(cursor+1); //yazıldığında bir yana gittiği için.

    }

    private void addBrackets() {
        String text=display.getText().toString();
        int cursor=display.getSelectionStart();
        int countBrac=0;
        for(int i=0;i<text.length();i++){
            if(text.substring(i,i+1).equalsIgnoreCase("(")) countBrac++;
            if(text.substring(i,i+1).equalsIgnoreCase(")")) countBrac--;
        }
        String lastChar=text.substring(text.length()-1);
        if(countBrac==0 || lastChar.equals("(")) updateDisplay("(");
        else if(countBrac>0 && !lastChar.equals(")")) updateDisplay(")");
    }
    private void showToast(String text){
        LayoutInflater infler=getLayoutInflater();
        View layout=infler.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout));
        Toast toast= new Toast(getApplicationContext());
        TextView toastText=layout.findViewById(R.id.toast_text);
        toastText.setText(text);

        toast.setGravity(Gravity.CENTER,0,-200);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}