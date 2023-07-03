node("ci-node") {

  stage("checkout") {
    checkout scmGit(branches: [[name: '*/angular']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Samlogy/project-learning']])
  }

  // stage("Quality Analyses"){
  //   sh "/opt/sonar-scanner/bin/sonar-scanner \\\n" +
  //     "  -Dsonar.projectKey=angular-s \\\n" +
  //     "  -Dsonar.sources=./src \\\n" +
  //     "  -Dsonar.projectName='angular-s \\\n" +
  //     "  -Dsonar.host.url=http://ci.check-consulting.net:11001 \\\n" +
  //     "  -Dsonar.token=sqp_a818bc464ceb4985bea7af89114471cde4b9842b"
  // }


  stage("build") {
    sh "npm install"
    sh "npm run build"
  }

  stage("build image") {
    sh "sudo docker build -t angular-app ."
  }

  // stage("push docker image") {
  //   withCredentials([usernamePassword(credentialsId: 's-docker-hub', usernameVariable: 'username',
  //     passwordVariable: 'password')]) {
  //     sh "sudo docker login -u senanisammy@gmail.com -p $password"
  //     sh "sudo docker tag angular-app senanisammy@gmail.com/angular-app:1.0"
  //     sh "sudo docker push senanisammy@gmail.com/angular-app:1.0"
  //     sh "sudo docker rmi senanisammy@gmail.com/angular-app:1.0"
  //     sh "sudo docker rmi angular-app"
  //     stash includes: 'docker-compose.yml', name: 'utils'
  //   }
  // }
  stage("push docker image") {
    withCredentials([usernamePassword(credentialsId: 's-docker-hub', usernameVariable: 'username',
      passwordVariable: 'password')]) {
      sh "sudo docker login -u '$username' --password-stdin '$password'"
      sh "sudo docker tag angular-app senanisammy/angular-app:1.0"
      sh "sudo docker push senanisammy/angular-app:1.0"
      sh "sudo docker rmi senanisammy/angular-app:1.0"
      sh "sudo docker rmi angular-app"
      stash includes: 'docker-compose.yml', name: 'utils'
    }
}


  node("apps-integration"){
    stage("deploy"){
      unstash 'utils'
      try{
        sh "sudo docker-compose down"
        sh "sudo docker-compose pull"
        sh "sudo docker-compose up -d"

      }catch (Exception e){
        println "No Running Docker Compose Running"
        sh "sudo docker-compose pull"
        sh "sudo docker-compose up -d"
      }

    }
  }


}