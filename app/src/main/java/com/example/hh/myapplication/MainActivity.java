package com.example.hh.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private EditText tvOut;
    private TextView label;
    private String sign = " ";
    private double first;
    private double second;

    private Boolean QClickEquals = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = (EditText)findViewById(R.id.editText);
        label = (TextView)findViewById(R.id.textView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnButtonClick(View v){
        if(QClickEquals){
            QClickEquals = false;
            label.setText("");
            tvOut.setText("");
        }

        Button b = (Button)v;
        String text = tvOut.getText().toString() + b.getText().toString();
        tvOut.setText(text);
        text = label.getText().toString()+b.getText().toString();
        label.setText(text);
    }

    public void OnSignButtonClick(View v){
        if(QClickEquals){
            QClickEquals = false;
            String text = String.valueOf(first);
            label.setText(text);
        }
        Button b = (Button) v;
        first = Double.parseDouble(tvOut.getText().toString());
        tvOut.setText("");
        sign = b.getText().toString();
        String text = label.getText().toString()+b.getText().toString();
        label.setText(text);
    }

    public void OnEqualsButtonClick(View v){
        if(!sign.equals(" ")) {
            second = Double.parseDouble(tvOut.getText().toString());
            switch (sign){
                case "+":
                    first = first + second;
                    break;
                case "-":
                    first = first - second;
                    break;
                case "×":
                    first = first * second;
                    break;
                case "÷":
                    first = first / second;
                    break;
            }
            String text = String.valueOf(first);
            tvOut.setText(text);
            sign = " ";
            text = label.getText().toString()+"=" + text;
            label.setText(text);
            QClickEquals = true;
        }
    }

    public void ButtonSqrClick(View v){
        double a = Double.parseDouble(tvOut.getText().toString());
        a = a * a;
        String text = String.valueOf(a);
        tvOut.setText(text);
    }

    public void ButtonDeleteClick(View v){
        if(QClickEquals){
            QClickEquals = false;
            String text = String.valueOf(first);
            label.setText(text);
        }
        String text = tvOut.getText().toString();
        if(text.length() > 0) {
            int delta = 1;
            if (text.length() > 3 && text.charAt(text.length() - 2) == '.') delta++;
            text = text.substring(0, text.length() - delta);
            tvOut.setText(text);

            text = label.getText().toString();
            text = text.substring(0, text.length() - delta);
            label.setText(text);
        }
    }

    public void ButtonNegationClick(View v){
        String text = tvOut.getText().toString();
        String labText = label.getText().toString();

        if(text.charAt(0) == '-') {
            text = text.substring(1, text.length());
            labText = labText.substring(0, labText.length() - text.length() - 3);
            labText = labText + text;
        } else {
            text = "-"+text;
            labText = labText.substring(0, labText.length() - text.length() + 1);
            labText = labText + "("+text+")";
        }
        tvOut.setText(text);
        label.setText(labText);
    }
}
