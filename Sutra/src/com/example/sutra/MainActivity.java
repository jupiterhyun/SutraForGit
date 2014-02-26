package com.example.sutra;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("CutPasteId")
public class MainActivity extends Activity {

	Bundle saveInstanceState;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		saveInstanceState = savedInstanceState;
		setContentView(R.layout.activity_main);
		
		ExpandableListAdapter adapter = new BaseExpandableListAdapter()
		{
 			private String[] armTypes = new String[]
				{ "楞严经", "妙法莲华经", "心经"};
			private String[][] arms = new String[][]
			{
				{ "大佛顶首楞严经 第一卷", "大佛顶首楞严经 第二卷", "大佛顶首楞严经 第三卷", "大佛顶首楞严经 第四卷", "大佛顶首楞严经 第五卷", "大佛顶首楞严经 第六卷", "大佛顶首楞严经 第七卷", "大佛顶首楞严经 第八卷", "大佛顶首楞严经 第九卷", "大佛顶首楞严经 第十卷" },
				{ "小狗", "刺蛇", "飞龙", "自爆飞机" },
				{ "般若波罗蜜多心经", "心经 要义" , "心经 略释" }
			};
			// 获取指定组位置、指定子列表项处的子列表项数据
			@Override
			public Object getChild(int groupPosition, int childPosition)
			{
				return arms[groupPosition][childPosition];
			}

			@Override
			public long getChildId(int groupPosition, int childPosition)
			{
				return childPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition)
			{
				return arms[groupPosition].length;
			}

			private TextView getTextView()
			{
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, 64);
				TextView textView = new TextView(MainActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}

			// 该方法决定每个子选项的外观
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent)
			{
				TextView textView = getTextView();
				textView.setText(getChild(groupPosition, childPosition).toString());
				return textView;
			}

			// 获取指定组位置处的组数据
			@Override
			public Object getGroup(int groupPosition)
			{
				return armTypes[groupPosition];
			}

			@Override
			public int getGroupCount()
			{
				return armTypes.length;
			}

			@Override
			public long getGroupId(int groupPosition)
			{
				return groupPosition;
			}

			// 该方法决定每个组选项的外观
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent)
			{
				LinearLayout ll = new LinearLayout(MainActivity.this);
				ll.setOrientation(0);
				TextView textView = getTextView();
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);
				return ll;
			}

			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition)
			{
				return true;
			}

			@Override
			public boolean hasStableIds()
			{
				return true;
			}
		};
		ExpandableListView expandListView = (ExpandableListView) findViewById(R.id.list);
		expandListView.setAdapter(adapter);
		
		expandListView.setOnChildClickListener(new OnChildClickListener() {  
             
	            @Override  
	            public boolean onChildClick(ExpandableListView parent, View v,  
	                    int groupPosition, int childPosition, long id) {  
	                Toast.makeText(MainActivity.this, "您单击了第" + groupPosition + "组，第" + childPosition + "个", Toast.LENGTH_SHORT).show();  
	        
	                createNewList(groupPosition, childPosition);
	                	                
	                return false;  
	            }  
	     }); 
				
	}

	private boolean createNewList(final int groupPosition, final int childPosition)
	{
		super.onCreate(saveInstanceState);
		setContentView(R.layout.chapters);
		
		//调用loadText方法获取对应文件名的文件
		String name = Integer.toString(groupPosition) + Integer.toString(groupPosition) + Integer.toString(childPosition) + ".txt";
		String nr=loads(name);
		EditText et2=(EditText)findViewById(R.id.EditText);
		//设置显示框内容
		et2.setText(nr);
		
		
		Button left = (Button) this.findViewById(R.id.Button00); // 返回上一章
		left.setOnClickListener // 返回按钮监听器
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createNewList(groupPosition, childPosition - 1);
			}
		});
		
		Button middle = (Button) this.findViewById(R.id.Button01); // 返回根目录
		middle.setOnClickListener // 返回按钮监听器
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
			
		Button right = (Button) this.findViewById(R.id.Button02); // 下一章
		right.setOnClickListener // 返回按钮监听器
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createNewList(groupPosition, childPosition + 1);
			}
		});
		
		
		return true;
	}
	
	public String loads(String name)						//加载assets文件方法
    { 	String nr = null;    									//内容字符串	
    	try 
    	{	//打开对应名称文件的输入流
    		InputStream is=this.getResources().getAssets().open(name);
    		int ch=0;										
    		//创建字节数组输出流
    		ByteArrayOutputStream baos=new ByteArrayOutputStream();
    		while((ch=is.read())!=-1)
    		{	baos.write(ch);		}						//读取文件
    		byte[] buff=baos.toByteArray();					//转化为字节数组
    		baos.close();									//关闭输入输出流
    		is.close();										//关闭输入输出流
			nr=new String(buff,"utf-8");					//转码生产新字符串
			nr=nr.replaceAll("\\r\\n","\n");				//替换换行符等空白字符
		} catch (Exception e) 
    	{	//没有找到对应文件，进行提示
			Toast.makeText(getBaseContext(), "对不起，没有找到指定文件。", Toast.LENGTH_LONG).show();
		}    	
			return nr;    										//返回内容字符串	
    }
	
}
