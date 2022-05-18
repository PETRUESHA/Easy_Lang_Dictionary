package com.example.easy_lang_dictionary.fragments_MainActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.easy_lang_dictionary.activities.MainActivity;
import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.adapters.ChooseProfileAdapter;
import com.example.easy_lang_dictionary.databinding.FragmentChooseProfileBinding;
import com.example.easy_lang_dictionary.room.App;
import com.example.easy_lang_dictionary.room.Database;
import com.example.easy_lang_dictionary.room.User;
import com.example.easy_lang_dictionary.room.UserDao;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class ChooseProfileFragment extends Fragment {

    private FragmentChooseProfileBinding binding;
    private NavController navController;
    public static RecyclerView recyclerView;

    public ChooseProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChooseProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        EditText title = MainActivity.title;

        Button navigation_to_newProf = binding.button;
        navigation_to_newProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(R.string.profile_1);
                title.setFocusableInTouchMode(true);
                navController.navigate(R.id.action_chooseProfile_to_newProfile);
            }
        });

        final Disposable subscribe = App.getInstance(getContext())
                .getDatabase()
                .userDao()
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        recyclerView.setAdapter(new ChooseProfileAdapter(users));
                    }
                });

        return view;
    }
}