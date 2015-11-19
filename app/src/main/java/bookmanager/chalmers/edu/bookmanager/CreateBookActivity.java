package bookmanager.chalmers.edu.bookmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class CreateBookActivity extends AppCompatActivity {

    private TextView title;
    private TextView author;
    private TextView course;
    private TextView price;
    private TextView isbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);

        title = (EditText)findViewById(R.id.textViewNameTitle);
        author = (EditText)findViewById(R.id.textViewNameAuthor);
        course = (EditText)findViewById(R.id.textViewNameCourse);
        price = (EditText)findViewById(R.id.textViewNamePrice);
        isbn = (EditText)findViewById(R.id.textViewNameISBN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {

            if (title.getText().toString().isEmpty())
                title.setError("This field can't be empty.");
            else {
                // Adding of the book
                SimpleBookManager manager = SimpleBookManager.getInstance();

                Book b = new Book();
                b.setTitle(title.getText().toString());
                b.setAuthor(author.getText().toString());
                b.setCourse(course.getText().toString());
                b.setPrice(Integer.valueOf(price.getText().toString()));
                b.setIsbn(isbn.getText().toString());
                manager.addBook(b);

                manager.saveChanges(this);

                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
