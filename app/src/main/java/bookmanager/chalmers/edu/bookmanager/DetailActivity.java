package bookmanager.chalmers.edu.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView author;
    private TextView course;
    private TextView price;
    private TextView isbn;

    private SimpleBookManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        manager = new SimpleBookManager();
        Book book = manager.getBook(0);

        title = (TextView)findViewById(R.id.textViewNameTitle);
        author = (TextView)findViewById(R.id.textViewNameAuthor);
        course = (TextView)findViewById(R.id.textViewNameCourse);
        price = (TextView)findViewById(R.id.textViewNamePrice);
        isbn = (TextView)findViewById(R.id.textViewNameISBN);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        course.setText(book.getCourse());
        price.setText(String.valueOf(book.getPrice()));
        isbn.setText(book.getIsbn());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
