package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText display;
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
        switch (view.getId()){
            case R.id.clear: display.setText(""); break;
            case R.id.brackets:addBrackets(); break;
            case R.id.square:updateDisplay("^"); break;
            case R.id.seven:updateDisplay("7"); break;
            case R.id.six:updateDisplay("6"); break;
            case R.id.five:updateDisplay("5"); break;
            case R.id.four:updateDisplay("4"); break;
            case R.id.three:updateDisplay("3"); break;
            case R.id.two:updateDisplay("2"); break;
            case R.id.one:updateDisplay("1"); break;
            case R.id.minus:updateDisplay("-"); break;
            case R.id.divided:updateDisplay("x"); break;
            case R.id.plus:updateDisplay("+"); break;
            case R.id.dot:updateDisplay("."); break;
            case R.id.zero:updateDisplay("0"); break;
            case R.id.threeZero:updateDisplayThreeZero(); break;
            case R.id.backspace:backSpace(); break;
            case R.id.equal:calculateTheResult(); break;

        }
    }

    private void backSpace() {
    }

    private void calculateTheResult() {
    }

    private void updateDisplayThreeZero() {
    }

    private void updateDisplay(String addCharToDisplay) {
    }

    private void addBrackets() {
    }
}