package bookmanager.chalmers.edu.bookmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    int position;
    Book book;

    final int REQUEST_CODE_EDIT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        manager = SimpleBookManager.getInstance();
        position = getIntent().getIntExtra("position", 0);
        book = manager.getBook(position);

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
        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("position", position);
            startActivityForResult(intent, REQUEST_CODE_EDIT);
            return true;
        }
        else if (id == R.id.action_delete) {
            SimpleBookManager.getInstance().removeBook(book);
            SimpleBookManager.getInstance().saveChanges(this);
            finish();
            return true;
        }
        else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // to handle the return from the EditActivity and skip the DetailActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_CODE_EDIT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}
