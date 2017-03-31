(defproject lunch "0.1.0-SNAPSHOT"
  :description "Lunch anyone"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [hiccup "1.0.5"]]
  :main lunch.handler
  :uberjar-name "lunch-standalone.jar"
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler lunch.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
