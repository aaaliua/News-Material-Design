package com.airbnb;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.aaaliua.application.MyApplication;
import com.aaaliua.typeface.TypefaceHelper;
import com.aaaliua.utils.Utils;
import com.example.ttt.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment{

	private static final String ARG_POSITION = "position";
	private int position;
	
	
	@InjectView(R.id.hint)TextView hint;
	@InjectView(R.id.hintchild)TextView hintchild;
	public static ContentFragment newInstance(int position) {
		ContentFragment fragment = new ContentFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		fragment.setArguments(b);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		switch (position) {
		case 0:
			hint.setText(Html.fromHtml(getString(R.string.content1,
					Utils.getVersionName(getActivity()))));
			hintchild.setVisibility(View.GONE);
			break;
		case 1:
			hint.setText(Html.fromHtml(getString(R.string.content2,
					Utils.getVersionName(getActivity()))));
			hintchild.setText(Html.fromHtml(getString(R.string.content2child,
					Utils.getVersionName(getActivity()))));
			hintchild.setVisibility(View.VISIBLE);
			break;
		case 2:
			hint.setText(Html.fromHtml(getString(R.string.content3,
					Utils.getVersionName(getActivity()))));
			hintchild.setText(Html.fromHtml(getString(R.string.content3child,
					Utils.getVersionName(getActivity()))));
			hintchild.setVisibility(View.VISIBLE);
			break;
		case 3:
			hint.setText(Html.fromHtml(getString(R.string.content4,
					Utils.getVersionName(getActivity()))));
			hintchild.setText(Html.fromHtml(getString(R.string.content4child,
					Utils.getVersionName(getActivity()))));
			hintchild.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 View view = inflater.inflate(R.layout.fragment_content, container, false);
		 ButterKnife.inject(this, view);
		 return view;
	}
	
	
	
}
