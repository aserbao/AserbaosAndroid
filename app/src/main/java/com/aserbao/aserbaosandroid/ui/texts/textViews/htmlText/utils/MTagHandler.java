/*
 * Copyright (C) 2013 Mohammed Lakkadshaw
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.utils;


import android.text.Editable;
import android.text.Html;
import android.text.style.BulletSpan;
import android.text.style.LeadingMarginSpan;

import org.xml.sax.XMLReader;

import java.util.Vector;

public class MTagHandler implements Html.TagHandler {
    private int mListItemCount = 0;
    private Vector<String> mListParents = new Vector<String>();

    @Override
    public void handleTag(final boolean opening, final String tag, Editable output, final XMLReader xmlReader) {

        if (tag.equals("ul") || tag.equals("ol") || tag.equals("dd")) {
            if (opening) {
                mListParents.add(tag);
            } else mListParents.remove(tag);

            mListItemCount = 0;
        } else if (tag.equals("li") && !opening) {
            handleListTag(output);
        } 
    }

 
    private void handleListTag(Editable output) {
        if (mListParents.lastElement().equals("ul")) {
            output.append("\n");
            String[] split = output.toString().split("\n");

            int lastIndex = split.length - 1;
            int start = output.length() - split[lastIndex].length() - 1;
            output.setSpan(new BulletSpan(15 * mListParents.size()), start, output.length(), 0);
        } else if (mListParents.lastElement().equals("ol")) {
            mListItemCount++;

            output.append("\n");
            String[] split = output.toString().split("\n");

            int lastIndex = split.length - 1;
            int start = output.length() - split[lastIndex].length() - 1;
            output.insert(start, mListItemCount + ". ");
            output.setSpan(new LeadingMarginSpan.Standard(15 * mListParents.size()), start, output.length(), 0);
        }
    }
} 
