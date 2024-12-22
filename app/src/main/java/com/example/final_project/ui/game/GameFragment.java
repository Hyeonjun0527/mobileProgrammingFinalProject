package com.example.final_project.ui.game;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.final_project.R;
import com.example.final_project.domain.Game;
import com.example.final_project.domain.NextPoint;
import com.example.final_project.domain.Player;
import com.example.final_project.util.FileManager;
import com.example.final_project.util.MediaPlayerManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class GameFragment extends Fragment {

    private GameViewModel gameViewModel;
    private String playerName;
    private TextView tvDay, tvScore, tvUniversity;
    private Button btnStudy, btnPlay, btnRestart, btnHallOfFame, btnMath,
            btnEnglish, btnKorean, btnScience1, btnScience2, btnRest, btnEat;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("playerName", playerName);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        if (savedInstanceState != null) {
            playerName = savedInstanceState.getString("playerName");
        } else {
            playerName = getArguments() != null ? getArguments().getString("playerName", "Anonymous") : "Anonymous";
            gameViewModel.getGame().setPlayer(new Player(playerName));


            applyStart30PointsEffect();
        }

        initializeUI(view);

        playerName = getArguments() != null ? getArguments().getString("playerName", "Anonymous") : "Anonymous";
        updateUIAndData();
        allButtonClickEvent();

        return view;
    }

    private void allButtonClickEvent() {
        btnMath.setOnClickListener(v -> {
            action("math");
            updateUIAndData();
        });

        btnEnglish.setOnClickListener(v -> {
            action("english");
            updateUIAndData();
        });

        btnKorean.setOnClickListener(v -> {
            action("korean");
            updateUIAndData();
        });

        btnScience1.setOnClickListener(v -> {
            action("science1");
            updateUIAndData();
        });

        btnScience2.setOnClickListener(v -> {
            action("science2");
            updateUIAndData();
        });

        btnRest.setOnClickListener(v -> {
            action("rest");
            updateUIAndData();
            Toast.makeText(requireContext(), "휴식을 했습니다! 기분이 좋아집니다.", Toast.LENGTH_SHORT).show();
        });

        btnEat.setOnClickListener(v -> {
            action("eat");
            updateUIAndData();
            Toast.makeText(requireContext(), "밥을 먹었습니다! 기분이 좋아집니다.", Toast.LENGTH_SHORT).show();
        });
        btnRestart.setOnClickListener(v -> restartGame());
        btnHallOfFame.setOnClickListener(v -> navigateToHallOfFame());
    }

    private void applyStart30PointsEffect() {
        boolean isStart30PointsActive = FileManager.readItemState(requireContext(), "items.txt", "start30Points");
        if (isStart30PointsActive) {
            Player player = gameViewModel.getGame().getPlayer();
            player.getScore().setMath(30);
            player.getScore().setEnglish(30);
            player.getScore().setKorean(30);
            player.getScore().setScience1(30);
            player.getScore().setScience2(30);

            //일회용이라면
//            FileManager.saveItemState(requireContext(), "items.txt", "start30Points", false);
        }
    }

    private void initializeUI(View view) {
        //초기화
        tvDay = view.findViewById(R.id.tv_day);
        tvUniversity = view.findViewById(R.id.tv_university);
        tvScore = view.findViewById(R.id.tv_score);
        btnMath = view.findViewById(R.id.btn_math);
        btnEnglish = view.findViewById(R.id.btn_english);
        btnKorean = view.findViewById(R.id.btn_korean);
        btnScience1 = view.findViewById(R.id.btn_science1);
        btnScience2 = view.findViewById(R.id.btn_science2);
        btnRest = view.findViewById(R.id.btn_rest);
        btnEat = view.findViewById(R.id.btn_eat);
        btnRestart = view.findViewById(R.id.btn_restart);
        btnHallOfFame = view.findViewById(R.id.btn_hall_of_fame);
        btnRestart.setVisibility(View.GONE);
        btnHallOfFame.setVisibility(View.GONE);
    }

    private void action(String subject) {
        NextPoint nextPoint = gameViewModel.getGame().getNextPoint();
        gameViewModel.getGame().performAction(subject, nextPoint, gameViewModel.getItemStates());
        gameViewModel.getGame().refreshNextPoint();
    }


    private void updateUIAndData() {
        Game game = gameViewModel.getGame();
        boolean isDoublePointsActive = Boolean.TRUE.equals(gameViewModel.getItemStates().getOrDefault("doublePoints", false));

        int pointMultiplier = isDoublePointsActive ? 2 : 1;
        int day = game.getDay().getCurrentDay();
        int mathPoint = game.getNextPoint().getMathPoint() * pointMultiplier;
        int englishPoint = game.getNextPoint().getEnglishPoint() * pointMultiplier;
        int koreanPoint = game.getNextPoint().getKoreanPoint() * pointMultiplier;
        int science1Point = game.getNextPoint().getScience1Point() * pointMultiplier;
        int science2Point = game.getNextPoint().getScience2Point() * pointMultiplier;


        tvDay.setText("Day: " + day);
        btnMath.setText("수학 공부\n" + mathPoint + "점");
        btnEnglish.setText("영어 공부\n" + englishPoint + "점");
        btnKorean.setText("국어 공부\n" + koreanPoint + "점");
        btnScience1.setText("과학1 공부\n" + science1Point + "점");
        btnScience2.setText("과학2 공부\n" + science2Point + "점");
        setTvScore(game);
        //게임오버라면..
        if (game.getDay().isGameOver()) {

            btnMath.setVisibility(View.GONE);
            btnEnglish.setVisibility(View.GONE);
            btnKorean.setVisibility(View.GONE);
            btnScience1.setVisibility(View.GONE);
            btnScience2.setVisibility(View.GONE);
            btnRest.setVisibility(View.GONE);
            btnEat.setVisibility(View.GONE);
            btnRestart.setVisibility(View.VISIBLE);
            btnHallOfFame.setVisibility(View.VISIBLE);
            tvUniversity.setText("입시 결과 : " + game.getUniversity());
            tvUniversity.setVisibility(View.VISIBLE);
            tvDay.setText("게임 종료");
            setTvScoreToGrade(game);

            // 게임 결과 저장
            saveGameResult(game.getPlayer().getName(), game.getUniversity());
            updateUserMoney(100); // 게임 종료 보상: 100원 추가
            Toast.makeText(requireContext(), "100원을 얻었습니다!", Toast.LENGTH_SHORT).show();
            MediaPlayerManager.stop();
        }
    }

    private void setTvScore(Game game) {
        tvScore.setText("수학점수: " + game.getPlayer().getScore().getMath());
        tvScore.append("\n국어점수: " + game.getPlayer().getScore().getKorean());
        tvScore.append("\n영어점수: " + game.getPlayer().getScore().getEnglish());
        tvScore.append("\n과학1점수: " + game.getPlayer().getScore().getScience1());
        tvScore.append("\n과학2점수: " + game.getPlayer().getScore().getScience2());
    }

    private void setTvScoreToGrade(Game game) {
        String[] subjects = {"수학", "국어", "영어", "과학1", "과학2"};
        int[] scores = {
                game.getPlayer().getScore().getMath(),
                game.getPlayer().getScore().getKorean(),
                game.getPlayer().getScore().getEnglish(),
                game.getPlayer().getScore().getScience1(),
                game.getPlayer().getScore().getScience2()
        };
        StringBuilder gradeMessage = new StringBuilder();
        for (int i = 0; i <= subjects.length - 2; i++) {
            gradeMessage.append(subjects[i])
                    .append(" 등급: ")
                    .append(getGrade(scores[i]))
                    .append("\n");
        }
        gradeMessage.append(subjects[subjects.length - 1])
                .append(" 등급: ")
                .append(getGrade(scores[subjects.length - 1]));
        tvScore.setText(gradeMessage.toString());
    }

    private String getGrade(int score) {
        if (score >= 100) {
            return "1등급";
        } else if (score >= 80) {
            return "2등급";
        } else if (score >= 60) {
            return "3등급";
        } else if (score >= 40) {
            return "4등급";
        } else if (score >= 20) {
            return "5등급";
        } else {
            return "6~9등급";
        }
    }


    private void restartGame() {

        //게임 상태 초기화
        gameViewModel.resetGame(playerName);
        MediaPlayerManager.restart(requireContext(), R.raw.gamebgm2);
        //버튼 상태 초기화
        applyStart30PointsEffect();
        btnMath.setVisibility(View.VISIBLE);
        btnEnglish.setVisibility(View.VISIBLE);
        btnKorean.setVisibility(View.VISIBLE);
        btnScience1.setVisibility(View.VISIBLE);
        btnScience2.setVisibility(View.VISIBLE);
        btnRest.setVisibility(View.VISIBLE);
        btnEat.setVisibility(View.VISIBLE);
        tvUniversity.setVisibility(View.GONE);
        btnRestart.setVisibility(View.GONE);
        btnHallOfFame.setVisibility(View.GONE);

        updateUIAndData();
    }

    private void navigateToHallOfFame() {
        NavController navController = Navigation.findNavController(requireView());
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.nav_home, false) // nav_home까지 백스택 유지
                .build();
        navController.navigate(R.id.action_gameFragment_to_nav_gallery, null, navOptions);
    }

    private void saveGameResult(String playerName, String university) {
        String fileName = "game_results.txt";
        String data = playerName + " : " + university + "\n";
        try {
            FileOutputStream fos = requireContext().openFileOutput(fileName, Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            System.out.println("결과가 저장되었습니다: " + data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("결과 저장 실패: " + e.getMessage());
        }
    }

    private void updateUserMoney(int earnedMoney) {
        String fileName = "money.txt";

        try {
            FileInputStream fis = requireContext().openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String currentMoneyLine = reader.readLine();
            fis.close();

            int currentMoney = currentMoneyLine != null ? Integer.parseInt(currentMoneyLine) : 0;

            int newMoney = currentMoney + earnedMoney;

            FileOutputStream fos = requireContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(String.valueOf(newMoney).getBytes());
            fos.close();

            System.out.println("금액이 업데이트되었습니다: " + newMoney);

        } catch (Exception e) {
            try {
                FileOutputStream fos = requireContext().openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write(String.valueOf(earnedMoney).getBytes());
                fos.close();
                System.out.println("금액 파일을 생성하고 초기 금액을 저장했습니다: " + earnedMoney);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("금액 업데이트 실패: " + ex.getMessage());
            }
        }
    }


}