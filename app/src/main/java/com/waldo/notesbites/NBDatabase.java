package com.waldo.notesbites;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Subject.class,Module.class,Quiz.class,QuizQuestion.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class NBDatabase extends RoomDatabase {

    private static NBDatabase instance;
    public abstract SubjectDao subjectDao();
    public abstract ModuleDao moduleDao();
    public abstract QuizDao quizDao();
    public static synchronized NBDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NBDatabase.class,"nb_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private SubjectDao subjectDao;
        private ModuleDao moduleDao;
        private QuizDao quizDao;

        private PopulateDbAsyncTask(NBDatabase db){
            subjectDao = db.subjectDao();
            moduleDao = db.moduleDao();
            quizDao = db.quizDao();
        }

        @Override
        protected Void doInBackground(Void... voids){
            String SUBJECT_NAME, MODULE_NAME;
            int subjectID;
            int moduleID;
            int quizID;
            String question;
            String option1, option2, option3, option4, correctOption;
            ///////////////// AI
            SUBJECT_NAME = "Artificial Intelligence";
            subjectDao.insert(new Subject(SUBJECT_NAME,"academic year 2018/2019",R.drawable.ai,"The goal of the course is to introduce the students to basic problems, models, and techniques of Artificial Intelligence (AI), and to enable them to model and solve specific AI problems. The course covers the most fundamental concepts, modelling approaches, and resolution methods of core AI, and also provides an introduction to the history of the discipline and to some philosophical issues involved. The teaching method is traditional (classroom lessons).",false ));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            ////MODULE 1
            MODULE_NAME = "Introduction to AI";
            moduleDao.insert(new Module(MODULE_NAME,"",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            // Quiz
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            quizDao.insert(new Quiz(moduleID, 0));

            ////MODULE 2
            MODULE_NAME = "Agents' Building Approaches";
            moduleDao.insert(new Module(MODULE_NAME,"",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            // Quiz
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            quizDao.insert(new Quiz(moduleID, 0));
            //// MODULE 3
            moduleDao.insert(new Module("modulo3","",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            //// MODULE 4
            moduleDao.insert(new Module("modulo4","",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            //// MODULE 5
            moduleDao.insert(new Module("modulo5","",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));




            //////////////// MACHINE LEARNING
            SUBJECT_NAME = "Machine Learning";
            subjectDao.insert(new Subject(SUBJECT_NAME,"academic year 2018/2019",R.drawable.ml,"The course is an introduction to the area of Artificial Intelligence, known as Machine Learning, that deals with the development of algorithmic techniques to extract knowledge from large amount of data (e.g., retail databases, web logs, etc.). The course focuses mainly on supervised and unsupervised techniques, e.g., decision trees, decision rules, induction of Horn clauses, hierarchical clustering, etc. And it will consider mainly Data Mining applications.",false ));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("introduction to ML","",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo2","",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo3","",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo4","",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo5","",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));



            /////////// ANN2DL
            SUBJECT_NAME = "Artificial Neural Networks and Deep Learning";
            subjectDao.insert(new Subject(SUBJECT_NAME,"academic year 2019/2020",R.drawable.ann2dl,"Neural networks are mature, flexible, and powerful non-linear data-driven models that have successfully been applied to solve complex tasks in science and engineering. The advent of the deep learning paradigm, i.e., the use of (neural) network to simultaneously learn an optimal data representation and the corresponding model, has further boosted neural networks and the data-driven paradigm.\n" +
                    "\n" +
                    "Nowadays, deep neural network can outperform traditional hand-crafted algorithms, achieving human performance in solving many complex tasks, such as natural language processing, text modeling, gene expression modeling, and image recognition. The course provides a broad introduction to neural networks (NN), starting from the traditional feedforward (FFNN) and recurrent (RNN) neural networks, till the most successful deep-learning models such as convolutional neural networks (CNN) and long short-term memories (LSTM).\n" +
                    "\n" +
                    "The course major goal is to provide students with the theoretical background and the practical skills to understand and use NN, and at the same time become familiar and with Deep Learning for solving complex engineering problems.",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("Introduction","",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Agents' building approaches","",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Markov Decision Processes","",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Game Theory","",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Nash Equilibrium","",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));





            /////////////// IOT
            SUBJECT_NAME = "Internet of Things";
            subjectDao.insert(new Subject(SUBJECT_NAME,"academic year 2019/2020",R.drawable.iot_img,"The use of Smart Things is nowadays is more and more widespread and concerns very different areas, such as business intelligence, domotics, healthcare, logistics and industry 4.0; for these reasons the impact of IoT on daily life concerns all those objects that can be connected to each other and communicate data on their state of use or the surrounding environment.",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);

            // MODULE 1
            MODULE_NAME = "The Things";
            moduleDao.insert(new Module(MODULE_NAME,"",1, "iot_/IoT_module1.md","https://www.youtube.com/watch?v=LlhmzVL5bm8",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            // Quiz
            quizDao.insert(new Quiz(moduleID, 0));
            quizID = quizDao.getQuizIDbyModuleID(moduleID);
            question = "Why Energy Efficiency is a must?";
            option1 = "Recharching or battery replacement may be immaterial or too expensive, and it is not always feasible to use a secondary battery which is rechargeable.";
            option2 = "Recharging a mote can take a very long time and in that period it can communicate with other motes.";
            option3 = "It is absolutely impossible to reacharge a mote, so when its battery dies all the motes connected to that network needs to be replaced.";
            option4 = "Energy efficiency is not a big problem nowadays, thanks to the wireless charging.";
            correctOption = "Recharching or battery replacement may be immaterial or too expensive, and it is not always feasible to use a secondary battery which is rechargeable.";
            quizDao.insert(new QuizQuestion(question, 1, quizID,  option1, option2, option3, option4, correctOption));
            question = "Which part consumes more?";
            option1 = "Sensing peripherals";
            option2 = "CPU";
            option3 = "They all consume the same more or less";
            option4 = "The communication part";
            correctOption = "The communication part";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));
            question = "What is the \"idle\" listening problem?";
            option1 = "the power consumption of communications devices is roughly the same wheter the radio is transmitting or receiving.";
            option2 = "the power consumption of communications devices is roughly the same as the power consumption of the sensing peripherals.";
            option3 = "When the radio is ON it consumes more power than when it is transmitting or receiving since it is always looking for a signal";
            option4 = "the power consumption of communications devices is roughly the same wheter the radio is transmitting, receiving or simply ON.";
            correctOption = "the power consumption of communications devices is roughly the same wheter the radio is transmitting, receiving or simply ON.";
            quizDao.insert(new QuizQuestion(question, 3, quizID,  option1, option2, option3, option4, correctOption));
            question = "The emitted power impacts on the distance between transmitter and receiver. It is a tunable power so is best to";
            option1 = "set it to the highest value in order to reach the greater number of devices";
            option2 = "set it to the lowest value which allows for \"good reception\", which is measured in terms of Bit Error Rate.";
            option3 = "set it to the lowest value in order to save battery";
            option4 = "it is not a tunable parameter; it depends from the type of mote we are using";
            correctOption = "set it to the lowest value which allows for \"good reception\", which is measured in terms of Bit Error Rate.";
            quizDao.insert(new QuizQuestion(question, 4, quizID,  option1, option2, option3, option4, correctOption));

            // MODULE 2
            MODULE_NAME = "Communication Technologies for the IoT";
            moduleDao.insert(new Module(MODULE_NAME,"",2, "iot_/IoT_module2.md","https://www.youtube.com/watch?v=WtRpFLx34BY",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            // Quiz
            quizDao.insert(new Quiz(moduleID, 0));
            quizID = quizDao.getQuizIDbyModuleID(moduleID);
            question = "What is the definition of Acyclic Traffic?";
            option1 = "traffic generated by unpredictable events";
            option2 = "periodic transfer of sensor measurements (field devices) and set points";
            option3 = "images/videos (smarth camera that sends images when there is an intrusion)";
            option4 = "aggragated flows (aggregated traffic)";
            correctOption = "traffic generated by unpredictable events";
            quizDao.insert(new QuizQuestion(question, 1, quizID,  option1, option2, option3, option4, correctOption));
            question = "Which of the following answers describe best a scheduled access?";
            option1 = "it is easy to implement but it only has \"statistical\" guarantees on performance";
            option2 = "is easy to implement and require no coordination thanks to the polling scheme.";
            option3 = "It has guaranteed performance but coordination is required";
            option4 = "there can be many collisions under heavy traffic";
            correctOption = "It has guaranteed performance but coordination is required";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));

            // MODULE 3
            MODULE_NAME = "The Long Range Communication Technologies for IoT";
            moduleDao.insert(new Module(MODULE_NAME,"",3, "iot_/IoT_module3.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            // Quiz
            quizDao.insert(new Quiz(moduleID, 0));
            quizID = quizDao.getQuizIDbyModuleID(moduleID);
            question = "In LoraWAN, the wireless links between end-nodes and gateway:";
            option1 = "there is and association phase in order to increase the performance";
            option2 = "are association-less, in order to increasy the simplicity";
            option3 = "generally are association-less, but sometimes and end-device can associate to a specific gateway in order to increase the performances";
            option4 = "there isn't a standard, every end-node and gateway can decide to associate or not";
            correctOption = "are association-less, in order to increasy the simplicity";
            quizDao.insert(new QuizQuestion(question, 1, quizID,  option1, option2, option3, option4, correctOption));
            question = "The spreading factor (SF) represents the number of symbols sent per bit of information; which of the following is the correct answer:";
            option1 = "Higher SF implies fewer chirps are sent per second. The benefit of high SF is that more extended airtime results in better sensitivity.";
            option2 = "Lower SF implies fewer chirps are sent per second. The benefit of low SF is that more extended airtime results in better sensitivity.";
            option3 = "Lower SF means more chirps are sent per second: hence, you can encode more data per second. This results in better sensitivity.";
            option4 = "Higher SF implies more chirps are sent per second. The benefit of high SF is that less airtime results in better sensitivity.";
            correctOption = "Higher SF implies fewer chirps are sent per second. The benefit of high SF is that more extended airtime results in better sensitivity.";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));
            question = "ALOHA protocol:";
            option1 = "Time is continuous. The first packet in the transmission queue is transmitted as soon as ready. If the ACK does not come, the transmission is reattempted after a random number of slots X.";
            option2 = "Time is discrete. The first packet in the transmission queue is transmitted as soon as ready. If the ACK does not come, the transmission is reattempted after a random number of slots X.";
            option3 = "Time is continuous. The first packet in the transmission queue is transmitted as soon as ready. If the ACK does not come, the transmission is aborted.";
            option4 = "Time is discrete. The first packet in the transmission queue is transmitted as soon as ready. If the ACK does not come, the transmission is reattempted immediately.";
            correctOption = "Time is continuous. The first packet in the transmission queue is transmitted as soon as ready. If the ACK does not come, the transmission is reattempted after a random number of slots X.";
            quizDao.insert(new QuizQuestion(question, 3, quizID,  option1, option2, option3, option4, correctOption));

            // MODULE 4
            MODULE_NAME = "Short Range Communication Technologies and Protocols";
            moduleDao.insert(new Module(MODULE_NAME,"",4, "iot_/IoT_module4.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            // Quiz
            quizDao.insert(new Quiz(moduleID, 0));
            quizID = quizDao.getQuizIDbyModuleID(moduleID);
            question = "ZigBee and 6LowPAN:";
            option1 = "They both not extends IP to the Internet of Things";
            option2 = "They both extends IP to the Internet of Things";
            option3 = "ZigBee extends IP to the Internet of Things, 6LowPAN doesn't.";
            option4 = "6LowPAN extends IP to the Internet of Things, ZigBee doesn't.";
            correctOption = "6LowPAN extends IP to the Internet of Things, ZigBee doesn't.";
            quizDao.insert(new QuizQuestion(question, 1, quizID,  option1, option2, option3, option4, correctOption));
            question = "802.15.4 Channel Access:";
            option1 = "Combines a mixture of Scheduled and Random Access. Random Access is implemented through PAN coordinator, Scheduled Access allowed between RFDs and between RFD/FFD and PAN Coordinator";
            option2 = "Combines a mixture of Scheduled and Random Access. Scheduled Access is implemented through PAN coordinator, Random Access allowed between RFDs and between RFD/FFD and PAN Coordinator ";
            option3 = "It only uses Scheduled Access which is implemented through PAN coordinator";
            option4 = "It only uses Random Access which is implemented through PAN coordinator";
            correctOption = "Combines a mixture of Scheduled and Random Access. Scheduled Access is implemented through PAN coordinator, Random Access allowed between RFDs and between RFD/FFD and PAN Coordinator ";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));

            // MODULE 5
            MODULE_NAME = "6LowPAN";
            moduleDao.insert(new Module(MODULE_NAME,"",5, "iot_/IoTmodule5.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            // Quiz
            quizDao.insert(new Quiz(moduleID, 0));
            quizID = quizDao.getQuizIDbyModuleID(moduleID);
            question = "Which of the following describes a Simple LoWPAN architecture:";
            option1 = "There is a edge router, but it doesn't provide any connection to the internet.";
            option2 = "no connection to the internet";
            option3 = "There are multiple LoWPANs, each one controlled by an edge router. The router are then connected by a common backbone link";
            option4 = "There is a edge router that is a device that interconnects the IoT network to the backend ";
            correctOption = "There is a edge router that is a device that interconnects the IoT network to the backend ";
            quizDao.insert(new QuizQuestion(question, 1, quizID,  option1, option2, option3, option4, correctOption));
            question = "Why we need some adaptation features in 6LoWPAN:";
            option1 = "IPv6 packet have very large header w.r.t. the limited sizes that we have at Medium Access Control (127 Byte) in 6LoWPAN so we need to compress the header, both of IPv6 and UDP.";
            option2 = "IPv6 packet have very large header w.r.t. the limited sizes that we have at Medium Access Control (127 Byte) in 6LoWPAN so we need to implement a fragmentation in order to not lose information of the header of IPv6.";
            option3 = "IPv6 packet have a smaller header w.r.t. what we have at Medium Access Control in 6LoWPAN so we can aggregate the IPv6 and UDP header in order to save some space.";
            option4 = "IPv6 packet have a header of the same size that we have at Medium Access Control (127 Byte) in 6LoWPAN, therefore we don't need to compress the header of IPv6.";
            correctOption = "IPv6 packet have very large header w.r.t. the limited sizes that we have at Medium Access Control (127 Byte) in 6LoWPAN so we need to compress the header, both of IPv6 and UDP.";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));

            // MODULE 6
            MODULE_NAME = "Application Layer Protocols for the IoT";
            moduleDao.insert(new Module(MODULE_NAME,"",6, "iot_/IoT_module6","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            // Quiz
            quizDao.insert(new Quiz(moduleID, 0));
            quizID = quizDao.getQuizIDbyModuleID(moduleID);
            question = "Which of the following is not a characteristic of COAP:";
            option1 = "Asynchronous transaction model: allows delay between request and response; resource may not be ready due to duty cycle";
            option2 = "messages don't have a header";
            option3 = "UDP binding with reliability and multicast support: COAP builds on top of UDP and not TCP for simplicity";
            option4 = "URI support";
            correctOption = "messages don't have a header";
            quizDao.insert(new QuizQuestion(question, 1, quizID,  option1, option2, option3, option4, correctOption));
            question = "MQTT";
            option1 = "has a publish/subscribe paradigm: the clients don't know each other. Every clients can be a publisher and a subcriber.";
            option2 = "has a publish/subscribe paradigm: the clients know each other. Some clients are publisher and other are subcriber.";
            option3 = "has a Client/Server protocol: resources must be asked by a client to a server";
            option4 = "has a Client/Server protocol:  the clients know each other. They can communicate among themselves to retrieve data.";
            correctOption = "has a publish/subscribe paradigm: the clients don't know each other. Every clients can be a publisher and a subcriber.";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));
            question = "What Quality of Service 1 means:";
            option1 = "it means that the client publish the message (sends the message to the broker) and then he doesn't care no more.";
            option2 = "there is a double check. First there is the PUBLISH message, then the ACK (PUBREC). Then the client sends a PUBREL saying that has received the ACK (is an ACK for the ACK). Lastly the broker sends a PUBCOMP to indicate the completion of the exchange";
            option3 = "the MQTT clients stores the message and keep retransmitting it until it is acknowledged by the MQTT broker but the message can be received multiple times.";
            option4 = "QoS1 means that the Client sends the request to the server and expets 1 message as response.";
            correctOption = "the MQTT clients stores the message and keep retransmitting it until it is acknowledged by the MQTT broker but the message can be received multiple times.";
            quizDao.insert(new QuizQuestion(question, 3, quizID,  option1, option2, option3, option4, correctOption));

            // MODULE 7
            MODULE_NAME = "IoT Platforms";
            moduleDao.insert(new Module(MODULE_NAME,"",7, "iot_/IoT_module7","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            // Quiz
            quizDao.insert(new Quiz(moduleID, 0));
            quizID = quizDao.getQuizIDbyModuleID(moduleID);
            question = "What are the desired feature of a IoT platform:";
            option1 = "Device management, Security, Data analysis, Power management";
            option2 = "Device management, Privacy, Data analysis, Power management";
            option3 = "Device management, Data management, Data analysis, Security";
            option4 = "Device management, Data management, Data analysis";
            correctOption = "Device management, Data management, Data analysis, Security";
            quizDao.insert(new QuizQuestion(question, 1, quizID,  option1, option2, option3, option4, correctOption));

            // MODULE 8
            MODULE_NAME = "Radio Frequency Identification";
            moduleDao.insert(new Module(MODULE_NAME,"",8, "iot_/IoT_module8.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(MODULE_NAME);
            // Quiz
            quizDao.insert(new Quiz(moduleID, 0));
            quizID = quizDao.getQuizIDbyModuleID(moduleID);
            question = "Which of the following definitions regarding the types of tags is false:";
            option1 = "Semi-Active: mostly active,  operational power provided by battery but it has a limited capacity which is recharged from the reader radiated power.";
            option2 = "Passive: they have no battery, operational power scavenged from reader radiated power. 98% of the tags are of this type.";
            option3 = "Semi-Passive: mostly passive, but also equipted with some battery which is used only for calculation to power the computation unit";
            option4 = "Active: more similar to sensor nodes; operational power provided by battery, transmitter built into tag";
            correctOption = "Semi-Active: mostly active,  operational power provided by battery but it has a limited capacity which is recharged from the reader radiated power.";
            quizDao.insert(new QuizQuestion(question, 1, quizID,  option1, option2, option3, option4, correctOption));
            question = "What is RFID collision arbitration:";
            option1 = "The signal of the reader power up all tags in range. All tags turned on send back their ID in broadcast, there may be conflicts. Problem at the reader to distinguish all the ID received.";
            option2 = "The signal of the reader power up all tags in range. All tags turned on send back their ID to the reader, there may be conflicts. Problem at the reader to distinguish all the ID received.";
            option3 = "The signal of the reader is not powerful enough to turn on the tag, which can't sent his ID to the reader.";
            option4 = "The signal of the reader is not powerful enough to turn on the tag, which can't sent his ID in broadcast.";
            correctOption = "The signal of the reader power up all tags in range. All tags turned on send back their ID to the reader, there may be conflicts. Problem at the reader to distinguish all the ID received.";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));



            //////////////// RL
            SUBJECT_NAME = "Reinforcement Learning";
            subjectDao.insert(new Subject(SUBJECT_NAME,"academic year 2018/2019",R.drawable.rl,"Reinforcement learning (RL) is an area of machine learning concerned with how software agents ought to take actions in an environment in order to maximize the notion of cumulative reward. Reinforcement learning is one of three basic machine learning paradigms, alongside supervised learning and unsupervised learning.",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("introduction to RL","",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo2","",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo3","",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo4","",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo5","",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));




            //////////////// RS
            SUBJECT_NAME = "Recommender Systems";
            subjectDao.insert(new Subject(SUBJECT_NAME,"academic year 2019/2020",R.drawable.rs,"Recommender systems are algorithms that mimic the psychology and personality of humans, in order to predict their needs and desires. More formally, recommender systems adopt data-mining and machine-learning techniques to help users in finding attractive and useful products. Products can be almost anything: physical items (e.g., smartphones), places (e.g., restaurants), digital content (e.g., movies and music), and many more. Recommender systems produce recommendations based on different inputs: demographic information about users, ratings and comments on products, individual’s or community’s past preferences and choices, social networks, context of use.\n" +
                    "\n" +
                    "During the last years, recommender systems have seen an increasing adoption in various services. The most famous success story of a recommender system is Netflix. The Netflix company launched a competition (www.netflixprize.com) offering a 1 million dollar prize to anyone able to create a better recommendation system than the one adopted by Netflix itself for its video-streaming service.\n" +
                    "\n" +
                    "The technology behind recommender systems has evolved over the past years into a rich collection of tools that enable practitioners and researchers to develop effective recommenders. We will study the most important of those algorithms, including how they work, how to use them, how to evaluate them, and their strengths and weaknesses in practice. The algorithms we will study include content-based filtering, collaborative filtering, dimensionality reduction, hybrid techniques, cros-domain and context aware techniques. The approach will be hands-on, with the evaluation based on a competition similar to the Netflix prize, which will involve implementation and testing of algorithms. We will also explore the design space for recommender systems, including designing recommender and the surrounding social issues such as identity, privacy, and manipulation.\n" +
                    "\n" +
                    " ",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("introduction to RS","",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo2","",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo3","",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo4","",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo5","",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));


            ////////////////// AAAMS
            SUBJECT_NAME = "Autonomous Agents and Multiagent Systems";
            subjectDao.insert(new Subject(SUBJECT_NAME,"academic year 2019/2020",R.drawable.aaams,"Computer systems are increasingly distributed and interconnected. This trend naturally leads to the development of systems composed of autonomous decision-making entities, called autonomous agents, that interact with each other in complex environments. Agent-based systems are an abstraction of specific computing systems deployed in several applications, including electronic commerce, control of industrial processes, logistics, ambient intelligence, web services, robotics, space systems, and modeling of complex systems.\n" +
                    "\n" +
                    "This course aims at presenting general techniques for developing multiagent systems, independently of the applicative domains. In particular, the course will present methods for developing single agents, able to make rational decisions in situations affected by uncertainty, and for developing systems composed of multiple agents, with special emphasis on the interaction mechanisms between the agents. Moreover, some real-world applications of agent systems will be discussed. At the end of the course, students will acquire the ability to design and develop distributed systems based on the agent paradigm.",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("Introduction","",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Agents' building approaches","",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Markov Decision Processes","",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Game Theory","",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Nash Equilibrium","",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Negotiation Part 1","",6,"aaams/6_negotiation_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Negotiation Part 2","",7,"aaams/7_negotiation_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Computational Social Choice Part 1","",8,"aaams/8_computational_social_choice_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Computational Social Choice Part 2","",9,"aaams/9_computational_social_choice_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Introduction to Auctions","",10,"aaams/10_auctions_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiple Items Auctions","",11,"aaams/11_auctions_multiple_items.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Computational Coalition Formation Part 1","",12,"aaams/12_computational_coal_form_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Computational Coalition Formation Part 2","",13,"aaams/13_computational_coal_form_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiagent Planning Part 1","",14,"aaams/14_multiagent_planning_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiagent Planning Part 2","",15,"aaams/15_multiagent_planning_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Introduction to DCOP","",16,"aaams/16_dcop.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("DCOP Algorithms","",17,"aaams/17_dcop_dpop_maxsum.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiagent Learning Part 1","",18,"aaams/18_multiagent_learning.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiagent Learning Part 2","",19,"aaams/19_multiagent_learning_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));


            return null;

        }



    }
}
