package com.arkainfoteck.smallbasket.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.arkainfoteck.smallbasket.model.ShoppingCartModel;

public class CartDatabse extends SQLiteOpenHelper {
    String query;
    private static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "smallbasketdatabase.db";
    public static final String TABLE_NAME = "carttable";
    public static final String PRODUCT_ID = "productid";
    public static final String PRODUCT_R_ID = "productrid";

    public static final String PRODUCT_NAME = "productname";
    public static final String PRODUCT_MRP_COST = "productmrp";
    public static final String PRODUCT_RS_COST = "productrs";
    public static final String PRODUCT_IMAGE = "productimage";
    public static final String PRODUCT_PRODUCT_TYPE_ONE = "producttype";
    public static final String PRODUCT_RESTAURANT_NAME= "productrestaurant";
    public static final String PRODUCT_RESTAURANT_APRICE= "productaprice";
    public static final String PRODUCT_RESTAURANT_COUNT= "productcount";

    String count;
    int total;
    public CartDatabse(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


     //   model.getMrp_cost(),model.getRs_cost(),model.getImage(),model.getProduct_type_one(),model.getRestaurant_name(),model.getAprice12(), String.valueOf(count));

         query = "CREATE TABLE "
                + TABLE_NAME + "(" + PRODUCT_ID + " TEXT," + PRODUCT_R_ID
                + " TEXT," + PRODUCT_NAME + " TEXT,"  + PRODUCT_MRP_COST + " TEXT,"  + PRODUCT_RS_COST + " TEXT,"  + PRODUCT_IMAGE+ " TEXT,"  + PRODUCT_PRODUCT_TYPE_ONE+ " TEXT,"  + PRODUCT_RESTAURANT_NAME+ " TEXT,"  + PRODUCT_RESTAURANT_APRICE+ " TEXT,"  + PRODUCT_RESTAURANT_COUNT
                + " TEXT" + ")";

        db.execSQL(query);
    }

    // add these two fileds for more information
   // + PRODUCT_SHOP_NAME + " TEXT," + PRODUCT_ + " TEXT,"

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }


    public boolean insertdata(String id,String rid,String name,String mrp,String rs,String image,String product_type,String restaurant,String aprice,String count) {

        SQLiteDatabase sqLiteDatabas = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_ID, id);
        contentValues.put(PRODUCT_R_ID, rid);
        contentValues.put(PRODUCT_NAME, name);
        contentValues.put(PRODUCT_MRP_COST, mrp);
        contentValues.put(PRODUCT_RS_COST, rs);
        contentValues.put(PRODUCT_IMAGE, image);
        contentValues.put(PRODUCT_PRODUCT_TYPE_ONE, product_type);
        contentValues.put(PRODUCT_RESTAURANT_NAME, restaurant);
        contentValues.put(PRODUCT_RESTAURANT_APRICE, aprice);
        contentValues.put(PRODUCT_RESTAURANT_COUNT,count);

        long result = sqLiteDatabas.insert(TABLE_NAME, null, contentValues);
      //  sqLiteDatabas.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getdata() {
        SQLiteDatabase sqLiteDatabas = this.getWritableDatabase();
        Cursor res = sqLiteDatabas.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }

    public  String getCountvalue(String productname,String producttype,String productcount){

        SQLiteDatabase sqLiteDatabas = this.getWritableDatabase();
     //   SELECT productrs FROM 'carttable' where productname='Moong Dal' and producttype='grocery' and productcount='1';
       // Cursor res = sqLiteDatabas.rawQuery("select  productrs from " + TABLE_NAME+" where productname="'" Moong Dal and producttype= grocery and productcount= 1", null);
        Cursor  c = sqLiteDatabas.rawQuery("SELECT * FROM carttable WHERE productname = ? and  producttype = ? and productrestaurant = ? ", new String[] {productname,producttype,productcount});
        while (c.moveToNext())
        {
          count=  c.getString(9);
        }
        System.out.println("get_count_table"+count);
        return count;
    }

    public  String getCountvalueDates(String productname,String producttype,String productcount){

        SQLiteDatabase sqLiteDatabas = this.getWritableDatabase();
        //   SELECT productrs FROM 'carttable' where productname='Moong Dal' and producttype='grocery' and productcount='1';
        // Cursor res = sqLiteDatabas.rawQuery("select  productrs from " + TABLE_NAME+" where productname="'" Moong Dal and producttype= grocery and productcount= 1", null);
        Cursor  c = sqLiteDatabas.rawQuery("SELECT COUNT(*) FROM carttable WHERE productname = ? and  producttype = ? and productrestaurant = ? ", new String[] {productname,producttype,productcount});

        System.out.println("get_count_table"+ c.getCount());
        return count;
    }
    public int getFinalCouunt(){

        SQLiteDatabase sqLiteDatabas = this.getWritableDatabase();

         String GET_TOTAL_COUNT="SELECT sum(productmrp*productcount)as producttotal FROM "+TABLE_NAME;
         Cursor  cursor = sqLiteDatabas.rawQuery( GET_TOTAL_COUNT,null);
        if (cursor.moveToFirst()) {
            total = cursor.getInt(cursor.getColumnIndex("producttotal"));
        }
        return total;
    }
    public boolean CheckIsDataAlreadyInDBorNot(String productname,
                                               String producttype, String productcount) {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor  cursor = db.rawQuery("SELECT COUNT(*) as totalrowcount FROM carttable WHERE productname = ? and  producttype = ? and productrestaurant = ? ", new String[] {productname,producttype,productcount});
        cursor.moveToFirst();


       int  total = cursor.getInt(cursor.getColumnIndex("totalrowcount"));
        System.out.println("get_count_for_cursorreal"+total);

        if (total<= 0) {
            System.out.println("get_count_for_cursor12" + total);

            cursor.close();
            return false;
        }
        System.out.println("get_count_for_cursor"+total);
        cursor.close();
        return true;
    }


    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean UpdateQuery(String id,String rid,String name,String mrp,String rs,String image,String  product_type,String restaurant,String aprice,String count){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();
       System.out.println("Database_name"+name);

        cv.put(PRODUCT_ID, id);
        cv.put(PRODUCT_R_ID, rid);
        cv.put(PRODUCT_NAME, name);
        cv.put(PRODUCT_MRP_COST, mrp);
        cv.put(PRODUCT_RS_COST, rs);
        cv.put(PRODUCT_IMAGE, image);
        cv.put(PRODUCT_PRODUCT_TYPE_ONE, product_type);
        cv.put(PRODUCT_RESTAURANT_NAME, restaurant);
        cv.put(PRODUCT_RESTAURANT_APRICE, aprice);
        cv.put(PRODUCT_RESTAURANT_COUNT,count);

   //     db.update(TABLE_NAME, cv,null , null);
 //       db.update(TABLE_NAME, cv, "productname="+name, null);
        db.update(TABLE_NAME, cv, "productname = ? and  producttype = ? and productrestaurant = ? ",new String[] { name ,product_type,restaurant});

        return true;
    }

    public Integer deleteData (String name,String product_type,String restaurant ) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "productname = ? and  producttype = ? and productrestaurant = ? ",new String[] {name,product_type,restaurant});
    }


    public int deleteConformOrderData(){
        SQLiteDatabase db=getWritableDatabase();
        int count= db.delete(TABLE_NAME,null,null);
        db.close();
        return count;
    }
}



