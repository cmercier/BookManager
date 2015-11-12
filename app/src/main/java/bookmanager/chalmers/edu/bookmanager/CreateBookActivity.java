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

    private SimpleBookManager manager;

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
                Intent data = new Intent();
                data.putExtra("title", title.getText().toString());
                data.putExtra("author", author.getText().toString());
                data.putExtra("course", course.getText().toString());
                data.putExtra("price", price.getText().toString());
                data.putExtra("isbn", isbn.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
