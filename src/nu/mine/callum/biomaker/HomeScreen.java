package nu.mine.callum.biomaker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

//Heat oil to >70 deg 
//Mix with pump to equalise temp
//Leave to settle and run off any water, murky / milky oil on the following day
//Reheat to 70 deg 
//Calculate 15% x 70% of meth for first dose (equiv to appx 11%)
//Mix 7 grams KOH with your meth
//Add slowly to your oil with pump running
//React for 75 - 90 mins
//Stop machine and take a sample - 10ml using syringe for 10/90 test (I use a glass syringe
//Separate glyc and check volume in tank (keep glyc for glyc washing next batch of feedstock)
//Drop out - each 0.1ml equates to 1% of unreacted veg oil
//Eg in 100 litre batch 1ml drop out = 10% or 10litres of unreacted oil
//2nd stage - Calculate 15% x 30% Meth (equiv to 4%)
//Add 7 grams per each litre of veg oil (in our example of 100 litres this equates to 70 grams
//react for 45 - 60 mins and retest 10/90
//If there is any drop out at all run pump with 2nd stage glyc in for a further 30 mins and check again

public class HomeScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        String[] available_options = getNumPickerOptions();
        NumberPicker num_picker = (NumberPicker)findViewById(R.id.oil_quantity_number_picker);
        num_picker.setMinValue(0);
        num_picker.setMaxValue(available_options.length -1);
        num_picker.setWrapSelectorWheel(false);
        num_picker.setDisplayedValues(getNumPickerOptions());
        num_picker.setOnValueChangedListener(new OnValueChangeListener(){
        	@Override
        	public void onValueChange (NumberPicker picker, int oldVal, int newVal) {
        		TextView firstDoseMeth = (TextView)findViewById(R.id.first_dose_meth_litres);
        		TextView firstDoseKOH = (TextView)findViewById(R.id.first_dose_KOH);
        		
        		int oilQuantity = Integer.parseInt(picker.getDisplayedValues()[newVal]);
        		
        		firstDoseMeth.setText(Double.valueOf(calculateMethFirstBatch(oilQuantity)).toString());
        		firstDoseKOH.setText(Double.valueOf(requiredKOH(oilQuantity)).toString());
        	}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }
    
    private double calculateMethFirstBatch(int oilQuantity) {
    	return oilQuantity * 0.7 * 0.15;
    }
    
    private double requiredKOH(double litresOfMeth) {
    	return litresOfMeth * 7;
    }
    
    private String[] getNumPickerOptions() {
    String[] nums = new String[21];
    
    for(int i=0; i<nums.length; i++) {
       nums[i] = Integer.toString(i*5);
    }
    return nums;
  }
}
