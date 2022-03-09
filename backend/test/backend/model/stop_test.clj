(ns backend.model.stop_test
  (:require [clojure.test :refer :all]
            [backend.model.stop :as stop-model]
            [clojure.data :as data]))

(deftest hsl-node->stop
  (testing "Transformation is done correctly when :wheelChairBoarding is 'POSSIBLE'"
    (let [result (stop-model/hsl-node->stop {:distance 333
                                             :stop     {:name               "NAME_X"
                                                        :lat                1.111111
                                                        :lon                2.222222
                                                        :wheelchairBoarding "POSSIBLE"}})]
      (is (= [nil nil result] (data/diff result {:name       "NAME_X"
                                               :lat        1.111111
                                               :lon        2.222222
                                               :distance   333,
                                               :accessible true})))))
  (testing "Transformation is done correctly when :wheelChairBoarding is something other than 'POSSIBLE'"
    (let [result (stop-model/hsl-node->stop {:distance 444
                                             :stop     {:name               "NAME_Y"
                                                        :lat                2.222222
                                                        :lon                1.111111
                                                        :wheelchairBoarding "NOT_POSSIBLE"}})]
      (is (= [nil nil result] (data/diff result {:name       "NAME_Y"
                                               :lat        2.222222
                                               :lon        1.111111
                                               :distance   444,
                                               :accessible false}))))))
