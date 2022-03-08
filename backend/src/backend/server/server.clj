(ns backend.server.server
  (:require [backend.server.handler :as handler]
            [ring.adapter.jetty :as jetty]))

(defonce running-server (atom nil))

(defn start
  []
  (when (nil? @running-server)
    (clojure.core/reset! running-server (jetty/run-jetty #'handler/app {:port 5000, :join? false}))
    (println "server running in port 5000")))

(defn stop
  []
  (when-not (nil? @running-server)
    (.stop @running-server)
    (clojure.core/reset! running-server nil)))

(defn restart
  []
  (stop)
  (start))

(defn -main [& args]
  (start))
