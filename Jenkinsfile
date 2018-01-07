pipeline {
    agent any

    parameters {
        choice(choices: "api-tests-suite.xml\gui-tests-suite.xml", description: 'What environment?', name: 'suite')
    }

    stages {
        stage("Build") {
            steps {
                bat "mvn --version"
                bat "mvn clean install -DsuiteXmlFile=${params.suite}"
            }
        }
    }
}