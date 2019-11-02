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

import java.util.ArrayList;

public class MainActivity_CongNo extends AppCompatActivity {

    ArrayList<List_congno> listview;
    CongNo_listviewAdapter congno_listviewAdapter;
    ListView listViewCN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congno_main);
        showlstview();
    }
    private void showlstview() {

        listview = new ArrayList<>();
        listview.add(new List_congno(1,"Công nghệ phần mềm",1335000,1335000,0));
        listview.add(new List_congno(2,"Truyền thông kỹ thuật số",1335000,0,1335000));
        listview.add(new List_congno(3,"Lập trình mã nguồn mở",1335000,1335000,0));
        listview.add(new List_congno(4,"Thực hành lập trình mã nguồn mở",890000,890000,0));
        listview.add(new List_congno(5,"Lập trình window nâng cao",1335000,1335000,0));
        listview.add(new List_congno(6,"Thực hành công nghệ web",575000,575000,0));
        listview.add(new List_congno(7,"Toán kỹ thuật",890000,890000,0));
        listview.add(new List_congno(8,"Lập trình trên thiết bị di động",1335000,1335000,0));
        listview.add(new List_congno(9,"Mạng máy tính",890000,890000,0));
        listview.add(new List_congno(10,"Trí tuệ nhân tạo",1335000,1335000,0));
        listview.add(new List_congno(11,"Đồ án môn học",575000,575000,0));
        listview.add(new List_congno(12,"Công nghệ web",1335000,0,1335000));

        congno_listviewAdapter = new CongNo_listviewAdapter(listview);

        listViewCN = (ListView)findViewById(R.id.lstcongno);
        listViewCN.setAdapter(congno_listviewAdapter);

        listViewCN.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                List_congno congno = (List_congno) congno_listviewAdapter.getItem(position);
                Toast.makeText(MainActivity_CongNo.this,congno.tenmh, Toast.LENGTH_LONG).show();
            }
        });
    }
    class List_congno{
        String tenmh;
        double thanhtien;
        double danop;
        double congno;
        int listID;
        public List_congno(int listID,String tenmh, double thanhtien,double danop, double congno){
            this.listID=listID;
            this.tenmh = tenmh;
            this.thanhtien = thanhtien;
            this.danop = danop;
            this.congno = congno;
        }
    }
    class CongNo_listviewAdapter extends BaseAdapter {
        final ArrayList<List_congno> listview;
        CongNo_listviewAdapter(ArrayList<List_congno> listview){this.listview = listview;}

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
                ViewTTSV =View.inflate(parent.getContext(),R.layout.congno_view,null);
            }else   ViewTTSV = convertView;
            //Bind dữ liệu phần tử vào View
            List_congno congno = (List_congno) getItem(position);
            ((TextView) ViewTTSV.findViewById(R.id.tenmh)).setText(String.format("%s",congno.tenmh));
            ((TextView) ViewTTSV.findViewById(R.id.thanhtien)).setText(String.format("%s",congno.thanhtien));
            ((TextView) ViewTTSV.findViewById(R.id.danop)).setText(String.format("%s",congno.danop));
            ((TextView) ViewTTSV.findViewById(R.id.congno)).setText(String.format("%s",congno.congno));
            return ViewTTSV;
        }
    }
}
