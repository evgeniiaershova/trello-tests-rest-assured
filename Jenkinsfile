pipeline {
    agent any

    parameters {
        choice(choices: "api-tests-suite.xml\ngui-tests-suite.xml", description: 'What environment?', name: 'suite')
        choice(choices: "chrome\nfirefox", description: 'Browser', name: 'browser')
    }

    stages {
        stage("Build") {
            steps {
                bat "mvn --version"
                bat "mvn clean install -DsuiteXmlFile=${params.suite} -Dbrowser=${params.browser}"
            }
        }
    }
}