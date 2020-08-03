package com.waldo.notesbites;

public class SubjectsList {
        //popolo i vari contenitori di moduli?
        private static final Module[] aiModules = {
                new Module("Introduction to AI","This is the content of Introduction to AI",R.drawable.intro),
                new Module("Minimax Algorithm","This is the content of Minimax Algorithm",R.drawable.m1)
        } ;
        private static final Module[] iotModules = {
                new Module("Introduction to IOT","This is the content of Introduction to IOT",R.drawable.intro),
                new Module("Random topic in IOT","This is the content of Random topic in IOT",R.drawable.m1)
        } ;
        private static final Module[] annModules = {
                new Module("Introduction to Artificial Neural Nets","This is the content of Introduction to Artificial Neural Nets",R.drawable.intro),
                new Module("The Perceptron","This is the content of The Perceptron",R.drawable.m1)
        } ;
    public static final Subject[] subjects = {
            new Subject(0,"Artificial Intelligence", "Amigoni is kind of a stick in the butt", R.drawable.ai, aiModules),
            new Subject(1,"Internet of Things", "I've no idea how to describe it", R.drawable.iot, iotModules),
            new Subject(2,"Artificial Neural Networks", "Matteucci is fun", R.drawable.ann2dl, annModules)
    };
}

ù