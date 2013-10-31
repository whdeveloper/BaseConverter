package com.whd.baseconverter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.whd.baseconverter.tools.Base;
import com.whd.baseconverter.widget.NumberPicker;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Converter extends SherlockActivity implements OnNavigationListener {
    
    //View references
    private TextView view_input;
    private TextView view_output;
    private TextView view_base_input;
    private TextView view_base_output;
    
    private EditText input_value;
    
    private NumberPicker base_input;
    private NumberPicker base_output;
    
    private Button convert;
    
    private ActionBar ab;
    private int tabnumber;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        
        ab = getSupportActionBar();
        ab.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(this, R.array.locations, R.layout.sherlock_spinner_item);
        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
        
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ab.setListNavigationCallbacks(list, this);
        
        //View references
        view_input = (TextView) findViewById(R.id.input);
        view_output = (TextView) findViewById(R.id.output);
        view_base_input = (TextView) findViewById(R.id.base_input);
        view_base_output = (TextView) findViewById(R.id.base_output);
        
        input_value = (EditText) findViewById(R.id.input_type);
        
        base_input = (NumberPicker) findViewById(R.id.base_input_value);
        base_output = (NumberPicker) findViewById(R.id.base_output_value);
        
        convert = (Button) findViewById(R.id.convert);
        
        //Set default values
        view_input.setText("Input: ");
        view_base_input.setText ("From base : ");
        view_base_output.setText("To base     : ");
        convert.setText("Convert!");
        
        base_input.setNumber(10);
        base_output.setNumber(10);
        
        view_output.setText("");
        
        //Set listeners
        convert.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String sinput = input_value.getText().toString();
                if (sinput.equals("")) {
                    input_value.setError("Input cannot be empty");
                }
                int base_in = base_input.getNumber();
                int base_out = base_output.getNumber();
                
                switch (tabnumber) {
                    case 0:
                        try {
                            view_output.setText(""+Base.baseToBase(sinput, base_in, base_out));
                        } catch (NumberFormatException nfe) {
                            view_output.setText("Unable to parse, maybe a wrong input base?");
                        }
                        break;
                    case 1:
                        try {
                            view_output.setText(""+Base.DecimalToBase(Integer.parseInt(sinput), base_out));
                        } catch (NumberFormatException nfe) {
                            input_value.setError("Input can contain 0-9 only");
                        }
                        break;
                    case 2:
                        view_output.setText(""+Base.BaseToDecimal(sinput, base_in));
                        break;
                    default: break;
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        tabnumber = itemPosition;
        switch (itemPosition) {
            case 0:
                view_base_input.setVisibility(View.VISIBLE);
                base_input.setVisibility(View.VISIBLE);
                view_base_output.setVisibility(View.VISIBLE);
                base_output.setVisibility(View.VISIBLE);
                break;
            case 1:
                view_base_input.setVisibility(View.GONE);
                base_input.setVisibility(View.GONE);
                view_base_output.setVisibility(View.VISIBLE);
                base_output.setVisibility(View.VISIBLE);
                break;
            case 2:
                view_base_input.setVisibility(View.VISIBLE);
                base_input.setVisibility(View.VISIBLE);
                view_base_output.setVisibility(View.GONE);
                base_output.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        return true;
    }
}
