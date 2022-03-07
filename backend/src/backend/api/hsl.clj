(ns backend.api.hsl
  (:require [clj-http.client :as client]
            [graphql-query.core :refer [graphql-query]]))

(def url "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql")

(defn get-stop-by-id
  []
  (let [query    (graphql-query {:queries [[:stop {:id "HSL:1173434"} [:name :lat :lon]]]})
        response (client/post url {:content-type "application/graphql"
                                   :body         query
                                   :as           :json})]
    (-> response
        :body
        :data
        :stop)))
