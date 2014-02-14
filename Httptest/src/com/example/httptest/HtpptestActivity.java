package com.example.httptest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


/*
 * 非同期通信でPOSTリクエストをする
 */
public class HtpptestActivity extends Activity implements OnClickListener {


        private Button btn = null;
        private TextView tv = null;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            btn = (Button)findViewById(R.id.btn1);
            tv = (TextView)findViewById(R.id.tv1);

            btn.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            // ボタン押下時
            if( v == btn )
            {
                exec_post1();
            }
        }



    // POST通信を実行（AsyncTaskによる非同期処理を使うバージョン）
//チェック通信
    private void exec_post1() {

        // 非同期タスクを定義
        HttpPostTask task = new HttpPostTask(
                this,
                "http://ip.jsontest.com/?callback=showMyIP",

                // タスク完了時に呼ばれるUIのハンドラ
                new HttpPostHandler(){

                    @Override
                    public void onPostCompleted(String response) {
                    	
                        ////////responseを解析///////
                        //JSONを解析	
                    	
                    	try {
                        	JSONObject jsonObj = new JSONObject();
                        	jsonObj.put("ip", "123.218.185.136");

                        	//res_codeを取り出す
                                String resCode =jsonObj.getString ("ip");

                                Toast.makeText(
                                        getApplicationContext(),
                                        resCode,
                                        Toast.LENGTH_LONG
                                ).show();



                    	}catch (JSONException e) {
                            e.printStackTrace();

                    
                        }
                    }
                    @Override
                    public void onPostFailed(String response) {

                        Toast.makeText(
                                getApplicationContext(),
                                "エラーが発生しました。636",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                 }
        );;

        //リクエストコードをセット
        task.addPostParam( "ip", "126.185.23.216" );

        // タスクを開始
        task.execute();


    }


    }