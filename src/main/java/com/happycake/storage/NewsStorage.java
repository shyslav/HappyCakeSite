package com.happycake.storage;

import com.sukhaniuk.models.news;

import java.util.*;

/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class NewsStorage extends HashMap<Integer, news> {
    @Override
    public news put(Integer key, news value) {
        return super.put(key, value);
    }

    /**
     * Get 3 popular news
     * @return
     */
    public ArrayList<news> getPopular() {
        ArrayList<news> result = new ArrayList();
        List<news> listOfNews = new ArrayList(this.values());
        listOfNews.sort((e1, e2) -> e2.getView() - e1.getView());
        int end = 3;
        if (end > listOfNews.size()) {
            end = listOfNews.size();
        }
        for (int i = 0; i < end; i++) {
            result.add(listOfNews.get(i));
        }
        return result;
    }

    public ArrayList<news> getByArray(){
        ArrayList<news> listOfNews = new ArrayList(this.values());
        return listOfNews;
    }

    public ArrayList<news> uniqueTagArray(){
        ArrayList<news> result = new ArrayList();
        for (news element: this.values()){
            if(!result.contains(element.getTegs())){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public news get(Object key) {
        return super.get(key);
    }

}
