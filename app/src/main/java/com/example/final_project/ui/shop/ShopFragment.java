package com.example.final_project.ui.shop;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.final_project.R;
import com.example.final_project.enums.ShopItem;
import com.example.final_project.util.FileManager;
import com.example.final_project.util.MediaPlayerManager;

public class ShopFragment extends Fragment {

    private int money;
    private final String MONEY_FILE = "money.txt";
    private final String ITEMS_FILE = "items.txt";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        TextView tvMoney = view.findViewById(R.id.tv_money);
        Button btnItem30Points = view.findViewById(R.id.btn_item_30_points);
        Button btnItemDoublePoints = view.findViewById(R.id.btn_item_double_points);

        // 현재 보유 금액 로드
        money = FileManager.readMoney(requireContext(), MONEY_FILE);
        tvMoney.setText("보유 금액: " + money + "원");


        MediaPlayerManager.play(requireContext(), R.raw.shopbgm);




        // 초기 버튼 상태 설정
        updateButtonState(btnItem30Points, ShopItem.START_30_POINTS);
        updateButtonState(btnItemDoublePoints, ShopItem.DOUBLE_POINTS);

        btnItem30Points.setOnClickListener(v -> {
            ShopItem item = ShopItem.START_30_POINTS;

            if (money >= item.getPrice()) {
                money -= item.getPrice();
                FileManager.saveMoney(requireContext(), MONEY_FILE, money);
                FileManager.saveItemState(requireContext(), "items.txt", item.getKey(), true);
                tvMoney.setText("보유 금액: " + money + "원");
                updateButtonState(btnItem30Points, item);
                Toast.makeText(requireContext(), item.getDescription() + " 구매 완료!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "금액이 부족합니다!", Toast.LENGTH_SHORT).show();
            }
        });

        btnItemDoublePoints.setOnClickListener(v -> {
            ShopItem item = ShopItem.DOUBLE_POINTS;

            if (money >= item.getPrice()) {
                money -= item.getPrice();
                FileManager.saveMoney(requireContext(), MONEY_FILE, money);
                FileManager.saveItemState(requireContext(), "items.txt", item.getKey(), true);
                tvMoney.setText("보유 금액: " + money + "원");
                updateButtonState(btnItemDoublePoints, item);
                Toast.makeText(requireContext(), item.getDescription() + " 구매 완료!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "금액이 부족합니다!", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    private void updateButtonState(Button button, ShopItem item) {
        boolean isOwned = FileManager.readItemState(requireContext(), ITEMS_FILE, item.getKey());
        if (isOwned) {
            button.setText(item.getDescription() + " : 보유함");
            button.setEnabled(false);
        } else {
            button.setText(item.getDescription() + " : 미보유");
            button.setEnabled(true);
        }
    }

    @Override
    public void onDestroyView() {
        MediaPlayerManager.stop();
        super.onDestroyView();

    }

    @Override
    public void onStop() {
        MediaPlayerManager.stop();
        super.onStop();

    }
}
