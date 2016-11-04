package cn.calculate;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShitiActivity extends Activity {
	private ListView lv;
	private Button reset;
	private Button tijiao;
	private List<Question> q;
	private Myadapter myadapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.neirongfragment);
		lv = (ListView) findViewById(R.id.lv);
		reset = (Button) findViewById(R.id.reset);
		tijiao = (Button) findViewById(R.id.tijiao);
		q = new ArrayList<Question>();// 问题集
		updatequestion();
		
		myadapter = new  Myadapter();
		lv.setAdapter(myadapter);
		      
		reset.setOnClickListener(new OnClickListener() {//重置数据

			@Override
			public void onClick(View v) {
				
				updatequestion();
				
				
				lv.setAdapter(myadapter);

			}
		});
		tijiao.setOnClickListener(new OnClickListener() {//提交时判断对错
			@Override
			public void onClick(View v) {
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ShitiActivity.this);

				builder.setTitle("警告");
				builder.setMessage("确定提交吗？");
				builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						int right=0;//对的题数
						for (int i = 0; i < 20; i++) {
							int youresult=0;
							 Question q1 = q.get(i);
							 String s1=q1.getYoghuzhi().toString().trim();
						      if("".equals(s1)){
						    	  
						    	  
						    	  Toast.makeText(ShitiActivity.this, "您的第"+i+"题没做，请继续", 1).show();
						    	  dialog.dismiss();
						    	  return;
						    	  
						    	  
						      }//没填当0
						      else{
						      youresult= Integer.parseInt(s1);						     
						    if( q1.getAnwser()==youresult){
						    	right++;
						    }}
						}
						dialog.dismiss();
						
						Toast.makeText(ShitiActivity.this, "你本次共做对"+right+"道题,得分："+right*5, 1).show();
						
					}
				});
				builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.show();

			}
		});
	

	}
	
	public void updatequestion() {//更新问题集
		q.clear();	
		if(myadapter!=null){
		myadapter.notifyDataSetChanged();}
		
			Question q1 = new Question();
			q1.setXuhao(0+"");
			q.add(q1);
		//先一次出20个题出来
		for (int i = 1; i < 20; i++) {// 检查后19道题是否重复
			Question q2 = new Question();
			for (int j = 0; j < i; j++) {
				Question q3 = q.get(j);
				while (q2.getA() == q3.getA() && q2.getB() == q3.getB()
						&& q2.getZ() == q3.getZ()) {
					q2 = new Question();// 要是问题重复就重新出
				}
				
			}
			q2.setXuhao(i+"");
			q.add(q2);
			

		}
	}
	

	

	public class Myadapter extends BaseAdapter {
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 20;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			final ViewHolder vh;
			if (convertView == null) {
			convertView = View.inflate(getApplicationContext(), R.layout.item, null);
			vh=new ViewHolder(convertView);
			convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
				
			}
			Question q1 = q.get(position);
			vh.tname.setText(position+"");	//设题号		
			vh.meEditText.setTag(q1);//问题和输入框绑定
			String content = "";
			switch (q1.getZ()) {
			case 2:
				content = q1.getA() + "+" + q1.getB() + "=";
				break;

			case 1:
				content = q1.getA() + "-" + q1.getB() + "=";

				break;
			}
			vh.timu.setText(content);
			
			vh.meEditText.clearFocus();//清除焦点
			vh.meEditText.addTextChangedListener(new TextWatcher() {	
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					Question been = (Question) vh.meEditText.getTag();
					been.setYoghuzhi(s+"");
					
					
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					
				}
			});
			if(!TextUtils.isEmpty(q1.getYoghuzhi())){
				vh.meEditText.setText(q1.getYoghuzhi());
			}
			else{
				vh.meEditText.setText("");
			}
			
			
			
			return convertView;
		}
		public class ViewHolder{
			TextView tname;
			TextView timu;
			EditText meEditText;
			public ViewHolder(View convertView) {
				tname=(TextView) convertView.findViewById(R.id.x1);
				timu=(TextView) convertView.findViewById(R.id.t1);
				meEditText=(EditText) convertView.findViewById(R.id.a1);
				
				
			}
			
			
		}
	}


}
