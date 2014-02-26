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
				{ "���Ͼ�", "�������", "�ľ�"};
			private String[][] arms = new String[][]
			{
				{ "��������Ͼ� ��һ��", "��������Ͼ� �ڶ���", "��������Ͼ� ������", "��������Ͼ� ���ľ�", "��������Ͼ� �����", "��������Ͼ� ������", "��������Ͼ� ���߾�", "��������Ͼ� �ڰ˾�", "��������Ͼ� �ھž�", "��������Ͼ� ��ʮ��" },
				{ "С��", "����", "����", "�Ա��ɻ�" },
				{ "���������۶��ľ�", "�ľ� Ҫ��" , "�ľ� ����" }
			};
			// ��ȡָ����λ�á�ָ�����б�������б�������
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

			// �÷�������ÿ����ѡ������
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent)
			{
				TextView textView = getTextView();
				textView.setText(getChild(groupPosition, childPosition).toString());
				return textView;
			}

			// ��ȡָ����λ�ô���������
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

			// �÷�������ÿ����ѡ������
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
	                Toast.makeText(MainActivity.this, "�������˵�" + groupPosition + "�飬��" + childPosition + "��", Toast.LENGTH_SHORT).show();  
	        
	                createNewList(groupPosition, childPosition);
	                	                
	                return false;  
	            }  
	     }); 
				
	}

	private boolean createNewList(final int groupPosition, final int childPosition)
	{
		super.onCreate(saveInstanceState);
		setContentView(R.layout.chapters);
		
		//����loadText������ȡ��Ӧ�ļ������ļ�
		String name = Integer.toString(groupPosition) + Integer.toString(groupPosition) + Integer.toString(childPosition) + ".txt";
		String nr=loads(name);
		EditText et2=(EditText)findViewById(R.id.EditText);
		//������ʾ������
		et2.setText(nr);
		
		
		Button left = (Button) this.findViewById(R.id.Button00); // ������һ��
		left.setOnClickListener // ���ذ�ť������
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createNewList(groupPosition, childPosition - 1);
			}
		});
		
		Button middle = (Button) this.findViewById(R.id.Button01); // ���ظ�Ŀ¼
		middle.setOnClickListener // ���ذ�ť������
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
			
		Button right = (Button) this.findViewById(R.id.Button02); // ��һ��
		right.setOnClickListener // ���ذ�ť������
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createNewList(groupPosition, childPosition + 1);
			}
		});
		
		
		return true;
	}
	
	public String loads(String name)						//����assets�ļ�����
    { 	String nr = null;    									//�����ַ���	
    	try 
    	{	//�򿪶�Ӧ�����ļ���������
    		InputStream is=this.getResources().getAssets().open(name);
    		int ch=0;										
    		//�����ֽ����������
    		ByteArrayOutputStream baos=new ByteArrayOutputStream();
    		while((ch=is.read())!=-1)
    		{	baos.write(ch);		}						//��ȡ�ļ�
    		byte[] buff=baos.toByteArray();					//ת��Ϊ�ֽ�����
    		baos.close();									//�ر����������
    		is.close();										//�ر����������
			nr=new String(buff,"utf-8");					//ת���������ַ���
			nr=nr.replaceAll("\\r\\n","\n");				//�滻���з��ȿհ��ַ�
		} catch (Exception e) 
    	{	//û���ҵ���Ӧ�ļ���������ʾ
			Toast.makeText(getBaseContext(), "�Բ���û���ҵ�ָ���ļ���", Toast.LENGTH_LONG).show();
		}    	
			return nr;    										//���������ַ���	
    }
	
}
