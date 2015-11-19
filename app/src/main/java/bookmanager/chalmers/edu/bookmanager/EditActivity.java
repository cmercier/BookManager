package bookmanager.chalmers.edu.bookmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    private TextView title;
    private TextView author;
    private TextView course;
    private TextView price;
    private TextView isbn;

    Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);

        int position = getIntent().getIntExtra("position", 0);
        currentBook = SimpleBookManager.getInstance().getBook(position);

        title = (EditText)findViewById(R.id.textViewNameTitle);
        title.setText(currentBook.getTitle());
        author = (EditText)findViewById(R.id.textViewNameAuthor);
        author.setText(currentBook.getAuthor());
        course = (EditText)findViewById(R.id.textViewNameCourse);
        course.setText(currentBook.getCourse());
        price = (EditText)findViewById(R.id.textViewNamePrice);
        price.setText(String.valueOf(currentBook.getPrice()));
        isbn = (EditText)findViewById(R.id.textViewNameISBN);
        isbn.setText(currentBook.getIsbn());
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
                currentBook.setTitle(title.getText().toString());
                currentBook.setAuthor(author.getText().toString());
                currentBook.setCourse(course.getText().toString());
                currentBook.setPrice(Integer.valueOf(price.getText().toString()));
                currentBook.setIsbn(isbn.getText().toString());
                SimpleBookManager.getInstance().saveChanges(this);
                setResult(RESULT_OK);
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
