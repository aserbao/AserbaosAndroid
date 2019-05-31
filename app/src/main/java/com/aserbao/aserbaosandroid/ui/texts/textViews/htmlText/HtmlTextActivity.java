package com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.utils.LinkMovementMethodExt;
import com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.utils.MImageGetter;
import com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.utils.MTagHandler;
import com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.utils.MessageSpan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 文章代码来自：https://www.ibm.com/developerworks/cn/web/1407_zhangqian_androidhtml/index.html的示例代码
 */
public class HtmlTextActivity extends AppCompatActivity {

    @BindView(R.id.html_tv)
    TextView mHtmlTv;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_text);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mContext = this;
//        new ArtUpdater("demo/demo.html","demo/imgs/").execute();
        new ArtUpdater("demo/notice.html","demo/imgs/").execute();

    }

    class ArtUpdater extends AsyncTask<Void, Void, Void> {

        public ArtUpdater(String artAdresss, String artImg) {
            this.artAdresss  = artAdresss;
            this.artImg = artImg;
        }

        String artAdresss;
        String artImg;
        String htmlContent;

        @SuppressLint("HandlerLeak")
        @Override
        protected void onPostExecute(Void result) {
            if (htmlContent != null) {

                htmlContent = htmlContent.replace("<img src=\"imgs/", "<br><img src=\"" + artImg);

                htmlContent = htmlContent.replaceAll("<head>([\\s\\S]*)<\\/head>", "");
                if (htmlContent.contains("><p>")) {
                    String regularExpression1 = "(<[^\\/]\\w><p>)";
                    Pattern pat1 = Pattern.compile(regularExpression1);
                    Matcher mat1 = pat1.matcher(htmlContent);
                    if (mat1.find()) {
                        for (int i = 0; i < mat1.groupCount(); i++) {
                            System.out.println(mat1.group(i));
                            String temp = mat1.group(i).replace("<p>", "");
                            htmlContent = htmlContent.replace(mat1.group(i), temp);
                            String tail = temp.replace("<", "</");
                            htmlContent = htmlContent.replace("</p>" + tail, tail);
                            System.out.println(htmlContent);
                        }
                    }
                }
            }
            try{
                mHtmlTv.setText(Html.fromHtml(htmlContent));
//                mHtmlTv.setText(Html.fromHtml(htmlContent, new MImageGetter(mHtmlTv,HtmlTextActivity.this), new MTagHandler()));
//                mHtmlTv.setText(Html.fromHtml(htmlContent, new MImageGetter(mHtmlTv,HtmlTextActivity.this),null));
                Handler handler = new Handler() {
                    public void handleMessage(Message msg) {
                        int what = msg.what;
                        if (what == 200) {
                            MessageSpan ms = (MessageSpan) msg.obj;
                            Object[] spans = (Object[])ms.getObj();

                            for (Object span : spans) {
                                if (span instanceof ImageSpan) {
                                    // TODO: 2018/9/25 跳图片界面 
                                    Toast.makeText(mContext, "显示图片……", Toast.LENGTH_SHORT).show();
                                   /* Intent intent = new Intent(mContext, ShowPicActivity.class);
                                    Bundle bundle = new Bundle();

                                    bundle.putString("picUrl",((ImageSpan) span).getSource());
                                    intent.putExtras(bundle);

                                    startActivity(intent);*/
                                }
                            }
                        }
                    };
                };
                mHtmlTv.setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
                mHtmlTv.setVisibility(View.VISIBLE);
            } catch (Throwable e){
                if(e != null){
                    e.printStackTrace();
                }
            }
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                String htmlString = null;

                htmlString = getFromAssets(HtmlTextActivity.this ,artAdresss);

                htmlContent = htmlString;

            } catch (Exception e) {
                return null;
            }

            return null;
        }

    }
    public static String getFromAssets(Context context, String fileName) {
        String result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context
                    .getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
