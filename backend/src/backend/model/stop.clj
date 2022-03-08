(ns backend.model.stop
  (:require [spec-tools.data-spec :as ds]))

(def stop-spec
  (ds/spec
    {:name       string?
     :lat        float?
     :lon        float?
     :distance   int?
     :accessible boolean?}))

(defn hsl-node->stop
  [{:keys [distance stop]}]
  (assoc (dissoc stop :wheelchairBoarding)
    :distance distance
    :accessible (= (:wheelchairBoarding stop) "POSSIBLE")))
