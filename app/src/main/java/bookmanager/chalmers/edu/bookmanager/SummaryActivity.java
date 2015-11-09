package bookmanager.chalmers.edu.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private TextView totalBooks;
    private TextView totalCost;
    private TextView maxCost;
    private TextView minCost;
    private TextView averageCost;

    private SimpleBookManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        manager = new SimpleBookManager();

        totalBooks = (TextView)findViewById(R.id.textViewNameTitle);
        totalCost = (TextView)findViewById(R.id.textViewNameAuthor);
        maxCost = (TextView)findViewById(R.id.textViewNameCourse);
        minCost = (TextView)findViewById(R.id.textViewNamePrice);
        averageCost = (TextView)findViewById(R.id.textViewNameISBN);

        totalBooks.setText(String.valueOf(manager.count()) + " books in your library.");
        totalCost.setText(String.valueOf(manager.getTotalCost()) + " SEK");
        maxCost.setText(String.valueOf(manager.getMaxPrice()) + " SEK");
        minCost.setText(String.valueOf(manager.getMinPrice()) + " SEK");
        averageCost.setText(String.valueOf(manager.getMeanPrice()) + " SEK");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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
}
