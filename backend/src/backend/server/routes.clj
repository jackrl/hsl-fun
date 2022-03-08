(ns backend.server.routes
  (:require [backend.api.hsl :as hsl-api]
            [backend.model.stop :as stop-model]
            [clojure.spec.alpha :as s]))

(def routes
  ["/api"
   ["/stops"
    {:post {:summary    "Get stops at lat and lon within radius"
            :parameters {:body {:lat    float?
                                :lon    float?
                                :radius int?}}
            :responses  {200 {:body {:stops (s/coll-of stop-model/stop-spec)}}}
            :handler    (fn [{{{:keys [lat lon radius]} :body} :parameters}]
                          (let [hsl-nodes (hsl-api/get-hsl-nodes-in-radius lat lon radius)]
                            {:status 200
                             :body   {:stops (map stop-model/hsl-node->stop hsl-nodes)}}))}}]])
