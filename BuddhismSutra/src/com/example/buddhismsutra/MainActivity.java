package com.example.buddhismsutra;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("CutPasteId")
public class MainActivity extends Activity {

	Bundle saveInstanceState;
	ExpandableListAdapter adapter_bak;
	private String[] armTypes = new String[]
			{ "���Ͼ�", "�������", "�ľ�"};
	private String[][] arms = new String[][]
	{
		{ "��������Ͼ� ��һ��", "��������Ͼ� �ڶ���", "��������Ͼ� ������", "��������Ͼ� ���ľ�", "��������Ͼ� �����", "��������Ͼ� ������", "��������Ͼ� ���߾�", "��������Ͼ� �ڰ˾�", "��������Ͼ� �ھž�", "��������Ͼ� ��ʮ��" },
		{ "�������", "��һ�� ������� ��Ʒ", "�ڶ��� ������� ����Ʒ", "������ ������� Ʃ��Ʒ", "���ľ� ������� �Ž�Ʒ", "����� ������� ҩ����Ʒ", "������ ������� �ڼ�Ʒ", "���߾� ������� ������Ʒ", "�ڰ˾� ������� ��ٵ����ڼ�Ʒ", "�ھž� ������� ��ѧ��ѧ�˼�Ʒ",
		  "��ʮ�� ������� ��ʦƷ", "��ʮһ�� ������� ������Ʒ", "��ʮ���� ������� ���Ŵ��Ʒ", "��ʮ���� ������� Ȱ��Ʒ", "��ʮ�ľ� ������� ������Ʒ", "��ʮ��� ������� �ӵ�ӿ��Ʒ", "��ʮ�߾� ������� �ֱ𹦵�Ʒ", "��ʮ�˾� ������� ��ϲ����Ʒ", "��ʮ�ž� ������� ��ʦ����Ʒ", "�ڶ�ʮ�� ������� ����������Ʒ",
		  "�ڶ�ʮһ�� ������� ��������Ʒ", "�ڶ�ʮ���� ������� ����Ʒ", "�ڶ�ʮ���� ������� ҩ����������Ʒ", "�ڶ�ʮ�ľ� ������� ��������Ʒ", "�ڶ�ʮ��� ������� ��������������Ʒ", "�ڶ�ʮ���� ������� ������Ʒ", "�ڶ�ʮ�߾� ������� ��ׯ��������Ʒ", "�ڶ�ʮ�˾� ������� ��������Ȱ��Ʒ"},
		{ "���������۶��ľ�" }
	};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		saveInstanceState = savedInstanceState;
		setContentView(R.layout.activity_main);
		
		ExpandableListAdapter adapter = new BaseExpandableListAdapter()
		{
			//��������ͼ��ͼƬ
            int[] logos = new int[] { R.drawable.lengyan, R.drawable.fahua,R.drawable.xinjin};
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
				ImageView logo = new ImageView(MainActivity.this);
				
				logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE );
				
	            logo.setImageResource(logos[groupPosition]);
	            logo.setPadding(0, 0, 0, 0);
	            ll.addView(logo);
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
		expandListView.setGroupIndicator(null);
		adapter_bak = adapter;
		
		expandListView.setOnChildClickListener(new OnChildClickListener() {  
	            @Override  
	            public boolean onChildClick(ExpandableListView parent, View v,  
	                    int groupPosition, int childPosition, long id) {  
	                //Toast.makeText(MainActivity.this, "�������˵�" + groupPosition + "�飬��" + childPosition + "��", Toast.LENGTH_SHORT).show();  
	                Toast.makeText(MainActivity.this, "����" + arms[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
	                createNewList(groupPosition, childPosition);                	                
	                return false;  
	            }  
	     }); 
		
				
	}

	private boolean createNewList(final int groupPosition, final int childPosition)
	{
		super.onCreate(saveInstanceState);
		setContentView(R.layout.chapters);
		String name;
		//����loadText������ȡ��Ӧ�ļ������ļ�
		if(childPosition < 10){
			name = Integer.toString(groupPosition) + "0" + Integer.toString(childPosition) + ".txt";				
		}else{
			name = Integer.toString(groupPosition) + Integer.toString(childPosition) + ".txt";
		}
		
		
		String nr=loads(name);
		if(nr == null){
			createHome();
			return true;
		}
		
		
		EditText et2=(EditText)findViewById(R.id.EditText);
		et2.setKeyListener(null);
		//������ʾ������
		et2.setText(nr);
		
		
		Button left = (Button) this.findViewById(R.id.Button00); // ������һ��
		left.setOnClickListener // ���ذ�ť������
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "����" + arms[groupPosition][childPosition], Toast.LENGTH_SHORT).show();  
				createNewList(groupPosition, childPosition - 1);
			}
		});
		
		Button middle = (Button) this.findViewById(R.id.Button01); // ���ظ�Ŀ¼
		middle.setOnClickListener // ���ذ�ť������
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "����Ŀ¼", Toast.LENGTH_SHORT).show();
				createHome();				
			}
		});
			
		Button right = (Button) this.findViewById(R.id.Button02); // ��һ��
		right.setOnClickListener // ���ذ�ť������
		(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "����" + arms[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
				createNewList(groupPosition, childPosition + 1);
			}
		});
		
		
		return true;
	}
	
	public void createHome(){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_main);
		
		ExpandableListView expandListView = (ExpandableListView) findViewById(R.id.list);
		expandListView.setAdapter(adapter_bak);
		
		expandListView.setOnChildClickListener(new OnChildClickListener() {  
            
            @Override  
            public boolean onChildClick(ExpandableListView parent, View v,  
                    int groupPosition, int childPosition, long id) {  
                //Toast.makeText(MainActivity.this, "�������˵�" + groupPosition + "�飬��" + childPosition + "��", Toast.LENGTH_SHORT).show();  
                //Toast.makeText(MainActivity.this, "����" + arms[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                createNewList(groupPosition, childPosition);
                	                
                return false;  
            }  
     }); 
		
		
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
