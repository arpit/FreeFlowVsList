package com.freeflow.clickeventtest;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.comcast.freeflow.core.AbsLayoutContainer;
import com.comcast.freeflow.core.FreeFlowContainer;
import com.comcast.freeflow.core.FreeFlowItem;
import com.comcast.freeflow.core.Section;
import com.comcast.freeflow.core.SectionedAdapter;
import com.comcast.freeflow.helpers.DefaultSectionAdapter;
import com.comcast.freeflow.layouts.VLayout;

public class ClickSpeedActivity extends Activity implements OnTouchListener {

	private ListView listView;
	private FreeFlowContainer container;

	String TAG = "clicktest";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_speed);
		listView = (ListView) findViewById(R.id.list_view);

		listView.setOnTouchListener(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				click = new Date().getTime();
				Log.d(TAG, "Delta: "+ (click-up));
			}
		});

		container = (FreeFlowContainer) findViewById(R.id.ff_container_view);
		container.setEdgeEffectsEnabled(false);

		String[] items = new String[] { "one", "two", "three","four", "five", "six","seven", "eight", "nine", "ten" };
		ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		listView.setAdapter(itemsAdapter);

		DefaultSectionAdapter sadapter = new DefaultSectionAdapter(this, 1, 10,
				false);
		VLayout v = new VLayout();
		v.setLayoutParams(new VLayout.LayoutParams(100, 100, 30));
		container.setLayout(v);
		container.setAdapter(sadapter);
		container.setOnTouchListener(this);
		container.setOnItemClickListener( new AbsLayoutContainer.OnItemClickListener() {
			
			@Override
			public void onItemClick(AbsLayoutContainer parent, FreeFlowItem proxy) {
				click = new Date().getTime();
				Log.d(TAG, "Delta: "+ (click-up));
			}
		});
		

	}
	
	private long down;
	private long up;
	private long click;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case (MotionEvent.ACTION_DOWN):
			down = new Date().getTime();
			Log.d(TAG, "Down: " + down);
			break;
		case (MotionEvent.ACTION_UP):
			up = new Date().getTime();
			Log.d(TAG, "Up: " + up);
			break;
			
		}
		return false;
	}

}
