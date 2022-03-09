(ns backend.api.hsl-test
  (:require [clojure.test :refer :all]
            [backend.api.hsl :as hsl-api]
            [clojure.data :as data]))

(deftest get-hsl-nodes-in-radius
  (testing "Returns hsl nodes correctly when hsl api returns several nodes"
    (with-redefs [clj-http.client/post (constantly {:status 200
                                                    :body   {:data {:stopsByRadius {:edges [{:node {:distance 225,
                                                                                                    :stop     {:name               "Pasilan asema",
                                                                                                               :lat                60.1981,
                                                                                                               :lon                24.93365,
                                                                                                               :wheelchairBoarding "POSSIBLE"}}}
                                                                                            {:node {:distance 261,
                                                                                                    :stop     {:name               "Pasilan asema",
                                                                                                               :lat                60.198075,
                                                                                                               :lon                24.933694,
                                                                                                               :wheelchairBoarding "NO_INFORMATION"}}}
                                                                                            {:node {:distance 294,
                                                                                                    :stop     {:name               "Pasilan asema",
                                                                                                               :lat                60.198002,
                                                                                                               :lon                24.933908,
                                                                                                               :wheelchairBoarding "NO_INFORMATION"}}}]}}}})]
      (let [lat    60.19803
            lon    24.93129
            radius 300
            result (hsl-api/get-hsl-nodes-in-radius lat lon radius)]
        (is (= [nil nil result] (data/diff result [{:distance 225, :stop {:name "Pasilan asema", :lat 60.1981, :lon 24.93365, :wheelchairBoarding "POSSIBLE"}}
                                                   {:distance 261, :stop {:name "Pasilan asema", :lat 60.198075, :lon 24.933694, :wheelchairBoarding "NO_INFORMATION"}}
                                                   {:distance 294, :stop {:name "Pasilan asema", :lat 60.198002, :lon 24.933908, :wheelchairBoarding "NO_INFORMATION"}}]))))))

  (testing "Returns one hsl nodes correctly when hsl api returns just one node"
    (with-redefs [clj-http.client/post (constantly {:status 200
                                                    :body   {:data {:stopsByRadius {:edges [{:node {:distance 225,
                                                                                                    :stop     {:name               "Pasilan asema",
                                                                                                               :lat                60.1981,
                                                                                                               :lon                24.93365,
                                                                                                               :wheelchairBoarding "POSSIBLE"}}}]}}}})]
      (let [lat    60.19803
            lon    24.93129
            radius 230
            result (hsl-api/get-hsl-nodes-in-radius lat lon radius)]
        (is (= [nil nil result] (data/diff result [{:distance 225, :stop {:name "Pasilan asema", :lat 60.1981, :lon 24.93365, :wheelchairBoarding "POSSIBLE"}}]))))
      ))

  (testing "Returns empty collection of nodes correctly when hsl api returns no nodes"
    (with-redefs [clj-http.client/post (constantly {:status 200
                                                    :body   {:data {:stopsByRadius {:edges []}}}})]
      (let [lat    60.19803
            lon    24.93129
            radius 100
            result (hsl-api/get-hsl-nodes-in-radius lat lon radius)]
        (is (empty? result)))
      )))
