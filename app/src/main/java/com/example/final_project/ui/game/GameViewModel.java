package com.example.final_project.ui.game;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;

import com.example.final_project.domain.Game;
import com.example.final_project.util.FileManager;

import java.util.HashMap;
import java.util.Map;

public class GameViewModel extends AndroidViewModel {
    private Game game;
    private Map<String, Boolean> itemStates;

    public GameViewModel(Application application) {
        super(application);
        game = new Game("Anonymous");
        itemStates = FileManager.readAllItemStates(application, "items.txt");
    }

    public Game getGame() {
        return game;
    }

    public void resetGame(String playerName) {
        game = new Game(playerName);
    }

    public Map<String, Boolean> getItemStates() {
        return itemStates;
    }

    public boolean isItemActive(String itemName) {
        return Boolean.TRUE.equals(itemStates.getOrDefault(itemName, false));
    }
}
