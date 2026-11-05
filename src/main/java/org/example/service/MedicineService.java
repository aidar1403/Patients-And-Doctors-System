package org.example.service;

import org.example.model.Medicine;
import org.example.model.MedicineCategory;
import java.util.*;

public class MedicineService {
    private long nextId = 1;
    public long getNextId() {
        return nextId++;
    }
    private final Map<String, Medicine> medicinesBySku = new HashMap<>();
    private static final Map<MedicineCategory,List<Medicine>> medicinesByCategory
            = new EnumMap<>(MedicineCategory.class);

    public void add(Medicine m) {
        medicinesBySku.put(m.getSku(),m);
        medicinesByCategory.computeIfAbsent(m.getCategory(), k-> new ArrayList<>()).add(m);
    }

    public Medicine findBySku(String sku) {
        return medicinesBySku.get(sku);
    }

    public static List<Medicine> getByCategory(MedicineCategory c) {
        return medicinesByCategory.getOrDefault(c,new ArrayList<>());


    }
}
