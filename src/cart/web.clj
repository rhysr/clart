(ns cart.web
  (:require [compojure.core :refer [defroutes routes GET]]
            [compojure.route :as route]
            [compojure.handler :as handler]))


(defn init []
  (println "Starting application"))

(defn destroy []
  (println "Shutting down application"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))


(defn- home []
  {:status 200
   :body "<h1>Home page<h1>"})

(def app
  (handler/site
    (routes
      (GET "/" [] (home))
      app-routes)))
