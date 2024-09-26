package com.dev.generators.catboot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CatbootService {
    private List arrayList = new ArrayList<String>();
    public void catlist(){
        arrayList.add("Meow");
        arrayList.add("Meeow");
        arrayList.add("Meeeow");
        arrayList.add("Meoow");
        arrayList.add("Meooow");
        arrayList.add("Meoww");
        arrayList.add("Meowww");



    }



    public StringBuilder catRepeat() {
        catlist();

        Random rand = new Random();
        int wordCounter = rand.nextInt(5,10);

        StringBuilder repeatedFromcat = new StringBuilder();

        for (int i=0;i<wordCounter;i++){
            int word=rand.nextInt(arrayList.size());
        repeatedFromcat.append(arrayList.get(word)).append(" ");
        }
        return repeatedFromcat;
    }
}
