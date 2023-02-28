package com.example.justjava;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
/* This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * this method is called when the incrementByOne button is clicked
     */
    int quantity=0;
    int whippedCream=0;
    int chocolate=0;
    public void incrementByOne(View view)
    {
        if(quantity>100)
        {
            Toast.makeText(this,"You have selected more than 100 coffee ",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        if(quantity==0)
        {
            return;
        }
        EditText editText=findViewById(R.id.name_textView);
        String value=editText.getText().toString();
        int numberOfCoffee=quantity;
//        int price=calculatePrice(numberOfCoffee,whippedCream,chocolate);
//        displayPrice(price);
        CheckBox checkBox=(CheckBox) findViewById(R.id.whipCream_checkBox);
        boolean hasWhippedCreamChecked=checkBox.isChecked();
        if(hasWhippedCreamChecked)
        {
            whippedCream=1;
        }

        CheckBox checkBox1=(CheckBox) findViewById(R.id.chocolate_checkBox);
        boolean hasChocolateTicked=checkBox1.isChecked();
        if(hasChocolateTicked)
        {
            chocolate=1;
        }
        String message=createOrderSummary(value ,numberOfCoffee,hasWhippedCreamChecked,hasChocolateTicked);
//        /*
//        Intent class is object which help app to let to other activity in specified
//        app
//        * */
        chocolate=0;
        whippedCream=0;
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); //only email app should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just java order for "+value);
        intent.putExtra(Intent.EXTRA_TEXT,message);

            startActivity(intent);
//        displayMessage(message);

    }
    /**
     * this method is called when the incrementByOne button is clicked
     */
    public void decrementByOne(View view)
    {
        quantity=quantity-1;
        if(quantity<0)
        {
            Toast.makeText(this,"Please add atLeast one coffee ", Toast.LENGTH_SHORT).show();
            quantity=0;
            display(quantity);

            return;
        }

        display(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
    /*
    this is bool function to know whether you selected whipped cream or not?
    * */

    /**
     * This method displays the given text on the screen.
     */
    private int calculatePrice(int quantity,int whippedCream,int chocolate)
    {

        return quantity*(25+(whippedCream*5)+(chocolate*2));
    }
    private String createOrderSummary(String value, int quantity, boolean hasWhippedCream, boolean hasChocolateSelected)
    {

        String message=" Name : "+value;
        message=message+ "\n Quantity : "+quantity;
        message=message+"\n Has Whipped Cream : "+hasWhippedCream;
        message+="\n Has Chocolate selected : "+hasChocolateSelected;
        message += "\nTotal: " +calculatePrice(quantity,whippedCream,chocolate) +"\u20B9"; // Use the Unicode character for the rupee symbol
        message=message+"\n Thank You ";
        return message;
    }
}
