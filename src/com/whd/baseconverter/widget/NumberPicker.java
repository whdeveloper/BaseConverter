package com.whd.baseconverter.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class NumberPicker extends RelativeLayout {
    
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL   = 1;
    
    private int orientation;
    private int counter;
    
    private Button add;
    private Button sub;
    private EditText display;
    
    private RelativeLayout.LayoutParams subparams;
    private RelativeLayout.LayoutParams displayparams;
    private RelativeLayout.LayoutParams addparams;
    
    public NumberPicker(Context context) {
        this(context, null);
    }
    
    public NumberPicker(Context context, AttributeSet attrs) {
        super (context, attrs);
        
        subparams     = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        displayparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addparams     = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        
        counter = 0;
        orientation = 0;
        
        add = new Button(context);
        add.setId(1);
        sub = new Button(context);
        sub.setId(2);
        display = new EditText(context);
        display.setId(3);
        
        add.setText("+");
        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            counter++;
            display.setText("" + counter);
            }
        });
        
        sub.setText("-");
        sub.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            counter--;
            display.setText("" + counter);
            }
        });
        
        display.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int i = Integer.parseInt(s.toString());
                    counter = i;
                } catch (Exception e) {
                    s.clear();
                    s.append("" + counter);
                }
            }
        });
        
        setOrientation(orientation);
        sub.setLayoutParams(subparams);
        display.setLayoutParams(displayparams);
        add.setLayoutParams(addparams);
        
        this.addView(sub);
        this.addView(display);
        this.addView(add);
        
        this.setOrientation(orientation);
    }
    
    private void setOrientation(int orientation) {
        if (orientation == HORIZONTAL) {
            subparams.addRule(RelativeLayout.ALIGN_LEFT);
            subparams.addRule(RelativeLayout.ALIGN_TOP, display.getId());
            subparams.addRule(RelativeLayout.ALIGN_BOTTOM, display.getId());
            
            displayparams.addRule(RelativeLayout.RIGHT_OF, sub.getId());
            
            addparams.addRule(RelativeLayout.ALIGN_TOP, display.getId());
            addparams.addRule(RelativeLayout.ALIGN_BOTTOM, display.getId());
            addparams.addRule(RelativeLayout.RIGHT_OF, display.getId());
            
        } else {
            addparams.addRule(RelativeLayout.ALIGN_TOP);
            addparams.addRule(RelativeLayout.ALIGN_RIGHT, display.getId());
            addparams.addRule(RelativeLayout.ALIGN_LEFT, display.getId());
            
            displayparams.addRule(RelativeLayout.BELOW, add.getId());
            
            subparams.addRule(RelativeLayout.ALIGN_LEFT, display.getId());
            subparams.addRule(RelativeLayout.ALIGN_RIGHT, display.getId());
            subparams.addRule(RelativeLayout.BELOW, display.getId());
        }
    }
    
    public int getOrientation() {
        return orientation;
    }
    
    public int getNumber() {
        return counter;
    }
    
    public void setNumber(int number) {
        counter = number;
        display.setText(""+counter);
    }
}