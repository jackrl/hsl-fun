(ns backend.model.stop)

(defn hsl-node->stop
  [{:keys [distance stop]}]
  (assoc (dissoc stop :wheelchairBoarding)
    :distance distance
    :accessible (= (:wheelchairBoarding stop) "POSSIBLE")))
