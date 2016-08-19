package com.happycake.storage;

import com.sukhaniuk.models.Dish;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class DishStorage extends ArrayList<Dish> {
    /**
     * Get all dish by category id
     *
     * @param id category id
     * @return dish list
     */
    public ArrayList<Dish> getByCategoryId(int id) {
        ArrayList<Dish> result = new ArrayList();
        for (Dish elements : this) {
            if (elements.getCategoryId() == id) {
                result.add(elements);
            }
        }
        return result;
    }
}
