node("ci-node") {

    stage("checkout") {
        checkout scmGit(branches: [[name: '*/spring-crud-demo']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Samlogy/project-learning']])
    }

    stage("Quality Analyses"){
        sh "chmod 700 mvnw && ./mvnw clean verify sonar:sonar \\\n" +
                "  -Dsonar.projectKey=api \\\n" +
                "  -Dsonar.projectName='api' \\\n" +
                "  -Dsonar.host.url=http://ci.check-consulting.net:11001 \\\n" +
                "  -Dsonar.token=sqp_5083a3124779353423b8a578cf6ef5f598dd8721"
    }

    stage("build") {
        sh "chmod 777 mvnw"
        sh "./mvnw clean package -DskipTests=true"
    }

    stage("build image") {
        sh "sudo docker build -t api ."
    }

    stage("push docker image") {
        withCredentials([usernamePassword(credentialsId: 'mchekini', usernameVariable: 'username',
                passwordVariable: 'password')]) {
            sh "sudo docker login -u mchekini -p $password"
            sh "sudo docker tag api mchekini/api:1.0"
            sh "sudo docker push mchekini/api:1.0"
            sh "sudo docker rmi mchekini/api:1.0"
            sh "sudo docker rmi api"
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
                println "No Docker Compose Running"
                sh "sudo docker-compose pull"
                sh "sudo docker-compose up -d"
            }

        }
    }


}