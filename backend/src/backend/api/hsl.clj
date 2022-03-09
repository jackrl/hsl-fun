(ns backend.api.hsl
  (:require [clj-http.client :as client]))

(def url "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql")

(defn get-hsl-nodes-in-radius
  [lat lon radius]
  (let [query    (str "{stopsByRadius (lat: " lat ", lon: " lon ", radius: " radius ") {
                          edges {
                            node {
                              distance
                                stop {
                                  name
                                  lat
                                  lon
                                  wheelchairBoarding}}}}}")
        response (client/post url {:content-type "application/graphql"
                                   :body         query
                                   :as           :json})]
    (map :node (-> response
                   :body
                   :data
                   :stopsByRadius
                   :edges))))
