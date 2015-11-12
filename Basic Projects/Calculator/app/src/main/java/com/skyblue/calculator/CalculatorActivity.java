package com.skyblue.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int BUTTONS_SIZE = 10;
    private Button[] buttonsArray;

    private Button button_equal;
    private Button button_plus;
    private Button button_minus;
    private Button button_product;
    private Button button_divide;
    private Button button_erase;

    private Boolean isEmpty;

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        textViewResult = (TextView) findViewById(R.id.textViewResult);

        buttonsArray = new Button[BUTTONS_SIZE];


        buttonsArray[0] = (Button) findViewById(R.id.button_0);
        buttonsArray[1] = (Button) findViewById(R.id.button_1);
        buttonsArray[2] = (Button) findViewById(R.id.button_2);
        buttonsArray[3] = (Button) findViewById(R.id.button_3);
        buttonsArray[4] = (Button) findViewById(R.id.button_4);
        buttonsArray[5] = (Button) findViewById(R.id.button_5);
        buttonsArray[6] = (Button) findViewById(R.id.button_6);
        buttonsArray[7] = (Button) findViewById(R.id.button_7);
        buttonsArray[8] = (Button) findViewById(R.id.button_8);
        buttonsArray[9] = (Button) findViewById(R.id.button_9);

        
        for(Button item: buttonsArray)
            item.setOnClickListener(this);

        button_equal = (Button) findViewById(R.id.button_equal);
        button_plus = (Button) findViewById(R.id.button_plus);
        button_minus = (Button) findViewById(R.id.button_minus);
        button_product = (Button) findViewById(R.id.button_product);
        button_divide = (Button) findViewById(R.id.button_divide);
        button_erase = (Button) findViewById(R.id.button_c);


    }

    public void onClickOperation(View view){
        Button pressed = (Button) view;
        String v = pressed.getText().toString();
        textViewResult.append(v);

    }

    public void onClickEquals(View view){

        double result = eval(textViewResult.getText().toString());
        textViewResult.setText(Double.toString(result));
    }

    public void onClickErase(View view){

        textViewResult.setText("0");
    }



    @Override
    public void onClick(View view){
        Button pressed = (Button) view;
        for (Button btn : buttonsArray) {
            if (pressed == btn) {

                if(textViewResult.getText().toString().equals("0"))
                    textViewResult.setText("");

                String v = btn.getText().toString();
                textViewResult.append(v);
            }
        }
    }


    public static double eval(final String str) {
        class Parser {
            int pos = -1, c;

            void eatChar() {
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            void eatSpace() {
                while (Character.isWhitespace(c)) eatChar();
            }

            double parse() {
                eatChar();
                double v = parseExpression();
                if (c != -1) throw new RuntimeException("Unexpected: " + (char)c);
                return v;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor | term brackets
            // factor = brackets | number | factor `^` factor
            // brackets = `(` expression `)`

            double parseExpression() {
                double v = parseTerm();
                for (;;) {
                    eatSpace();
                    if (c == '+') { // addition
                        eatChar();
                        v += parseTerm();
                    } else if (c == '-') { // subtraction
                        eatChar();
                        v -= parseTerm();
                    } else {
                        return v;
                    }
                }
            }

            double parseTerm() {
                double v = parseFactor();
                for (;;) {
                    eatSpace();
                    if (c == '/') { // division
                        eatChar();
                        v /= parseFactor();
                    } else if (c == '*' || c == '(') { // multiplication
                        if (c == '*') eatChar();
                        v *= parseFactor();
                    } else {
                        return v;
                    }
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;
                eatSpace();
                if (c == '+' || c == '-') { // unary plus & minus
                    negate = c == '-';
                    eatChar();
                    eatSpace();
                }
                if (c == '(') { // brackets
                    eatChar();
                    v = parseExpression();
                    if (c == ')') eatChar();
                } else { // numbers
                    StringBuilder sb = new StringBuilder();
                    while ((c >= '0' && c <= '9') || c == '.') {
                        sb.append((char)c);
                        eatChar();
                    }
                    if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)c);
                    v = Double.parseDouble(sb.toString());
                }
                eatSpace();
                if (c == '^') { // exponentiation
                    eatChar();
                    v = Math.pow(v, parseFactor());
                }
                if (negate) v = -v; // unary minus is applied after exponentiation; e.g. -3^2=-9
                return v;
            }
        }
        return new Parser().parse();
    }



}





