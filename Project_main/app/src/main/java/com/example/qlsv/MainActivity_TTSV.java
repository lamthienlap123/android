package com.example.qlsv;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv.R;

import java.util.ArrayList;

public class MainActivity_TTSV extends AppCompatActivity {

    ArrayList<List_ttsv> listview;
    TTSV_listviewAdapter ttsv_listviewAdapter;
    ListView listViewTTSV;
    ImageView imageuser;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttsv_main);
        showlstview();
        imageandname();
    }

    private void imageandname() {
        imageuser = (ImageView)findViewById(R.id.icon_ttsv);
        name = (TextView)findViewById(R.id.nameuser);
    }

    private void showlstview() {

        listview = new ArrayList<>();
        listview.add(new List_ttsv(1,"Trạng Thái:","Đang học"));
        listview.add(new List_ttsv(2,"Giới Tính:","Nam"));
        listview.add(new List_ttsv(3,"Ngày Sinh:", "22/07/1998"));
        listview.add(new List_ttsv(4,"MSSV:","2001160174"));
        listview.add(new List_ttsv(5,"Lớp:","07DHTH1"));
        listview.add(new List_ttsv(6,"Bậc Đào Tạo:","Đại học"));
        listview.add(new List_ttsv(7,"Khoa:","Công Nghệ Thông Tin"));
        listview.add(new List_ttsv(8,"Ngành:","Công nghệ phần mềm"));
        listview.add(new List_ttsv(9,"Địa Chỉ:", "140 Lê Trọng Tấn"));
        listview.add(new List_ttsv(10,"Số Điện Thoại:","0919375274"));
        listview.add(new List_ttsv(11,"Nơi Sinh:","Cà Mau"));


        /*String[] arrstring = {"Trạng thái:","Giới Tính:","Ngày Sinh:"
                ,"MSSV:","Lớp:","Bậc Đào Tạo:","Khoa:","Chuyên Ngành","Địa Chỉ:","Số Điện Thoại:","Nơi Sinh:"};
        for (int i=0; i<arrstring.length;i++)
        {
            arrayTT.add(arrstring[i]);

        }*/
        ttsv_listviewAdapter = new TTSV_listviewAdapter(listview);

        listViewTTSV = (ListView)findViewById(R.id.lstdstt);
        listViewTTSV.setAdapter(ttsv_listviewAdapter);

        listViewTTSV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                List_ttsv ttsv = (List_ttsv) ttsv_listviewAdapter.getItem(position);
                Toast.makeText(MainActivity_TTSV.this,ttsv.nmtext, Toast.LENGTH_LONG).show();
            }
        });
    }
    class List_ttsv{
        String lsttext;
        String nmtext;
        int listID;
        public List_ttsv(int listID, String lsttext, String nmtext){
            this.listID=listID;
            this.lsttext = lsttext;
            this.nmtext = nmtext;
        }
    }
    class TTSV_listviewAdapter extends BaseAdapter {
        final ArrayList<List_ttsv> listview;
        TTSV_listviewAdapter(ArrayList<List_ttsv> listview){this.listview = listview;}

        @Override
        public int getCount() {
            return listview.size();
        }
        @Override
        public Object getItem(int position){
            return listview.get(position);
        }

        @Override
        public long getItemId(int position) {
            return listview.get(position).listID;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View ViewTTSV;
            if(convertView == null){
                ViewTTSV =View.inflate(parent.getContext(),R.layout.ttsv_view,null);
            }else   ViewTTSV = convertView;
            //Bind dữ liệu phần tử vào View
            List_ttsv ttsv = (List_ttsv) getItem(position);
            ((TextView) ViewTTSV.findViewById(R.id.list_text)).setText(String.format("%s",ttsv.lsttext));
            ((TextView) ViewTTSV.findViewById(R.id.name_text)).setText(String.format("%s",ttsv.nmtext));
            return ViewTTSV;
        }
    }
}
