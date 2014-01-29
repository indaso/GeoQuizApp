package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	public static final String EXTRA_ANSWER_IS_TRUE = 
			"com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN =
			"com.bignerdranch.android.geoquiz.answer_shown";
	
	private boolean mAnswerIsTrue;
	private boolean mCheated;
	private static final String KEY_CHEATED = 
			"com.bignerdranch.android.geoquiz.mCheated";
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	private TextView mAPITextView;
	
	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		mAPITextView = (TextView)findViewById(R.id.api_level);
		mAPITextView.setText("API level " + Build.VERSION.SDK_INT);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCheated = true;
				if (mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}
		});
		
		if (savedInstanceState != null) {
			mCheated = savedInstanceState.getBoolean(KEY_CHEATED, false);
		}
		
		// check if button has been pressed already on orientation change
		if (mCheated) {
			if (mAnswerIsTrue) {
				mAnswerTextView.setText(R.string.true_button);
			} else {
				mAnswerTextView.setText(R.string.false_button);
			}
			setAnswerShownResult(true);
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean(KEY_CHEATED, mCheated);
	}
}
