package org.example.service;

import org.example.model.Prescription;
import org.example.model.PrescriptionStatus;
import org.example.model.Medicine;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrescriptionService {
    private final Queue<Prescription> incomingQueue = new PriorityQueue<>();
    private final Map<Long, Prescription> allPrescriptions = new HashMap<>();
    private Prescription currentProcessing;
    private long nextId = 1;

    private final MedicineService medicineService;

    public PrescriptionService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
    public void submitRequest(long patientId, String medicineName) {
        Prescription req = new Prescription(nextId++, patientId, medicineName);
        incomingQueue.offer(req);
        allPrescriptions.put(req.getId(), req);
        System.out.println("Request about a medicine '" + medicineName + "' is sent.");
    }

    public Prescription processNext() {
        Prescription req = incomingQueue.poll();
        if (req == null) {
            System.out.println("Queue is empty! No more Requests.");
        } else {
            req.setStatus(PrescriptionStatus.PROCESSING);
            this.currentProcessing = req;
            System.out.println("You took a Request #" + req.getId() + ". Status: PROCESSING.");
        }

        return req;
    }

    public void showQueue() {
        if (incomingQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            for (Prescription p : incomingQueue) {
                System.out.println(p);
            }
        }
    }

    public void completeCurrentRequest() {
        if (currentProcessing == null) {
            System.out.println("You don't have a request to complete it.");
            return;
        }
        currentProcessing.setStatus(PrescriptionStatus.ISSUED);
        System.out.println("Request #" + currentProcessing.getId() + " issued successfully! Status: ISSUED.");
        currentProcessing = null;
    }
    public void completeCurrentRequest(String sku) {
        if (currentProcessing == null) {
            System.out.println("У вас нет активных заявок.");
            return;
        }


        Medicine med = medicineService.findBySku(sku);

        if (med == null) {
            System.out.println("Ошибка: Лекарства с артикулом " + sku + " нет в базе!");
            return;
        }

        // 2. Проверяем, есть ли оно на складе
        if (med.getInStock() <= 0) {
            System.out.println("Ошибка: Лекарство '" + med.getName() + "' закончилось на складе!");
            return;
        }

        med.setInStock(med.getInStock() - 1);
        currentProcessing.setStatus(PrescriptionStatus.ISSUED);

        System.out.println("✔ Успешно! Пациенту выдано лекарство: " + med.getName());
        System.out.println("Остаток на складе: " + med.getInStock());
        System.out.println("Заявка #" + currentProcessing.getId() + " закрыта (ISSUED).");

        currentProcessing = null; // Очищаем руки
    }
}