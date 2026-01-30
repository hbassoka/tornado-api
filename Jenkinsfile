pipeline {
    agent any

    parameters {
        choice(
            name: 'BRANCH_TO_BUILD',
            choices: ['master','develop','preproduction','production'],
            description: 'Choisissez la branche à builder'
        )
    }

    environment {
		

        GIT_REPO_URL = 'https://github.com/hbassoka/tornado-api.git'

        GIT_CREDENTIALS_ID = 'gitlab_creds'
        TAG_PREFIX = 'release-'

        SONARQUBE_ENV  = 'SonarQubeServer'
        SONARQUBE_HOST_URL = 'https://sonar.jdevhub.com'
                
        NEXUS_CREDENTIALS_ID = 'nexus-creds'
        MAVEN_SETTINGS='user-maven-settings'
       

        DOCKER_REGISTRY_URL = "nexus.jdevhub.com"
        DOCKER_REGISTRY_CREDENTIALS_ID = 'nexus-creds'

        PROD_KUBECONFIG_CREDENTIAL_ID = 'prod-kubeconfig-creds'
        PROD_SPRING_PROFILES_ACTIVE ='prod'         
        // Non sensibles (runtime)
        PROD_DB_URL = 'jdbc:postgresql://172.16.0.40:5432/tornadodb'
        PROD_DB_USERNAME = 'postgres'
        PROD_MAIL_SERVER = 'ssl0.ovh.net'
        PROD_MAIL_PORT = '465'
        PROD_MAIL_USERNAME = 'admin@jdevhub.com'
        PROD_KAFKA_SERVERS ='172.16.0.40:9092'
        
        // Sensibles (stockées dans Jenkins Credentials)
        PROD_DB_PASSWORD = 'prod-db-password'    // Jenkins Credential ID
        PROD_MAIL_PASSWORD = 'prod-mail-password'
        
        
        PPROD_KUBECONFIG_CREDENTIAL_ID  = 'dev-kubeconfig-creds'
        PPROD_SPRING_PROFILES_ACTIVE ='pprod'
        // Non sensibles (runtime)
        PPROD_DB_URL = 'jdbc:postgresql://172.16.0.30:5432/tornadodb'
        PPROD_DB_USERNAME = 'postgres'
        PPROD_MAIL_SERVER = 'ssl0.ovh.net'
        PPROD_MAIL_PORT = '465'
        PPROD_MAIL_USERNAME = 'admin@jdevhub.com'
        PPROD_KAFKA_SERVERS ='172.16.0.30:9092'
        
        // Sensibles (stockées dans Jenkins Credentials)
        PPROD_DB_PASSWORD = 'pprod-db-password'    // Jenkins Credential ID
        PPROD_MAIL_PASSWORD = 'pprod-mail-password'
    }

    tools {
        jdk 'java-21-LTS'
        maven 'maven-3.9.9'
    }

    stages {

        stage('Init') {
            steps {
                script { env.BUILD_TIME = System.currentTimeMillis().toString() }
            }
        }

        stage('Set Environment') {
            steps {
                script {
                    // Variables dynamiques selon la branche
                    // Stocker dans env pour les étapes suivantes
                    env.SPRING_PROFILES_ACTIVE = (params.BRANCH_TO_BUILD == 'preproduction') ? env.PPROD_SPRING_PROFILES_ACTIVE : env.PROD_SPRING_PROFILES_ACTIVE
                    env.DB_URL                 = (params.BRANCH_TO_BUILD == 'preproduction') ? env.PPROD_DB_URL : env.PROD_DB_URL
                    env.DB_USERNAME            = (params.BRANCH_TO_BUILD == 'preproduction') ? env.PPROD_DB_USERNAME : env.PROD_DB_USERNAME
                    env.MAIL_SERVER            = (params.BRANCH_TO_BUILD == 'preproduction') ? env.PPROD_MAIL_SERVER : env.PROD_MAIL_SERVER
                    env.MAIL_PORT              = (params.BRANCH_TO_BUILD == 'preproduction') ? env.PPROD_MAIL_PORT : env.PROD_MAIL_PORT
                    env.MAIL_USERNAME          = (params.BRANCH_TO_BUILD == 'preproduction') ? env.PPROD_MAIL_USERNAME : env.PROD_MAIL_USERNAME
                    env.KAFKA_SERVERS          = (params.BRANCH_TO_BUILD == 'preproduction') ? env.PPROD_KAFKA_SERVERS : env.PROD_KAFKA_SERVERS
                    

                    echo "Environment variables set for branch ${params.BRANCH_TO_BUILD}"
                }
            }
        }
        
        stage('Checkout') {
            steps {
                echo "🔍 Clonage de la branche : ${params.BRANCH_TO_BUILD}"
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${params.BRANCH_TO_BUILD}"]],
                    extensions: [[$class: 'WipeWorkspace']], // force clean
                    userRemoteConfigs: [[
                        url: "${env.GIT_REPO_URL}",
                        credentialsId: "${env.GIT_CREDENTIALS_ID}"
                    ]]
                ])
            }
        }

        stage('Extract Maven props') {
            steps {
                script {
                    env.ARTIFACT_ID = sh(script: 'mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', returnStdout: true).trim()
                    env.VERSION     = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true).trim()
                }
            }
        }

        stage('Build & Test') {
            steps {
				
				configFileProvider([
				    configFile(fileId: 'user-maven-settings', variable: 'MAVEN_SETTINGS')
				]) {
				    sh """
				       mvn clean verify -B -V -s ${MAVEN_SETTINGS}
				     """
				 }
               
            }
        }

     
        stage('SonarQube Analysis') {
            steps {
              timeout(time: 30, unit: 'MINUTES') {   // <-- augmente le temps si nécessaire
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=${env.ARTIFACT_ID} \
                        -Dsonar.projectName=${env.ARTIFACT_ID} \
                        -Dsonar.language=java                  \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.tests=src/test/java \
                        -Dsonar.host.url=${SONARQUBE_HOST_URL} \
                        -Dsonar.java.binaries=target/classes
                    """
                }
               }
            }
        }

      
        stage('Package') {
            steps {
				
				configFileProvider([
					 configFile(fileId: 'user-maven-settings', variable: 'MAVEN_SETTINGS')
			    ]) {
                sh """
                mvn package -B -V -s ${MAVEN_SETTINGS}
                
                """
                }
            }
        }

        stage('Deploy Artifacts to Nexus') {
		    when { expression { params.BRANCH_TO_BUILD == 'master' } }
		    steps {
		        withCredentials([usernamePassword(
		            credentialsId: "${env.NEXUS_CREDENTIALS_ID}", 
		            usernameVariable: 'NEXUS_USER', 
		            passwordVariable: 'NEXUS_PASS')]) {
		            
		           configFileProvider([
					    configFile(fileId: 'user-maven-settings', variable: 'MAVEN_SETTINGS')
					]) {
					    sh """
		                echo "📦 Déploiement vers Nexus (master uniquement)"
		                
		                # Utilise les credentials dans Maven via options -D
		                
		                mvn clean deploy -B -s ${MAVEN_SETTINGS} -DskipTests
		                
		                """
		            }
		        }
		    }
		}


        stage('Docker Build & Push') {
		    when { anyOf { expression { params.BRANCH_TO_BUILD in ['preproduction','production'] } } }
		    steps {
		        script {
					
					echo " Docker Build & Push  STARTED"							
		            // Build Docker
		            
		            echo " Build, Tag & Push  Docker Image STARTED"
		            
		            configFileProvider([
					  configFile(fileId: env.MAVEN_SETTINGS, variable: 'MAVEN_SETTINGS')
					]) {
					sh """
					
					# Copy settings.xml to the build context
                    cp ${MAVEN_SETTINGS} ./settings.xml
    
					docker build --no-cache \
					--build-arg ARTIFACT_ID=${env.ARTIFACT_ID} \
					--build-arg VERSION=${env.VERSION} \
					--label build_time=${env.BUILD_TIME} \
					-t ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:${env.VERSION} .
					
					"""
		            }
		            		            
		            echo " Tag Docker image"
		           
		            sh "docker tag ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:${env.VERSION} ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:latest"
		
		            
		            echo " Push Docker image"
		            
		            withCredentials([
						usernamePassword(credentialsId: DOCKER_REGISTRY_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')
					]) {
						
		                sh "echo \$DOCKER_PASS | docker login ${DOCKER_REGISTRY_URL} -u \$DOCKER_USER --password-stdin"
		            }
		
		            sh "docker push ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:${env.VERSION}"
		            sh "docker push ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:latest"
		            sh "docker logout ${DOCKER_REGISTRY_URL}"
		
		            // Supprimer les images locales après push
		            sh "docker rmi -f ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:${env.VERSION}"
		            sh "docker rmi -f ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:latest"
		            sh "docker image prune -a -f"
		            
		            
		            
		            echo " Build, Tag & Push  Docker Image ENDED"
		        }
		    }
		}
        stage('Deploy to Kubernetes') {
    when { expression { params.BRANCH_TO_BUILD in ['preproduction','production'] } }

    steps {
        script {
            def kubeCred = (params.BRANCH_TO_BUILD == 'production')
                ? env.PROD_KUBECONFIG_CREDENTIAL_ID
                : env.PPROD_KUBECONFIG_CREDENTIAL_ID

             def dbPassword = (params.BRANCH_TO_BUILD == 'production')
                ? env.PROD_DB_PASSWORD
                : env.PPROD_DB_PASSWORD
                
             def mailPassword = (params.BRANCH_TO_BUILD == 'production')
                ? env.PROD_MAIL_PASSWORD
                : env.PPROD_MAIL_PASSWORD

            withCredentials([
                file(credentialsId: kubeCred, variable: 'KUBECONFIG'),
                string(credentialsId: dbPassword, variable: 'DB_PASSWORD'),
                string(credentialsId: mailPassword, variable: 'MAIL_PASSWORD')
            ]) {

                sh """
                
                kubectl delete configmap tornado-api-config --ignore-not-found
                kubectl delete secret tornado-api-secrets --ignore-not-found
                envsubst < k8s/deployment.yaml | kubectl delete -f - --ignore-not-found

                kubectl create configmap tornado-api-config \
                  --from-literal=SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE \
                  --from-literal=DB_URL=$DB_URL \
                  --from-literal=DB_USERNAME=$DB_USERNAME \
                  --from-literal=MAIL_SERVER=$MAIL_SERVER \
                  --from-literal=MAIL_PORT=$MAIL_PORT \
                  --from-literal=MAIL_USERNAME=$MAIL_USERNAME \
                  --from-literal=KAFKA_SERVERS=$KAFKA_SERVERS \
                  --dry-run=client -o yaml | kubectl apply -f -

                kubectl create secret generic tornado-api-secrets \
                  --from-literal=DB_PASSWORD=$DB_PASSWORD \
                  --from-literal=MAIL_PASSWORD=$MAIL_PASSWORD \
                  --dry-run=client -o yaml | kubectl apply -f -

                envsubst < k8s/deployment.yaml | kubectl apply -f -
                """
            }
        }
    }
}


    } // end stages

    post {
        always { echo "🏁 Pipeline Finished pour ${params.BRANCH_TO_BUILD}" }
        success { echo "✅ Pipeline Success pour ${params.BRANCH_TO_BUILD}" }
        failure { echo "❌ Pipeline Failed pour ${params.BRANCH_TO_BUILD}" }
    }
}
