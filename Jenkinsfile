pipeline {
  agent any

  parameters {
          choice(choices: 'api-tests-suite.xml', description: 'suite name', name: 'suite')
  }
  stages {
    stage('build') {
      steps {
        bat 'mvn --version'
        bat 'mvn clean install -DsuiteXmlFile=api-tests-suite.xml'
      }
    }
  }
}