package com.waldo.notesbites;

public class SubjectsList {
        //popolo i vari contenitori di moduli?
        private static final Module[] aiModules = {
                new Module("Introduction to AI","This is the content of Introduction to AI"),
                new Module("Minimax Algorithm","This is the content of Minimax Algorithm")
        } ;
        private static final Module[] iotModules = {
                new Module("Introduction to IOT","This is the content of Introduction to IOT"),
                new Module("Random topic in IOT","This is the content of Random topic in IOT")
        } ;
        private static final Module[] annModules = {
                new Module("Introduction to Artificial Neural Nets","This is the content of Introduction to Artificial Neural Nets"),
                new Module("The Perceptron","This is the content of The Perceptron")
        } ;
    public static final Subject[] subjects = {
            new Subject("Artificial Intelligence", "Amigoni is kind of a stick in the butt", R.drawable.ai, aiModules),
            new Subject("Internet of Things", "I've no idea how to describe it", R.drawable.iot, iotModules),
            new Subject("Artificial Neural Networks", "Matteucci is fun", R.drawable.ann2dl, annModules)
    };
}
