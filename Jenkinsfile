pipeline {
  agent any

  parameters {
          choice(choices: 'api-tests-suite.xml\gui-tests-suite.xm', description: 'suite name', name: 'suite')
  }
  stages {
    stage('build') {
      steps {
        bat 'mvn --version'
        bat 'mvn clean'
        bat 'mvn install -DsuiteXmlFile=${params.suite}'
      }
    }
  }
}