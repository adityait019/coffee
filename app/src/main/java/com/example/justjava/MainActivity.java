package com.example.justjava;



import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
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
    public void incrementByOne(View view)
    {
        quantity=quantity+1;
        display(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int numberOfCoffee=quantity;
        String message="Thank_You";
        display(numberOfCoffee);
        displayPrice(numberOfCoffee*5);
        displayMessage(message);
    }
    /**
     * this method is called when the incrementByOne button is clicked
     */
    public void decrementByOne(View view)
    {
        quantity=quantity-1;
        if(quantity<0)
        {
            quantity=0;
            display(quantity);
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
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view1);
        priceTextView.setText(message);
    }
}
