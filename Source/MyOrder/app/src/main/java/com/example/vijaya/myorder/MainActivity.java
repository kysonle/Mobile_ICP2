package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 12;
    final int CHICKEN_PRICE = 4;
    final int ONION_PRICE = 1;
    final int BACON_PRICE = 3;
    final int GREEN_PEPPER_PRICE = 1;
    final int PEPPERONI_PRICE = 3;
    final int PINEAPPLE_PRICE = 2;
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if Chicken is selected
        CheckBox chicken = (CheckBox) findViewById(R.id.chicken_checked);
        boolean hasChicken = chicken.isChecked();

        // check if Bacon is selected
        CheckBox bacon = (CheckBox) findViewById(R.id.bacon_checked);
        boolean hasBacon = bacon.isChecked();

        // check if onions is selected
        CheckBox onion = (CheckBox) findViewById(R.id.onions_checked);
        boolean hasOnion = onion.isChecked();

        // check if Green Peppers is selected
        CheckBox greenPepper = (CheckBox) findViewById(R.id.green_pepper_checked);
        boolean hasGreenPepper = greenPepper.isChecked();

        // check if Pepperoni is selected
        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();

        // check if Pineapple is selected
        CheckBox pineapple = (CheckBox) findViewById(R.id.pineapple_checked);
        boolean hasPineapple = pineapple.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasChicken,hasBacon,hasOnion,hasGreenPepper,hasPepperoni,hasPineapple);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasChicken,hasBacon,hasOnion,hasGreenPepper,hasPepperoni,hasPineapple, totalPrice);

        Intent redirect = new Intent(MainActivity.this, SummaryPage.class);
        redirect.putExtra("summary", orderSummaryMessage);
        startActivity(redirect);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
    }

    public void sendEmail(View view) {
        // Write the relevant code for triggering email

        // Hint to accomplish the task

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if Chicken is selected
        CheckBox chicken = (CheckBox) findViewById(R.id.chicken_checked);
        boolean hasChicken = chicken.isChecked();

        // check if Bacon is selected
        CheckBox bacon = (CheckBox) findViewById(R.id.bacon_checked);
        boolean hasBacon = bacon.isChecked();

        // check if onions is selected
        CheckBox onion = (CheckBox) findViewById(R.id.onions_checked);
        boolean hasOnion = onion.isChecked();

        // check if Green Peppers is selected
        CheckBox greenPepper = (CheckBox) findViewById(R.id.green_pepper_checked);
        boolean hasGreenPepper = greenPepper.isChecked();

        // check if Pepperoni is selected
        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();

        // check if Pineapple is selected
        CheckBox pineapple = (CheckBox) findViewById(R.id.pineapple_checked);
        boolean hasPineapple = pineapple.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasChicken,hasBacon,hasOnion,hasGreenPepper,hasPepperoni,hasPineapple);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasChicken,hasBacon,hasOnion,hasGreenPepper,hasPepperoni,hasPineapple, totalPrice);

        Intent emailSend = new Intent(Intent.ACTION_SEND);
        String TO[] = {"anthony.le@gmail.com"};
        emailSend.setData(Uri.parse("mailto:"));
        emailSend.setType("text/plain");
        emailSend.putExtra(Intent.EXTRA_EMAIL, TO);
        emailSend.putExtra(Intent.EXTRA_SUBJECT, "Thank you for your Pizza order");
        emailSend.putExtra(Intent.EXTRA_TEXT, orderSummaryMessage);

        try {
            startActivity(Intent.createChooser(emailSend, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

        /*Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/
    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }
    // Create an order summary
    private String createOrderSummary(String userInputName, boolean hasChicken,boolean hasBacon,boolean hasOnion,boolean hasGreenPepper,boolean hasPepperoni,boolean hasPineapple, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "," + "\n\n" +
                "Your order details "+ "\n\n" +
                getString(R.string.order_summary_ingredient) + "\n" + "     " +
                getString(R.string.order_summary_chicken, boolToString(hasChicken)) + "\n" + "     " +
                getString(R.string.order_summary_bacon, boolToString(hasBacon)) + "\n" + "     " +
                getString(R.string.order_summary_onion, boolToString(hasOnion)) + "\n" + "     " +
                getString(R.string.order_summary_green_pepper, boolToString(hasGreenPepper)) + "\n" + "     " +
                getString(R.string.order_summary_pepperoni, boolToString(hasPepperoni)) + "\n" + "     " +
                getString(R.string.order_summary_pineapple, boolToString(hasPineapple)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasChicken,boolean hasBacon,boolean hasOnion,boolean hasGreenPepper,boolean hasPepperoni,boolean hasPineapple) {
        int basePrice = PIZZA_PRICE;
        if (hasChicken) {
            basePrice += CHICKEN_PRICE;
        }
        if (hasBacon) {
            basePrice += BACON_PRICE;
        }
        if (hasOnion) {
            basePrice += ONION_PRICE;
        }
        if (hasGreenPepper) {
            basePrice += GREEN_PEPPER_PRICE;
        }
        if (hasPepperoni) {
            basePrice += PEPPERONI_PRICE;
        }
        if (hasPineapple) {
            basePrice += PINEAPPLE_PRICE;
        }

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred Pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select at least one Pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}