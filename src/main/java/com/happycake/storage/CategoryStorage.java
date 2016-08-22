package com.happycake.storage;

import com.sukhaniuk.models.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Shyshkin Vladyslav on 19.08.2016.
 */
public class CategoryStorage extends ArrayList<Category> {
    private HashMap<Integer,Category> map = new HashMap();
    private final int RANDOM_ELEMENTS_ON_PAGE = 3;

    @Override
    public boolean add(Category category) {
        map.put(category.getId(),category);
        return super.add(category);
    }

    /**
     * Get rand category
     *
     * @return arraylist of new rand categoryes
     */
    public ArrayList getRand() {
        Random random = new Random();
        int mas[] = new int[3];
        int i = 0;
        do {
            int rand = random.nextInt(this.size());
            if (!containsNumber(mas, rand)) {
                mas[i] = rand;
                i++;
            }
        } while (i < RANDOM_ELEMENTS_ON_PAGE);
        ArrayList result = new ArrayList();
        for (int el : mas) {
            result.add(this.get(el));
        }
        return result;
    }

    /**
     * Check if mas contains the number
     *
     * @param mas    arraylist of numbers
     * @param number search number
     * @return conteins or not
     */
    private boolean containsNumber(int[] mas, int number) {
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get arrayelement by id
     * @param id category id
     * @return category object
     */
    public Category getById(int id){
        return map.get(id);
    }
}
