package com.happycake.storage;

import com.sukhaniuk.models.dish;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class DishStorage extends ArrayList<dish> {
    /**
     * Get all dish by category id
     *
     * @param id category id
     * @return dish list
     */
    public ArrayList<dish> getByCategoryId(int id) {
        ArrayList<dish> result = new ArrayList();
        for (dish elements : this) {
            if (elements.getCategoryId() == id) {
                result.add(elements);
            }
        }
        return result;
    }
}