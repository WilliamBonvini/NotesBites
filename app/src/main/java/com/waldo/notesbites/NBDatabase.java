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
            String SUBJECT_NAME;
            int subjectID;
            int moduleID;
            String moduleName;
            int quizID;
            String question;
            String option1, option2, option3, option4, correctOption;
            SUBJECT_NAME = "Artificial Intelligence";
            ///////////////// AI
            ////MODULE 1
            subjectDao.insert(new Subject(SUBJECT_NAME,"as taught during the academic year 2018/2019",R.drawable.ai,"The goal of the course is to introduce the students to basic problems, models, and techniques of Artificial Intelligence (AI), and to enable them to model and solve specific AI problems. The course covers the most fundamental concepts, modelling approaches, and resolution methods of core AI, and also provides an introduction to the history of the discipline and to some philosophical issues involved. The teaching method is traditional (classroom lessons).",false ));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleName = "introduction to AI";
            moduleDao.insert(new Module(moduleName,"bangarang",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(moduleName);
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
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));
            question = "The emitted power impacts on the distance between transmitter and receiver. It is a tunable power so is best to";
            option1 = "set it to the highest value in order to reach the greater number of devices";
            option2 = "set it to the lowest value which allows for \"good reception\", which is measured in terms of Bit Error Rate.";
            option3 = "set it to the lowest value in order to save battery";
            option4 = "it is not a tunable parameter; it depends from the type of mote we are using";
            correctOption = "set it to the lowest value which allows for \"good reception\", which is measured in terms of Bit Error Rate.";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));

            ////MODULE 2
            moduleName = "modulo2";
            moduleDao.insert(new Module(moduleName,"bangarang",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleID = moduleDao.getModuleIDByModuleName(moduleName);
            quizDao.insert(new Quiz(moduleID, 0));
            question = "What is the definition of Acyclic Traffic:";
            option1 = "traffic generated by unpredictable events";
            option2 = "periodic transfer of sensor measurements (field devices) and set points";
            option3 = "images/videos (smarth camera that sends images when there is an intrusion)";
            option4 = "aggragated flows (aggregated traffic)";
            correctOption = "traffic generated by unpredictable events";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));
            question = "Which of the following answers describe best a scheduled access:";
            option1 = "it is easy to implement but it only has \"statistical\" guarantees on performance";
            option2 = "is easy to implement and require no coordination thanks to the polling scheme.";
            option3 = "It has guaranteed performance but coordination is required";
            option4 = "there can be many collisions under heavy traffic";
            correctOption = "It has guaranteed performance but coordination is required";
            quizDao.insert(new QuizQuestion(question, 2, quizID,  option1, option2, option3, option4, correctOption));

            moduleDao.insert(new Module("modulo3","bangarang",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo4","bangarang",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo5","bangarang",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));




            //////////////// MACHINE LEARNING
            SUBJECT_NAME = "Machine Learning";
            subjectDao.insert(new Subject(SUBJECT_NAME,"as taught during the academic year 2018/2019",R.drawable.ml,"The course is an introduction to the area of Artificial Intelligence, known as Machine Learning, that deals with the development of algorithmic techniques to extract knowledge from large amount of data (e.g., retail databases, web logs, etc.). The course focuses mainly on supervised and unsupervised techniques, e.g., decision trees, decision rules, induction of Horn clauses, hierarchical clustering, etc. And it will consider mainly Data Mining applications.",false ));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("introduction to ML","bangarang",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo2","bangarang",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo3","bangarang",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo4","bangarang",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo5","bangarang",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));



            /////////// ANN2DL
            SUBJECT_NAME = "Artificial Neural Networks and Deep Learning";
            subjectDao.insert(new Subject(SUBJECT_NAME,"as taught during the academic year 2019/2020",R.drawable.ann2dl,"Neural networks are mature, flexible, and powerful non-linear data-driven models that have successfully been applied to solve complex tasks in science and engineering. The advent of the deep learning paradigm, i.e., the use of (neural) network to simultaneously learn an optimal data representation and the corresponding model, has further boosted neural networks and the data-driven paradigm.\n" +
                    "\n" +
                    "Nowadays, deep neural network can outperform traditional hand-crafted algorithms, achieving human performance in solving many complex tasks, such as natural language processing, text modeling, gene expression modeling, and image recognition. The course provides a broad introduction to neural networks (NN), starting from the traditional feedforward (FFNN) and recurrent (RNN) neural networks, till the most successful deep-learning models such as convolutional neural networks (CNN) and long short-term memories (LSTM).\n" +
                    "\n" +
                    "The course major goal is to provide students with the theoretical background and the practical skills to understand and use NN, and at the same time become familiar and with Deep Learning for solving complex engineering problems.",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("Introduction","bangarang",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Agents' building approaches","bangarang",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Markov Decision Processes","bangarang",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Game Theory","bangarang",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Nash Equilibrium","bangarang",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));





            /////////////// IOT
            SUBJECT_NAME = "Internet of Things";
            subjectDao.insert(new Subject(SUBJECT_NAME,"as taught during the academic year 2019/2020",R.drawable.iot,"The use of Smart Things is nowadays is more and more widespread and concerns very different areas, such as business intelligence, domotics, healthcare, logistics and industry 4.0; for these reasons the impact of IoT on daily life concerns all those objects that can be connected to each other and communicate data on their state of use or the surrounding environment.",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("introduction to IOT","bangarang",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo2","bangarang",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo3","bangarang",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo4","bangarang",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo5","bangarang",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));


            //////////////// RL
            SUBJECT_NAME = "Reinforcement Learning";
            subjectDao.insert(new Subject(SUBJECT_NAME,"as taught during the academic year 2018/2019",R.drawable.rl,"Reinforcement learning (RL) is an area of machine learning concerned with how software agents ought to take actions in an environment in order to maximize the notion of cumulative reward. Reinforcement learning is one of three basic machine learning paradigms, alongside supervised learning and unsupervised learning.",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("introduction to RL","bangarang",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo2","bangarang",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo3","bangarang",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo4","bangarang",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo5","bangarang",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));




            //////////////// RS
            SUBJECT_NAME = "Recommender Systems";
            subjectDao.insert(new Subject(SUBJECT_NAME,"as taught during the academic year 2019/2020",R.drawable.rs,"Recommender systems are algorithms that mimic the psychology and personality of humans, in order to predict their needs and desires. More formally, recommender systems adopt data-mining and machine-learning techniques to help users in finding attractive and useful products. Products can be almost anything: physical items (e.g., smartphones), places (e.g., restaurants), digital content (e.g., movies and music), and many more. Recommender systems produce recommendations based on different inputs: demographic information about users, ratings and comments on products, individual’s or community’s past preferences and choices, social networks, context of use.\n" +
                    "\n" +
                    "During the last years, recommender systems have seen an increasing adoption in various services. The most famous success story of a recommender system is Netflix. The Netflix company launched a competition (www.netflixprize.com) offering a 1 million dollar prize to anyone able to create a better recommendation system than the one adopted by Netflix itself for its video-streaming service.\n" +
                    "\n" +
                    "The technology behind recommender systems has evolved over the past years into a rich collection of tools that enable practitioners and researchers to develop effective recommenders. We will study the most important of those algorithms, including how they work, how to use them, how to evaluate them, and their strengths and weaknesses in practice. The algorithms we will study include content-based filtering, collaborative filtering, dimensionality reduction, hybrid techniques, cros-domain and context aware techniques. The approach will be hands-on, with the evaluation based on a competition similar to the Netflix prize, which will involve implementation and testing of algorithms. We will also explore the design space for recommender systems, including designing recommender and the surrounding social issues such as identity, privacy, and manipulation.\n" +
                    "\n" +
                    " ",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("introduction to RS","bangarang",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo2","bangarang",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo3","bangarang",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo4","bangarang",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("modulo5","bangarang",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));


            ////////////////// AAAMS
            SUBJECT_NAME = "Autonomous Agents and Multiagent Systems";
            subjectDao.insert(new Subject(SUBJECT_NAME,"as taught during the academic year 2019/2020",R.drawable.aaams,"Computer systems are increasingly distributed and interconnected. This trend naturally leads to the development of systems composed of autonomous decision-making entities, called autonomous agents, that interact with each other in complex environments. Agent-based systems are an abstraction of specific computing systems deployed in several applications, including electronic commerce, control of industrial processes, logistics, ambient intelligence, web services, robotics, space systems, and modeling of complex systems.\n" +
                    "\n" +
                    "This course aims at presenting general techniques for developing multiagent systems, independently of the applicative domains. In particular, the course will present methods for developing single agents, able to make rational decisions in situations affected by uncertainty, and for developing systems composed of multiple agents, with special emphasis on the interaction mechanisms between the agents. Moreover, some real-world applications of agent systems will be discussed. At the end of the course, students will acquire the ability to design and develop distributed systems based on the agent paradigm.",false));
            subjectID = subjectDao.getSubjectIDbySubjectName(SUBJECT_NAME);
            moduleDao.insert(new Module("Introduction","bangarang",1,"aaams/1_Introduction.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Agents' building approaches","bangarang",2,"aaams/2_agents_building_approaches.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Markov Decision Processes","bangarang",3,"aaams/3_mdp.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Game Theory","bangarang",4,"aaams/4_game_theory.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Nash Equilibrium","bangarang",5,"aaams/5_nash_equilibrium.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Negotiation Part 1","bangarang",6,"aaams/6_negotiation_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Negotiation Part 2","bangarang",7,"aaams/7_negotiation_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Computational Social Choice Part 1","bangarang",8,"aaams/8_computational_social_choice_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Computational Social Choice Part 2","bangarang",9,"aaams/9_computational_social_choice_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Introduction to Auctions","bangarang",10,"aaams/10_auctions_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiple Items Auctions","bangarang",11,"aaams/11_auctions_multiple_items.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Computational Coalition Formation Part 1","bangarang",12,"aaams/12_computational_coal_form_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Computational Coalition Formation Part 2","bangarang",13,"aaams/13_computational_coal_form_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiagent Planning Part 1","bangarang",14,"aaams/14_multiagent_planning_1.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiagent Planning Part 2","bangarang",15,"aaams/15_multiagent_planning_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Introduction to DCOP","bangarang",16,"aaams/16_dcop.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("DCOP Algorithms","bangarang",17,"aaams/17_dcop_dpop_maxsum.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiagent Learning Part 1","bangarang",18,"aaams/18_multiagent_learning.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));
            moduleDao.insert(new Module("Multiagent Learning Part 2","bangarang",19,"aaams/19_multiagent_learning_2.md","https://www.youtube.com/watch?v=jNQXAC9IVRw",subjectID));


            return null;











        }



    }
}
