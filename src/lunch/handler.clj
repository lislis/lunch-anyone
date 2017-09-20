(ns lunch.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer
             [wrap-defaults site-defaults]]
            [clojure.data.json :as json]
            [ring.adapter.jetty :as ring]
            [hiccup.core :as hiccup]
            [hiccup.page :as hp])
  (:gen-class))

(def lunch-options
  {:falafel {:emoji ":falafel:"}
   :pizza {:emoji ":pizza-spin:"}
   :bagel {:emoji ":bagel:"}
   :burger {:emoji ":burger:"}
   :thai {:emoji ":padthai:"}
   :vietnamese {:emoji ":curry:"}
   :hotdog-soup {:emoji ":hotdog::soup:"}
   :empanada {:emoji ":empanada:"}
   :kumpir {:emoji ":kumpir:"}})

(defn lunch-option [options]
  (rand-nth (vals options)))

(defn handle-request [req]
  (hp/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:title "Anyone up for lunch?"]]
   [:body
    [:div {:class "wrapper"}
     [:h1 "Lunch anyone?"]
     [:p {:class "food"}
      "How about "
      [:span (:emoji (lunch-option lunch-options))]]]]))


(defn handle-json [req]
  (let [lunch (:emoji (lunch-option lunch-options))]
    {:status 200
     :headers {"Content-Type" "text/json"}
     :body (json/write-str
            {:data
             {:emoji lunch}})}))

(defroutes app-routes
  (GET "/" [] handle-request)
  (GET "/api/emoji" [] handle-json)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (ring/run-jetty app {:port port})))
