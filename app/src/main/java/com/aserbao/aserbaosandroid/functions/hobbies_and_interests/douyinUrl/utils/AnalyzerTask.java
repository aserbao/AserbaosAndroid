package com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.interfaces.IAnalyzeTaskCallBack;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.beans.DouyinV2;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.beans.Video;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.exception.URLInvalidException;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.exception.VideoException;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.os.AsyncTaskResult;
import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.parser.VideoParser;


public class AnalyzerTask extends AsyncTask<String, Integer, AsyncTaskResult<Video>>  {

    private Context context;
    private IAnalyzeTaskCallBack listener;

    public AnalyzerTask(Context context, IAnalyzeTaskCallBack listener)
    {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected AsyncTaskResult<Video> doInBackground(String ...params) {
        String str = params[0];
        VideoParser parser = null;

        if (str.matches(this.context.getString(R.string.url_douyin_regex))) {
            parser = DouyinV2.getInstance(this.context);
        }

        try {
            if (parser == null)
                throw new URLInvalidException(this.context.getString(R.string.exception_invalid_url));
            Video video = parser.get(str);

            if (video == null || video.isEmpty())
                throw new VideoException(this.context.getString(R.string.exception_invalid_video));

            return new AsyncTaskResult<>(video);

        } catch (Exception e) {
            return new AsyncTaskResult<>(e);
        }

    }

    @Override
    protected void onPostExecute(AsyncTaskResult<Video> result) {
        super.onPostExecute(result);

        if (isCancelled())
            listener.onCanceled();
        else if (result.getError() != null)
            listener.onAnalyzeError(result.getError());
        else
            listener.onAnalzed(result.getResult());

    }

}
