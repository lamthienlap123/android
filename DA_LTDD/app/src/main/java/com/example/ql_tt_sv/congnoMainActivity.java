package com.example.ql_tt_sv;

import android.os.Bundle;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class congnoMainActivity extends AppCompatActivity {

    ArrayList<Product> listProduct;
    ProductListViewAdapter productListViewAdapter;
    ListView listViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congno);

        //Khoi tao ListProduct
        listProduct = new ArrayList<>();
        listProduct.add(new Product(1000, 1000, 500));
        listProduct.add(new Product(2, 3000, 700));
        listProduct.add(new Product(3, 4000, 800));
        listProduct.add(new Product(4, 750, 900));
        listProduct.add(new Product(5, 5469, 500));
        listProduct.add(new Product(6, 1315, 700));
        listProduct.add(new Product(7, 0165, 800));
        listProduct.add(new Product(8, 4653, 900));


        listViewProduct = findViewById(R.id.id_congno);
        productListViewAdapter = new ProductListViewAdapter(listProduct);
        listViewProduct.setAdapter(productListViewAdapter);


        //Lắng nghe bắt sự kiện một phần tử danh sách được chọn
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) productListViewAdapter.getItem(position);
                //Làm gì đó khi chọn sản phẩm (ví dụ tạo một Activity hiện thị chi tiết, biên tập ..)
                Toast.makeText(congnoMainActivity.this, product.name, Toast.LENGTH_LONG).show();
            }
        });
    }

    //Model phần tử dữ liệu hiện
    class Product {
        int name;
        int price;
        int productID;

        public Product(int productID, int name, int price) {
            this.name = name;
            this.price = price;
            this.productID = productID;
        }

        public int getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }
    }

    class ProductListViewAdapter extends BaseAdapter {

        //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
        final ArrayList<Product> listProduct;

        ProductListViewAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }

        @Override
        public int getCount() {
            //Trả về tổng số phần tử, nó được gọi bởi ListView
            return listProduct.size();
        }

        @Override
        public Object getItem(int position) {
            //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
            //có chỉ số position trong listProduct
            return listProduct.get(position);
        }

        @Override
        public long getItemId(int position) {
            //Trả về một ID của phần
            return listProduct.get(position).productID;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
            //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
            //Nếu null cần tạo mới

            View viewProduct;
            if (convertView == null) {
                viewProduct = View.inflate(parent.getContext(), R.layout.activity_item_congno, null);
            } else viewProduct = convertView;

            //Bind sữ liệu phần tử vào View
            Product product = (Product) getItem(position);
            ((TextView) viewProduct.findViewById(R.id.muchocphi)).setText(String.format("ID = %d", product.productID));
            ((TextView) viewProduct.findViewById(R.id.danop)).setText(String.format("Tên SP : %s", product.name));
            ((TextView) viewProduct.findViewById(R.id.no)).setText(String.format("Giá %d", product.price));


            return viewProduct;
        }
    }
}
