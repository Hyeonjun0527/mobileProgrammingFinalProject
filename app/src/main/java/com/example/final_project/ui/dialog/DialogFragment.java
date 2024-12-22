package com.example.final_project.ui.dialog;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.final_project.R;
import com.example.final_project.util.MediaPlayerManager;

public class DialogFragment extends Fragment {

    private static final String ARG_USER_NAME = "user_name";
    private String userName;
    private int currentDialogueIndex = 0;
    private String[] dialogues;
    public static DialogFragment newInstance(String userName) {
        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_NAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_USER_NAME);
        }

        dialogues = new String[]{
                "엄마\n" + userName + "아, 오늘부터 공부 열심히 한다며?\n 게임기 치운 거 맞아?",
                userName + "\n맞아, 엄마. 오늘부터 진짜 열심히 할 거야. \n계획도 다 짰다니까!",
                "엄마\n그래, 다행이다. 밥 먹고 바로 시작하는 거지?",
                userName + "\n응! 물론이지. 국어 먼저 할 거야. \n단어 암기 끝내고 문제집 풀 거야.",
                "친구\n" + userName + "아, 오늘 저녁에 롤 한 판 어때? 오랜만에 같이 하자!",
                userName + "\n아... 미안. 지금은 안 될 것 같아. 나 수능 끝나고 하자.",
                "친구\n야, 한 판만 하고 해도 되잖아.",
                userName + "\n진짜 안 돼. 요즘 집중 잘 되고 있어서 \n이거 흐트러지면 큰일 나.\n" +
                        "우리 수능 30일밖에 안남았잖아.",
                "그렇게 " + userName + "은 공부를 시작하였습니다.\n",
                "30일 안에 " + userName + "을 샤울대학교로 보내십시오..!"
        };

        MediaPlayerManager.play(requireContext(), R.raw.gamebgm2);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        TextView textView = view.findViewById(R.id.dialog_text);
        Button nextButton = view.findViewById(R.id.btn_next);

        textView.setText(dialogues[currentDialogueIndex]);
        //0 1 2 3, 4 5 6 7
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDialogueIndex++;

                if (currentDialogueIndex == 4) {
                    view.findViewById(R.id.container).setBackground(getResources().getDrawable(R.drawable.school));
                }

                if (currentDialogueIndex < dialogues.length) {
                    textView.setText(dialogues[currentDialogueIndex]);
                } else {

                    Bundle bundle = new Bundle();
                    bundle.putString("playerName", userName);
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.dialogFragment, true)
                            .build();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_dialogFragment_to_gameFragment, bundle, navOptions);
                }
            }
        });

        return view;
    }
}