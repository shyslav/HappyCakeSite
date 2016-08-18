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
     * @return arraylist of popular news
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

    /**
     *  Get map by arraylist
     * @return arrylist of news
     */
    public ArrayList getByArray(){
        return new ArrayList(this.values());
    }

    /**
     * Get unique tags
     * @return unique tags list
     */
    public ArrayList uniqueTagArray(){
        ArrayList result = new ArrayList();
        for (news element: this.values()){
            if(!result.contains(element.getTegs())){
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Get news by id
     * @param id id news
     * @return arraylist of news
     */
    public ArrayList<news> getById(int id){
        ArrayList result = new ArrayList();
        result.add(super.get(id));
        return result;
    }

}
