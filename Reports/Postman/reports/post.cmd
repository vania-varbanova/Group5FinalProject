newman run collections/Posts.postman_collection.json -e environment/LocalEnvironment.postman_environment.json -r htmlextra --reporter-htmlextra-export ./reports/post_report.html --reporter-htmlextra-browserTitle "Post tests report" --reporter-htmlextra-displayProgressBar --reporter-htmlextra-title "Post tests report dashaboard" & cd reports & start post_report.html
