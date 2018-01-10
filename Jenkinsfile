pipeline {
//    agent any
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    parameters {
        choice(choices: 'api-tests-suite.xml\ngui-tests-suite.xml', description: 'What environment?', name: 'suite')
        choice(choices: 'chrome\nfirefox', description: 'Browser', name: 'browser')
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn --version'
                sh "mvn clean install -DsuiteXmlFile=${params.suite} -Dbrowser=${params.browser}"
            }
        }
    }
}

