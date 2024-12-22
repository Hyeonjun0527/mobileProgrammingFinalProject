package com.example.final_project.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.final_project.R;
import com.example.final_project.databinding.FragmentHomeBinding;
import com.example.final_project.util.FileManager;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private int money;
    private final String MONEY_FILE = "money.txt";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button startGameButton = binding.btnStartGame;
        EditText userNameEditText = binding.etUserName;
        Button submitNameButton = binding.btnSubmitName;
        TextView tvMoney = binding.tvMoney;

        // 현재 보유 금액 로드
        money = FileManager.readMoney(requireContext(), MONEY_FILE);
        tvMoney.setText("보유 금액: " + money + "원");

        //상점
        Button shopButton = binding.btnShop;
        shopButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_nav_home_to_shopFragment);
        });

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameButton.setVisibility(View.GONE);
                userNameEditText.setVisibility(View.VISIBLE);
                submitNameButton.setVisibility(View.VISIBLE);
            }
        });

        submitNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();

                if (userName.isEmpty()) {
                    // 사용자가 이름 입력을 안함
                    Toast toast = Toast.makeText(getContext(), "이름을 입력해주세요!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                NavController navController = Navigation.findNavController(v);
                Bundle bundle = new Bundle();
                bundle.putString("user_name", userName);
                navController.navigate(R.id.action_nav_home_to_dialogFragment, bundle);
                Toast toast = Toast.makeText(getContext(), "게임이 시작되었습니다!!!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}