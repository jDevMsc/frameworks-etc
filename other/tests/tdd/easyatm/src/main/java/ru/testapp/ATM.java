package ru.testapp;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ATM {

    //for iteration in desc order
    SortedMap<Integer, Integer> dispensers = new TreeMap<>(Collections.reverseOrder());

    //add notes of different denomination
    public void deposit(int notes, int denomination) {
        int oldValue = dispensers.getOrDefault(denomination, 0);
        dispensers.put(denomination, oldValue + notes);
    }

    public int getBalance() {
        return dispensers.entrySet()
            .stream()
            .mapToInt(entry -> entry.getKey() * entry.getValue())
            .sum();

    }

    //return map notes different denomination
    public Map<Integer, Integer> withDraw(final int amount) {
        Map<Integer, Integer> withdrowal = new HashMap<>();
        int remaining = amount;
        for (Integer denomination : dispensers.keySet()) {
            int notesThereIs = dispensers.get(denomination);
            int notesWantDispence = remaining / denomination;
            int notesToDispence =
                notesWantDispence > notesThereIs ? notesThereIs : notesWantDispence;

            withdrowal.put(denomination, notesToDispence);
            dispensers.compute(denomination, (key, oldValue) -> oldValue - notesToDispence);
            remaining -= notesToDispence * denomination;
        }
        if (remaining > 0) {
           throw new RuntimeException(String.format("Cant dispense amount  %d with dispense %s", remaining, dispensers));
        }
        return withdrowal;
    }
}
