package com.aserbao.aserbaosandroid.aaThird.pickvideo.callback;

import com.aserbao.aserbaosandroid.aaThird.pickvideo.beans.BaseFile;
import com.aserbao.aserbaosandroid.aaThird.pickvideo.beans.Directory;

import java.util.List;

/**
 * Created by Vincent Woo
 * Date: 2016/10/11
 * Time: 11:39
 */

public interface FilterResultCallback<T extends BaseFile> {
    void onResult(List<Directory<T>> directories);
}
