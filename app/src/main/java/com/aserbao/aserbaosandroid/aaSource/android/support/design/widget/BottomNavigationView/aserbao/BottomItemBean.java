package com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.BottomNavigationView.aserbao;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-09-04 17:10
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.BottomNavigationView.aserbao
 */
public class BottomItemBean {
    int unSelectedIcon;     //没选中时图标
    int selectedIcon;       //选中时图标
    int textContent;        //文字内容

    public BottomItemBean(int unSelectedIcon, int selectedIcon, int textContent) {
        this.unSelectedIcon = unSelectedIcon;
        this.selectedIcon = selectedIcon;
        this.textContent = textContent;
    }

    public int getUnSelectedIcon() {
        return unSelectedIcon;
    }

    public void setUnSelectedIcon(int unSelectedIcon) {
        this.unSelectedIcon = unSelectedIcon;
    }

    public int getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(int selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public int getTextContent() {
        return textContent;
    }

    public void setTextContent(int textContent) {
        this.textContent = textContent;
    }
}
