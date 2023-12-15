pipeline {
    agent any

    stages {
        stage('run tests'){
            steps{
                bat 'javac -classpath junit-4.12.jar;src -d .\\classes src\\ism\\ase\\ro\\tests\\cases\\TestCase1.java src\\ism\\ase\\ro\\tests\\cases\\*.java src\\ism\\ase\\ro\\classes\\*.java src\\ism\\ase\\ro\\exceptions\\*.java' 
                bat 'java -cp junit-4.12.jar;hamcrest-core-1.3.jar;classes org.junit.runner.JUnitCore ism.ase.ro.tests.cases.TestCase1'
                
                bat 'javac -classpath junit-4.12.jar;src -d .\\classes src\\ism\\ase\\ro\\tests\\cases\\TestCase2.java src\\ism\\ase\\ro\\tests\\cases\\*.java src\\ism\\ase\\ro\\classes\\*.java src\\ism\\ase\\ro\\exceptions\\*.java' 
                bat 'java -cp junit-4.12.jar;hamcrest-core-1.3.jar;classes org.junit.runner.JUnitCore ism.ase.ro.tests.cases.TestCase2'
                
                bat 'javac -classpath junit-4.12.jar;src -d .\\classes src\\ism\\ase\\ro\\tests\\cases\\TestCase3.java src\\ism\\ase\\ro\\tests\\cases\\*.java src\\ism\\ase\\ro\\classes\\*.java src\\ism\\ase\\ro\\exceptions\\*.java' 
                bat 'java -cp junit-4.12.jar;hamcrest-core-1.3.jar;classes org.junit.runner.JUnitCore ism.ase.ro.tests.cases.TestCase3'
            }
        }
    }
}