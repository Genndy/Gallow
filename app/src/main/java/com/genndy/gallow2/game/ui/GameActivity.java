package com.genndy.gallow2.game.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.genndy.gallow2.R;
import com.genndy.gallow2.game.mockdata.MockDataAncientAnimals;
import com.genndy.gallow2.game.mockdata.MockDataAncientAntiquity;
import com.genndy.gallow2.game.mockdata.MockDataTechnology;
import com.genndy.gallow2.game.model.Letter;
import com.genndy.gallow2.game.ui.adapter.LetterGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private GridView mLetterGridView;
    private LetterGridAdapter mLetterGridAdapter;
    private List<Letter> letters;
    private TextView mGameText;
    private TextView mGameStatusText;
    private TextView mLoseCount;
    private TextView mWinCount;
    private String answerWord;
    private String gameTheme;
    private Button mNextBtn;
    private ImageView mGallowImage;
    List<String> words;
    private char[] answerWordChars;
    private int unicLetterCount;
    private int openedLetterCount;
    private int health;
    private int loseCount = 0;
    private int winCount = 0;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameTheme = getIntent().getStringExtra("game_theme");
        answerWordChars = new char[]{};
        mGameText = (TextView) findViewById(R.id.game_text);
        mGameStatusText = (TextView) findViewById(R.id.game_status_text);
        mNextBtn = (Button) findViewById(R.id.button_next);
        mLetterGridView = (GridView) findViewById(R.id.letters_grid_view);
        mGallowImage = (ImageView) findViewById(R.id.gallow_image);
        mLoseCount = (TextView) findViewById(R.id.lose_count);
        mWinCount = (TextView) findViewById(R.id.win_count);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextWord();
            }
        });

        mLetterGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickLetter(parent, view, position, id);
            }
        });

        nextWord();
    }

    public void clickLetter(AdapterView<?> parent, View view, int position, long id){
        Letter letter = (Letter) parent.getItemAtPosition(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
        if(letter.getStatus() == Letter.DEFAULT && isPlaying){ // Проверка на кликабельность
            // проверка на наличие буквы
            boolean isHere = false;
            for(char nChar : answerWordChars){
                if(nChar == letter.getLetter().charAt(0)){
                    isHere = true;
                    break;
                }
            }
            if(isHere){
                letter.setStatus(Letter.RIGHT);
                imageView.setImageResource(R.drawable.eclipse);
                imageView.setVisibility(View.VISIBLE);
                openedLetterCount++;
                renderWord();
                if(openedLetterCount >= unicLetterCount){
                    endGame(true);
                }
            }else{
                // Висельница добавляется
                health--;
                drawGallow();
                imageView.setImageResource(R.drawable.cross);
                letter.setStatus(Letter.WRONG);
                imageView.setVisibility(View.VISIBLE);
                if(health <= 0){
                    endGame(false);
                }
            }
        }
    }

    public void nextWord(){
        isPlaying = true;

        switch (gameTheme) {
            case "ancient_animals" : words = new MockDataAncientAnimals().getWord(); mGameStatusText.setText("Тема: древние животные");  break;
            case "ancient_antiquity" : words = new MockDataAncientAntiquity().getWord(); mGameStatusText.setText("Тема: древняя античность"); break;
            case "technology" : words = new MockDataTechnology().getWord(); mGameStatusText.setText("Тема: технологии"); break;
            case "all" : {
                int randomTheme = (int) ((Math.random() * (3 - 1)) + 1);
                switch (randomTheme) {
                    case 1 : words = new MockDataAncientAnimals().getWord(); mGameStatusText.setText("Тема: древние животные");  break;
                    case 2 : words = new MockDataAncientAntiquity().getWord(); mGameStatusText.setText("Тема: древняя античность"); break;
                    case 3 : words = new MockDataTechnology().getWord(); mGameStatusText.setText("Тема: технологии"); break;
                }
            }
        }
        mNextBtn.setVisibility(View.INVISIBLE);
        lettersInit();
        openedLetterCount = 0;
        health = 7;
        drawGallow();
        int randomizer = (int) ((Math.random() * (words.size() - 1)) + 0);
        mLetterGridAdapter = new LetterGridAdapter(letters, GameActivity.this);
        answerWord = words.get(randomizer);
        mLetterGridView.setAdapter(mLetterGridAdapter);
        answerWord = answerWord.toUpperCase();
        unicLetterCount = countUniqueCharacters(answerWord);
        answerWordChars = answerWord.toCharArray();
        renderWord();
    }

    public void endGame(boolean isWin){
        isPlaying = false;
        if(isWin){
            winCount++;
            mGameStatusText.setText("ВЫ ВЫЖИЛИ!");
        }else{
            loseCount++;
            showAllLetters();
            mGameStatusText.setText("Вас повесили...");
        }
        mWinCount.setText("Выиграно: " + winCount);
        mLoseCount.setText("Проиграно: " + loseCount);
        mNextBtn.setVisibility(View.VISIBLE);
    }

    public void showAllLetters(){
        StringBuilder renderResult = new StringBuilder();
        for (char nChar : answerWordChars){
                renderResult.append(nChar).append(" ");
        }
        mGameText.setText(renderResult.toString());
    }

    public void renderWord(){
        StringBuilder renderResult = new StringBuilder();
        for (char nChar : answerWordChars){
            boolean isHere = false; // Проверить, открыта ли буква
            for (Letter letter : letters){
                if (nChar == letter.getLetter().charAt(0) && letter.getStatus() == Letter.RIGHT){
                    isHere = true;
                    break;
                }
            }
            if(isHere){
                renderResult.append(nChar).append(" ");
            }else{
                renderResult.append("_").append(" ");
            }
        }

        renderResult.replace(renderResult.length(), renderResult.length(), "");

        mGameText.setText(renderResult.toString());
    }

    public static int countUniqueCharacters(String input) {
        boolean[] isItThere = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < input.length(); i++) {
            isItThere[input.charAt(i)] = true;
        }

        int count = 0;
        for (int i = 0; i < isItThere.length; i++) {
            if (isItThere[i] == true){
                count++;
            }
        }
        return count;
    }

    public void drawGallow(){
        switch (health){
            case 6 : mGallowImage.setImageResource(R.drawable.gallow_6); break;
            case 5 : mGallowImage.setImageResource(R.drawable.gallow_5); break;
            case 4 : mGallowImage.setImageResource(R.drawable.gallow_4); break;
            case 3 : mGallowImage.setImageResource(R.drawable.gallow_3); break;
            case 2 : mGallowImage.setImageResource(R.drawable.gallow_2); break;
            case 1 : mGallowImage.setImageResource(R.drawable.gallow_1); break;
            case 0 : mGallowImage.setImageResource(R.drawable.gallow_0); break;
            default: mGallowImage.setImageResource(R.drawable.gallow_7); break;
        }
    }

    public void lettersInit(){
        letters = new ArrayList<Letter>();
        letters.add(new Letter("А"));
        letters.add(new Letter("Б"));
        letters.add(new Letter("В"));
        letters.add(new Letter("Г"));
        letters.add(new Letter("Д"));
        letters.add(new Letter("Е"));
        letters.add(new Letter("Ё"));
        letters.add(new Letter("Ж"));
        letters.add(new Letter("З"));
        letters.add(new Letter("И"));
        letters.add(new Letter("Й"));
        letters.add(new Letter("К"));
        letters.add(new Letter("Л"));
        letters.add(new Letter("М"));
        letters.add(new Letter("Н"));
        letters.add(new Letter("О"));
        letters.add(new Letter("П"));
        letters.add(new Letter("Р"));
        letters.add(new Letter("С"));
        letters.add(new Letter("Т"));
        letters.add(new Letter("У"));
        letters.add(new Letter("Ф"));
        letters.add(new Letter("Х"));
        letters.add(new Letter("Ц"));
        letters.add(new Letter("Ч"));
        letters.add(new Letter("Ш"));
        letters.add(new Letter("Щ"));
        letters.add(new Letter("Ъ"));
        letters.add(new Letter("Ы"));
        letters.add(new Letter("Ь"));
        letters.add(new Letter("Э"));
        letters.add(new Letter("Ю"));
        letters.add(new Letter("Я"));
    }
}