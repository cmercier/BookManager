package bookmanager.chalmers.edu.bookmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CollectionFragment extends Fragment {

    ListView listBooks;
    ArrayAdapter<Book> listAdapter;

    public static CollectionFragment newInstance() {
        // return an instance of the fragment
        CollectionFragment fragment = new CollectionFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_collection, container, false);

        listBooks = (ListView)rootView.findViewById(R.id.list_books);

        listAdapter = new ArrayAdapter<Book>(getContext(), android.R.layout.simple_list_item_1, SimpleBookManager.getInstance().getAllBooks());

        listBooks.setAdapter(listAdapter);

        listBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public void updateView() {
        listAdapter.notifyDataSetChanged();
    }
}
