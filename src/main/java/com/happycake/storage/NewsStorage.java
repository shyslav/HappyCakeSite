package com.happycake.storage;

import com.sukhaniuk.models.News;

import java.util.*;
/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class NewsStorage extends HashMap<Integer, News> {
    @Override
    public News put(Integer key, News value) {
        return super.put(key, value);
    }

    /**
     * Get 3 popular news
     * @return arraylist of popular news
     */
    public ArrayList<News> getPopular() {
        ArrayList<News> result = new ArrayList();
        List<News> listOfNews = new ArrayList(this.values());
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

    public ArrayList<News> getByTag(String teg){
        ArrayList<News> result = new ArrayList();
        for (News element: this.values()){
            if(element.getTegs().contains(teg)){
                result.add(element);
            }
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
        for (News element: this.values()){
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
    public ArrayList<News> getById(int id){
        ArrayList result = new ArrayList();
        result.add(super.get(id));
        return result;
    }

}
