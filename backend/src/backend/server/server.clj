(ns backend.server.server
  (:require [backend.server.handler :as handler]
            [ring.adapter.jetty :as jetty]))

(defn start []
  (jetty/run-jetty handler/app {:port 3000, :join? false})
  (println "server running in port 3000"))

(defn -main [& args]
  (start))
