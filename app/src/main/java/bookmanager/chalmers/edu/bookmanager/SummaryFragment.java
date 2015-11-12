package bookmanager.chalmers.edu.bookmanager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SummaryFragment extends Fragment {

    private TextView totalBooks;
    private TextView totalCost;
    private TextView maxCost;
    private TextView minCost;
    private TextView averageCost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);

        totalBooks = (TextView)rootView.findViewById(R.id.textViewNameTitle);
        totalCost = (TextView)rootView.findViewById(R.id.textViewNameAuthor);
        maxCost = (TextView)rootView.findViewById(R.id.textViewNameCourse);
        minCost = (TextView)rootView.findViewById(R.id.textViewNamePrice);
        averageCost = (TextView)rootView.findViewById(R.id.textViewNameISBN);

        updateView();

        return rootView;
    }

    public void updateView(){
        SimpleBookManager manager = SimpleBookManager.getInstance();

        totalBooks.setText(String.valueOf(manager.count()) + " books in your library.");
        totalCost.setText(String.valueOf(manager.getTotalCost()) + " SEK");
        maxCost.setText(String.valueOf(manager.getMaxPrice()) + " SEK");
        minCost.setText(String.valueOf(manager.getMinPrice()) + " SEK");
        averageCost.setText(String.valueOf(manager.getMeanPrice()) + " SEK");
    }
}
